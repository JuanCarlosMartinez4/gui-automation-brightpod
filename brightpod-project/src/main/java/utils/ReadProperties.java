package utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class ReadProperties {
    public static Properties propertiesFileReader(final String fileName) throws IOException {
        FileInputStream inputStream = null;
        Properties properties = null;
        try {
            inputStream = new FileInputStream(fileName);
            properties = new Properties();
            properties.load(inputStream);
        } catch (FileNotFoundException fnfe) {
            fnfe.printStackTrace();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        } finally {
            inputStream.close();
        }
        return properties;
    }
}
