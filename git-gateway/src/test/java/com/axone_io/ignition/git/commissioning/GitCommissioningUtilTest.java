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

        Yaml yaml = new Yaml(new Constructor(ProjectConfig.class, new LoaderOptions()));
        InputStream inputStream = this.getClass()
                .getClassLoader()
                .getResourceAsStream("yaml/customer_with_contact_details_and_address.yaml");
        ProjectConfig projectConfig = yaml.load(inputStream);

        assertNotNull(projectConfig);
        assertEquals("John", projectConfig.getFirstName());
        assertEquals("Doe", projectConfig.getLastName());
        assertEquals(31, projectConfig.getAge());
        assertNotNull(projectConfig.getContactDetails());
        assertEquals(2, projectConfig.getContactDetails().size());

        assertEquals("mobile", projectConfig.getContactDetails()
                .get(0)
                .getType());
        assertEquals(123456789, projectConfig.getContactDetails()
                .get(0)
                .getNumber());
        assertEquals("landline", projectConfig.getContactDetails()
                .get(1)
                .getType());
        assertEquals(456786868, projectConfig.getContactDetails()
                .get(1)
                .getNumber());
        assertNotNull(projectConfig.getHomeAddress());
        assertEquals("Xyz, DEF Street", projectConfig.getHomeAddress()
                .getLine());
    }
}
