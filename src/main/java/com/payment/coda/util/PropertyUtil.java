package com.payment.coda.util;

import com.payment.coda.exceptions.InvalidArgumentException;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;

/**
 * @author Kunal Malhotra
 */
public class PropertyUtil {

    private PropertyUtil() {
    }

    private static final Logger log = LogManager.getLogger(PropertyUtil.class);

    /**
     * Reads the properties from config.properties File
     *
     * @param propertyKey
     * @return
     * @throws InvalidArgumentException
     */
    public static String getPropertyValue(String propertyKey) throws InvalidArgumentException {
        Path resourceDirectory = Paths.get("src", "main", "resources");

        String absolutePath = resourceDirectory.toFile().getAbsolutePath() + "" + File.separator + "config.properties";

        try (InputStream input = new FileInputStream(absolutePath)) {

            Properties prop = new Properties();
            // load a properties file
            prop.load(input);
            String value = prop.getProperty(propertyKey);
            if (value == null || value.length() < 1) {
                throw new InvalidArgumentException("Property Key Is Invalid:" + propertyKey);
            }
            return value;
        } catch (IOException ex) {
            log.error(ex);
            throw new InvalidArgumentException("Property Key Is Invalid:" + propertyKey);
        }
    }
}
