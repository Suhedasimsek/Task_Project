package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {
    private Properties properties;

    //This is used to raed from properties files and returns properties object
    public Properties initProp() {
        properties = new Properties();
        try {
            FileInputStream fileInputStream = new FileInputStream("./src/test/resources/config/config.properties");
            properties.load(fileInputStream);
        } catch (IOException e) {
            System.err.println("Unable to read Properties file: " + e.getMessage());
            e.printStackTrace();
        }

        return properties;
    }
}