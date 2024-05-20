package in.precisiontestautomation.scriptlessautomation.core.configurations;

import in.precisiontestautomation.scriptlessautomation.core.exceptionhandling.PrecisionTestException;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

/**
 * ConfigReader is a utility class designed to manage the reading of configuration files.
 * It provides a central mechanism to load and retrieve properties from configuration files located in the "config" directory.
 * This class is commonly used in applications where configurations need to be externalized and loaded dynamically.
 *
 * The configurations are expected to be in standard properties file format and located under a 'config' directory
 * in the project's working directory.
 *
 * @author PTA-dev
 * @version $Id: $Id
 * @since 2024-05-02
 */
public class ConfigReader {

    private static final String CONFIGURATION_PATH = System.getProperty("user.dir")+ File.separator+"config";
    private static final Properties prop = new Properties();

    /**
     * Initializes and loads a configuration file into the Properties object.
     * This method opens the specified configuration file, reads its content, and loads it into the Properties object.
     *
     * @param filepath The complete path to the configuration file.
     * @return A Properties object containing all configuration properties from the file.
     * @throws PrecisionTestException if the file is not found or if an IOException occurs during file reading.
     */
    private static Properties initConfig(String filepath) {
        try(FileInputStream fis = new FileInputStream(filepath)){
            prop.load(fis);
        } catch (FileNotFoundException e) {
            throw new PrecisionTestException(filepath + " File not found");
        } catch (IOException e) {
            throw new PrecisionTestException("Failed on parsing the File "+filepath+" "+e.getStackTrace().toString());
        }

        return prop;
    }

    /**
     * Retrieves the value of a specific configuration property by key from the specified configuration file.
     * If the configuration file has not been loaded yet, it triggers the loading process.
     *
     * @param fileName The name of the configuration file (located within the configuration path directory).
     * @param key The key whose value is to be retrieved.
     * @return The value associated with the specified key in the given configuration file.
     */
    public static String getConfigValue(String fileName,String key){
        return initConfig(CONFIGURATION_PATH+File.separator+fileName).getProperty(key);
    }
}
