package org.test.covid_api.domain;


import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Objects;
import java.util.Properties;

import static org.junit.jupiter.api.Assertions.fail;

public class TestConfig {
    private static Properties props = getProperties();
    public static String BASE_URI = Objects.requireNonNull(props).getProperty("baseURI");

    public static Properties getProperties() {
        try {
            File configFile = new File("config.properties");
            FileReader reader = new FileReader(configFile);
            Properties props = new Properties();
            props.load(reader);
            return props;
        } catch (IOException e) {
            e.printStackTrace();
            fail("There is no properties!");
        }
        return null;
    }
}
