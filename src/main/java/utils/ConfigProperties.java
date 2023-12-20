package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Paths;
import java.util.Properties;

public class ConfigProperties {

    private static final Properties properties = new Properties();

    private static void loadProperties() {

        String propsPath = Paths.get("src", "main", "resources", "config.properties").toString();

        try ( InputStream input = new FileInputStream(propsPath) ) {
            properties.load(input);
        } catch (IOException e) {
            ExceptionLogger.logError("An error occurred at: utils/ConfigProperties", e);
        }
    }

    public static String getProperty(String name) {
        loadProperties();
        return properties.getProperty(name);
    }
}