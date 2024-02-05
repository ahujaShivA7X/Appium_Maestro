package Utils;

import com.sun.xml.internal.bind.v2.runtime.property.PropertyFactory;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Logger;

public class PropertyReader {

    private static Properties property = new Properties();

    public PropertyReader() {
        loadProperty();
    }

    public static Properties getProperty() {
        return property;
    }

    public static void loadProperty() {
        FileInputStream propertyPath;
        try {
            propertyPath = new FileInputStream("src/test/resources/config.properties");
            property.load(propertyPath);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to find the properties file");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("Unable to load the properties file");
            e.printStackTrace();
        }
    }
}

