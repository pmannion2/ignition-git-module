package com.axone_io.ignition.git.commissioning;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;

//public class GitCommissioningConfig {
//    private String repoURI;
//    private String repoBranch;
//    private String ignitionProjectName;
//    private String ignitionUserName;
//    private String userName;
//    private String userPassword;
//    private String sshKey;
//    private String userEmail;
//
//    private boolean importImages = false;
//    private boolean importTags = false;
//    private boolean importThemes = false;
//
//    private String initDefaultBranch;
//
//    public String getRepoURI() {
//        return repoURI;
//    }
//
//    public void setRepoURI(String repoURI) {
//        this.repoURI = repoURI;
//    }
//
//    public String getRepoBranch() {
//        return repoBranch;
//    }
//
//    public void setRepoBranch(String repoBranch) {
//        this.repoBranch = repoBranch;
//    }
//
//    public String getIgnitionProjectName() {
//        return ignitionProjectName;
//    }
//
//    public void setIgnitionProjectName(String ignitionProjectName) {
//        this.ignitionProjectName = ignitionProjectName;
//    }
//
//    public String getUserName() {
//        return userName;
//    }
//
//    public void setUserName(String userName) {
//        this.userName = userName;
//    }
//
//    public String getUserPassword() {
//        return userPassword;
//    }
//
//    public void setUserPassword(String userPassword) {
//        this.userPassword = userPassword;
//    }
//
//    public String getUserEmail() {
//        return userEmail;
//    }
//
//    public void setUserEmail(String userEmail) {
//        this.userEmail = userEmail;
//    }
//
//    public String getIgnitionUserName() {
//        return ignitionUserName;
//    }
//
//    public void setIgnitionUserName(String ignitionUserName) {
//        this.ignitionUserName = ignitionUserName;
//    }
//
//    public boolean isImportImages() {
//        return importImages;
//    }
//
//    public void setImportImages(boolean importImages) {
//        this.importImages = importImages;
//    }
//
//    public boolean isImportTags() {
//        return importTags;
//    }
//
//    public void setImportTags(boolean importTags) {
//        this.importTags = importTags;
//    }
//
//    public boolean isImportThemes() {
//        return importThemes;
//    }
//
//    public void setImportThemes(boolean importThemes) {
//        this.importThemes = importThemes;
//    }
//
//    public String getSshKey() {
//        return sshKey;
//    }
//
//    public void setSshKey(String sshKey) {
//        this.sshKey = sshKey;
//    }
//
//    public void setSecretFromFilePath(Path filePath, boolean isSSHAuth) throws IOException {
//        if (filePath.toFile().exists() && filePath.toFile().isFile()) {
//            String secret = Files.readString(filePath, StandardCharsets.UTF_8);
//            if (isSSHAuth) {
//                this.sshKey = secret;
//            } else {
//                this.userPassword = secret;
//            }
//        }
//    }
//
//    public String getInitDefaultBranch() {
//        return initDefaultBranch;
//    }
//
//    public void setInitDefaultBranch(String initDefaultBranch) {
//        this.initDefaultBranch = initDefaultBranch;
//    }
//}


public class GitCommissioningConfig {

    // Existing fields and methods
    private String repoURI;
    private String repoBranch;
    private String ignitionProjectName;
    private String ignitionUserName;
    private String userName;
    private String userPassword;
    private String sshKey;
    private String userEmail;

    private boolean importImages = false;
    private boolean importTags = false;
    private boolean importThemes = false;

    private String initDefaultBranch;

    private ProjectConfig projectConfig;;

    public void loadFromProjectConfig(ProjectConfig projectConfig) {

        this.repoURI = projectConfig.getRepo().getUri();
        this.repoBranch = projectConfig.getRepo().getBranch();
        this.ignitionProjectName = projectConfig.getIgnition().getProjectName();
        this.ignitionUserName = projectConfig.getIgnition().getUserName();
        this.userName = projectConfig.getUser().getName();
        this.userEmail = projectConfig.getUser().getEmail();
        this.userPassword = projectConfig.getUser().getPassword();
        this.importImages = projectConfig.getCommissioning().isImportImages();
        this.importTags = projectConfig.getCommissioning().isImportTags();
        this.importThemes = projectConfig.getCommissioning().isImportThemes();
        this.initDefaultBranch = projectConfig.getInitDefaultBranch();
    }
    public String getRepoURI() {
        return repoURI;
    }

    public void setRepoURI(String repoURI) {
        this.repoURI = repoURI;
    }

    public String getRepoBranch() {
        return repoBranch;
    }

    public void setRepoBranch(String repoBranch) {
        this.repoBranch = repoBranch;
    }

    public String getIgnitionProjectName() {
        return ignitionProjectName;
    }

    public void setIgnitionProjectName(String ignitionProjectName) {
        this.ignitionProjectName = ignitionProjectName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getIgnitionUserName() {
        return ignitionUserName;
    }

    public void setIgnitionUserName(String ignitionUserName) {
        this.ignitionUserName = ignitionUserName;
    }

    public boolean isImportImages() {
        return importImages;
    }

    public void setImportImages(boolean importImages) {
        this.importImages = importImages;
    }

    public boolean isImportTags() {
        return importTags;
    }

    public void setImportTags(boolean importTags) {
        this.importTags = importTags;
    }

    public boolean isImportThemes() {
        return importThemes;
    }

    public void setImportThemes(boolean importThemes) {
        this.importThemes = importThemes;
    }

    public String getSshKey() {
        return sshKey;
    }

    public void setSshKey(String sshKey) {
        this.sshKey = sshKey;
    }

    public void setSecretFromFilePath(Path filePath, boolean isSSHAuth) throws IOException {
        if (filePath.toFile().exists() && filePath.toFile().isFile()) {
            String secret = Files.readString(filePath, StandardCharsets.UTF_8);
            if (isSSHAuth) {
                this.sshKey = secret;
            } else {
                this.userPassword = secret;
            }
        }
    }

    public String getInitDefaultBranch() {
        return initDefaultBranch;
    }

    public void setInitDefaultBranch(String initDefaultBranch) {
        this.initDefaultBranch = initDefaultBranch;
    }
}
class ProjectConfig {
    private RepoConfig repo;
    private IgnitionConfig ignition;
    private UserConfig user;
    private CommissioningConfig commissioning;
    private String initDefaultBranch;

    // Getters and setters
}

class RepoConfig {
    private String uri;
    private String branch;

    // Getters and setters
    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }
    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }
}


class IgnitionConfig {
    private String projectName;
    private String userName;

    // Getters and setters
    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}

class UserConfig {
    private String name;
    private String email;
    private String password;

    // Getters and setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

class CommissioningConfig {
    private boolean importThemes;
    private boolean importTags;
    private boolean importImages;

    // Getters and setters
    public boolean isImportThemes() {
        return importThemes;
    }

    public void setImportThemes(boolean importThemes) {
        this.importThemes = importThemes;
    }

    public boolean isImportTags() {
        return importTags;
    }

    public void setImportTags(boolean importTags) {
        this.importTags = importTags;
    }

    public boolean isImportImages() {
        return importImages;
    }

    public void setImportImages(boolean importImages) {
        this.importImages = importImages;
    }
}

class ProjectConfigs {
    private List<ProjectConfig> projects;
    // Getters and setters
}