package com.axone_io.ignition.git.commissioning;

import org.junit.Test;
import org.yaml.snakeyaml.LoaderOptions;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;

import java.io.InputStream;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class GitCommissioningUtilTest {
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
    }
}
