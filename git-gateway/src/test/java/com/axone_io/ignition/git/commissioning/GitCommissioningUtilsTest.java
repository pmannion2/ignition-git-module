package com.axone_io.ignition.git.commissioning;

import org.junit.Test;
import org.yaml.snakeyaml.LoaderOptions;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;

import java.io.InputStream;

import static org.junit.Assert.*;

public class GitCommissioningUtilsTest {
    @Test
    public void
    whenLoadYAMLDocumentWithTopLevelClass_thenLoadCorrectJavaObjectWithNestedObjects() {

        Yaml yaml = new Yaml(new Constructor(ProjectConfigs.class, new LoaderOptions()));
        InputStream inputStream = this.getClass()
                .getClassLoader()
                .getResourceAsStream("git.yaml");
        ProjectConfigs projectConfigs = yaml.load(inputStream);

        assertNotNull(projectConfigs);
        assertNotNull(projectConfigs.getProjects());
        assertEquals(2, projectConfigs.getProjects()
                .size());
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
    }
}
