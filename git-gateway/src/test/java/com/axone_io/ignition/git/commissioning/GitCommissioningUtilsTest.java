package com.axone_io.ignition.git.commissioning;

import org.junit.Test;
import org.yaml.snakeyaml.LoaderOptions;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.nio.file.Path;

import static com.axone_io.ignition.git.managers.GitManager.getDataFolderPath;
import static org.junit.Assert.*;

public class GitCommissioningUtilsTest {
    @Test
    public void
    whenLoadYAMLDocumentWithTopLevelClass_thenLoadCorrectJavaObjectWithNestedObjects() throws FileNotFoundException {

        Yaml yaml = new Yaml(new Constructor(ProjectConfigs.class, new LoaderOptions()));
        Path dataDir = getDataFolderPath();
        Path yamlConfigPath = dataDir.resolve("git.yaml"); // Assuming the YAML file is named git.yaml
        InputStream inputStream = new FileInputStream(yamlConfigPath.toFile());
//        Yaml yaml = new Yaml(new Constructor(ProjectConfigs.class));
//        InputStream inputStream = this.getClass()
//                .getClassLoader()
//                .getResourceAsStream("C:\\Users\\PatrickMannion\\IdeaProjects\\ignition-git-module\\git-gateway\\src\\test\\java\\com\\axone_io\\ignition\\git\\commissioning\\git.yaml");
        ProjectConfigs projectConfigs = yaml.load(inputStream);

        assertNotNull(projectConfigs);
        assertNotNull(projectConfigs.getProjects());
        assertEquals(2, projectConfigs.getProjects()
                .size());

        // Unit tests for first project in git.yaml
        assertNotNull(projectConfigs.getProjects()
                .get(0)
                .getRepo());
        assertEquals("https://github.com/WHK01/whk-distillery01-mes-ui.git", projectConfigs.getProjects()
                .get(0)
                .getRepo()
                .getUri());
        assertEquals("development", projectConfigs.getProjects()
                .get(0)
                .getRepo()
                .getBranch());
        assertEquals("WHK-MES", projectConfigs.getProjects()
                .get(0)
                .getIgnition()
                .getProjectName());
        assertEquals("admin", projectConfigs.getProjects()
                .get(0)
                .getIgnition()
                .getUserName());
        assertFalse(projectConfigs.getProjects()
                .get(0)
                .getIgnition()
                .isInheritable());
        assertEquals("Global", projectConfigs.getProjects()
                .get(0)
                .getIgnition()
                .getParentName());
        assertEquals("pmannion2", projectConfigs.getProjects()
                .get(0)
                .getUser()
                .getName());
        assertEquals("pmannion@whiskeyhouse.com", projectConfigs.getProjects()
                .get(0)
                .getUser()
                .getName());
        assertEquals("abc123", projectConfigs.getProjects()
                .get(0)
                .getUser()
                .getName());
        assertTrue(projectConfigs.getProjects()
                .get(0)
                .getCommissioning()
                .isImportThemes());
        assertTrue(projectConfigs.getProjects()
                .get(0)
                .getCommissioning()
                .isImportTags());
        assertTrue(projectConfigs.getProjects()
                .get(0)
                .getCommissioning()
                .isImportImages());
        assertEquals("main",projectConfigs.getProjects()
                .get(0)
                .getInitDefaultBranch());


        // Unit tests for second project in git.yaml
        assertNotNull(projectConfigs.getProjects()
                .get(1)
                .getRepo());
        assertEquals("https://github.com/WHK01/whk-distillery01-ignition-global.git", projectConfigs.getProjects()
                .get(1)
                .getRepo()
                .getUri());
        assertEquals("development", projectConfigs.getProjects()
                .get(1)
                .getRepo()
                .getBranch());
        assertEquals("WHK-MES", projectConfigs.getProjects()
                .get(1)
                .getIgnition()
                .getProjectName());
        assertEquals("admin", projectConfigs.getProjects()
                .get(1)
                .getIgnition()
                .getUserName());
        assertFalse(projectConfigs.getProjects()
                .get(1)
                .getIgnition()
                .isInheritable());
        assertEquals("Global", projectConfigs.getProjects()
                .get(1)
                .getIgnition()
                .getParentName());
        assertEquals("pmannion2", projectConfigs.getProjects()
                .get(1)
                .getUser()
                .getName());
        assertEquals("pmannion@whiskeyhouse.com", projectConfigs.getProjects()
                .get(1)
                .getUser()
                .getName());
        assertEquals("abc123", projectConfigs.getProjects()
                .get(1)
                .getUser()
                .getName());
        assertTrue(projectConfigs.getProjects()
                .get(1)
                .getCommissioning()
                .isImportThemes());
        assertTrue(projectConfigs.getProjects()
                .get(1)
                .getCommissioning()
                .isImportTags());
        assertTrue(projectConfigs.getProjects()
                .get(1)
                .getCommissioning()
                .isImportImages());
        assertEquals("main",projectConfigs.getProjects()
                .get(1)
                .getInitDefaultBranch());
    }
}
