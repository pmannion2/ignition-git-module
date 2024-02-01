package com.axone_io.ignition.git.commissioning.utils;

import com.axone_io.ignition.git.commissioning.GitCommissioningConfig;
import com.axone_io.ignition.git.commissioning.ProjectConfig;
import com.axone_io.ignition.git.commissioning.ProjectConfigs;
import com.axone_io.ignition.git.managers.GitImageManager;
import com.axone_io.ignition.git.managers.GitProjectManager;
import com.axone_io.ignition.git.managers.GitTagManager;
import com.axone_io.ignition.git.managers.GitThemeManager;
import com.axone_io.ignition.git.records.GitProjectsConfigRecord;
import com.axone_io.ignition.git.records.GitReposUsersRecord;
import com.google.common.eventbus.Subscribe;
import com.inductiveautomation.ignition.common.project.ProjectManifest;
import com.inductiveautomation.ignition.common.util.LoggerEx;
import com.inductiveautomation.ignition.gateway.localdb.persistence.PersistenceInterface;
import com.inductiveautomation.ignition.gateway.project.ProjectManager;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;
import simpleorm.dataset.SQuery;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

import static com.axone_io.ignition.git.GatewayHook.context;
import static com.axone_io.ignition.git.managers.GitManager.*;

public class GitCommissioningUtils {
    private final static LoggerEx logger = LoggerEx.newBuilder().build(GitCommissioningUtils.class);

    public static GitCommissioningConfig config;

    @Subscribe
    public static void loadConfiguration() {
        Path dataDir = getDataFolderPath();
        Path yamlConfigPath = dataDir.resolve("git.yaml"); // Assuming the YAML file is named git.yaml
        ProjectManager projectManager = context.getProjectManager();

        try (FileInputStream fis = new FileInputStream(yamlConfigPath.toFile())) {
            if (yamlConfigPath.toFile().exists() && yamlConfigPath.toFile().isFile()) {
                ProjectConfigs projectConfigs = parseYaml(yamlConfigPath);
//                GitCommissioningConfig projectConfigs = parseConfigLines(yamlBytes);

                for (ProjectConfig projectConfig : projectConfigs.getProjects()) {
                    GitCommissioningConfig gitConfig = new GitCommissioningConfig();
                    gitConfig.loadFromProjectConfig(projectConfig);

                    config = gitConfig;
                    if (projectManager.getProjectNames().contains(gitConfig.getIgnitionProjectName())) {
                        logger.info("The configuration of the git module was interrupted because the project '" + config.getIgnitionProjectName() + "' already exist.");
                        return;
                    }

                    if (config.getRepoURI() == null || config.getRepoBranch() == null
                            || config.getIgnitionProjectName() == null || config.getIgnitionUserName() == null
                            || config.getUserName() == null || (config.getUserPassword() == null && config.getSshKey() == null)
                            || config.getUserEmail() == null) {
                        throw new RuntimeException("Incomplete git configuration file.");
                    }

                    projectManager.createProject(config.getIgnitionProjectName(), new ProjectManifest(config.getIgnitionProjectName(), "", false, false, ""), new ArrayList());

                    Path projectDir = getProjectFolderPath(config.getIgnitionProjectName());
                    clearDirectory(projectDir);

                    // Creation of records
                    PersistenceInterface persistenceInterface = context.getPersistenceInterface();
                    SQuery<GitProjectsConfigRecord> query = new SQuery<>(GitProjectsConfigRecord.META).eq(GitProjectsConfigRecord.ProjectName, config.getIgnitionProjectName());
                    if (persistenceInterface.queryOne(query) != null) {
                        logger.info("The configuration of the git module was interrupted because the GitProjectsConfigRecord '" + config.getIgnitionProjectName() + "' already exist.");
                        return;
                    }
                    GitProjectsConfigRecord projectsConfigRecord = persistenceInterface.createNew(GitProjectsConfigRecord.META);
                    projectsConfigRecord.setProjectName(config.getIgnitionProjectName());
                    projectsConfigRecord.setURI(config.getRepoURI());

                    String userSecretFilePath = System.getenv("GATEWAY_GIT_USER_SECRET_FILE");
                    if (userSecretFilePath != null) {
                        config.setSecretFromFilePath(Paths.get(userSecretFilePath), projectsConfigRecord.isSSHAuthentication());
                    }
                    if (config.getSshKey() == null && config.getUserPassword() == null) {
                        throw new Exception("Git User Password or SSHKey not configured.");
                    }
                    persistenceInterface.save(projectsConfigRecord);

                    GitReposUsersRecord reposUsersRecord = persistenceInterface.createNew(GitReposUsersRecord.META);
                    reposUsersRecord.setUserName(config.getUserName());
                    reposUsersRecord.setIgnitionUser(config.getIgnitionUserName());
                    reposUsersRecord.setProjectId(projectsConfigRecord.getId());
                    if (projectsConfigRecord.isSSHAuthentication()) {
                        reposUsersRecord.setSSHKey(config.getSshKey());
                    } else {
                        reposUsersRecord.setPassword(config.getUserPassword());
                    }
                    reposUsersRecord.setEmail(config.getUserEmail());
                    persistenceInterface.save(reposUsersRecord);

                    // CLONE PROJECT
                    cloneRepo(config.getIgnitionProjectName(), config.getIgnitionUserName(), config.getRepoURI(), config.getRepoBranch());

                    // IMPORT PROJECT
                    GitProjectManager.importProject(config.getIgnitionProjectName());

                    // IMPORT TAGS
                    if (config.isImportTags()) {
                        GitTagManager.importTagManager(config.getIgnitionProjectName());
                    }

                    // IMPORT THEMES
                    if (config.isImportThemes()) {
                        GitThemeManager.importTheme(config.getIgnitionProjectName());
                    }

                    // IMPORT IMAGES
                    if (config.isImportImages()) {
                        GitImageManager.importImages(config.getIgnitionProjectName());
                    }
                }
            } else {
                logger.info("No git configuration file was found.");
            }
        }
        catch (Exception e) {
            logger.error("An error occurred while git configuration settings up from the provided YAML.", e);
        }


    }

    static protected ProjectConfigs parseYaml(Path yamlFilePath) {
        try (InputStream inputStream = new FileInputStream(yamlFilePath.toFile())) {
            Yaml yaml = new Yaml(new Constructor(ProjectConfigs.class));
            return yaml.load(inputStream);
        } catch (Exception e) {
            logger.error("An error occurred while parsing the YAML configuration file.", e);
            return null; // or throw a custom exception
        }
    }

}
