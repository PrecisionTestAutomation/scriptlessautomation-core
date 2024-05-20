package in.precisiontestautomation.scriptlessautomation.core.configurations;

import in.precisiontestautomation.scriptlessautomation.core.utils.CoreFrameworkActions;

import java.util.Objects;

/**
 * TestNgConfig is a utility class that loads and provides configuration settings for TestNG tests. It handles the retrieval
 * of configuration values from either system properties or a properties file specifically for testing configurations.
 * This class is crucial for configuring aspects like thread count, platform, browser settings, retry mechanisms, and other
 * test execution parameters. The flexibility to override these settings at runtime or through a properties file allows for
 * dynamic test execution in different environments.
 *
 * @author PTA-dev
 * @version $Id: $Id
 * @since 2024-05-02
 */
public class TestNgConfig {

    private static final String CONFIGURATION_FILE_NAME = "testNgConfiguration.properties";

    /** Constant <code>ThreadCount=Integer.parseInt(FrameworkActions.getProperty(&quot;ThreadCount&quot;) == null
            ? ConfigReader.getConfigValue(CONFIGURATION_FILE_NAME,&quot;ThreadCount&quot;)
            : Objects.requireNonNull(FrameworkActions.getProperty(&quot;ThreadCount&quot;)))</code> */
    public static final int ThreadCount = Integer.parseInt(CoreFrameworkActions.getProperty("ThreadCount") == null
            ? ConfigReader.getConfigValue(CONFIGURATION_FILE_NAME,"ThreadCount")
            : Objects.requireNonNull(CoreFrameworkActions.getProperty("ThreadCount")));

    /** Constant <code>PLATFORM="Objects.isNull(FrameworkActions.getProp"{trunked}</code> */
    public static String PLATFORM = Objects.isNull(CoreFrameworkActions.getProperty("Platform")) ?
            ConfigReader.getConfigValue(CONFIGURATION_FILE_NAME,"Platform") : CoreFrameworkActions.getProperty("Platform");

    /** Constant <code>BROWSER="Objects.isNull(FrameworkActions.getProp"{trunked}</code> */
    public static final String BROWSER = Objects.isNull(CoreFrameworkActions.getProperty("Browser")) ?
            ConfigReader.getConfigValue(CONFIGURATION_FILE_NAME,"Browser") : CoreFrameworkActions.getProperty("Browser");

    /** Constant <code>MAX_RETRY_CNT=Integer.parseInt(!Objects.isNull(FrameworkActions.getProperty(&quot;FAILED_RETRY_COUNT&quot;)) ?
            Objects.requireNonNull(FrameworkActions.getProperty(&quot;FAILED_RETRY_COUNT&quot;)) :
            ConfigReader.getConfigValue(CONFIGURATION_FILE_NAME,&quot;FAILED_RETRY_COUNT&quot;))</code> */
    public static final int MAX_RETRY_CNT = Integer.parseInt(!Objects.isNull(CoreFrameworkActions.getProperty("FAILED_RETRY_COUNT")) ?
            Objects.requireNonNull(CoreFrameworkActions.getProperty("FAILED_RETRY_COUNT")) :
            ConfigReader.getConfigValue(CONFIGURATION_FILE_NAME,"FAILED_RETRY_COUNT"));

    /** Constant <code>ENV="!Objects.isNull(FrameworkActions.getPro"{trunked}</code> */
    public static final String ENV = !Objects.isNull(CoreFrameworkActions.getProperty("Env")) ?
            Objects.requireNonNull(CoreFrameworkActions.getProperty("Env")) :
            ConfigReader.getConfigValue(CONFIGURATION_FILE_NAME,"Env");

    /** Constant <code>SET_TEST_SUITE_NAME="Objects.isNull(FrameworkActions.getProp"{trunked}</code> */
    public static final String SET_TEST_SUITE_NAME = Objects.isNull(CoreFrameworkActions.getProperty("SET_TEST_SUITE_NAME")) ?
            ConfigReader.getConfigValue(CONFIGURATION_FILE_NAME,"SET_TEST_SUITE_NAME") : CoreFrameworkActions.getProperty("SET_TEST_SUITE_NAME");
    /** Constant <code>SET_TEST_NAME="Objects.isNull(FrameworkActions.getProp"{trunked}</code> */
    public static final String SET_TEST_NAME = Objects.isNull(CoreFrameworkActions.getProperty("SET_TEST_NAME")) ?
            ConfigReader.getConfigValue(CONFIGURATION_FILE_NAME,"SET_TEST_NAME") : CoreFrameworkActions.getProperty("SET_TEST_NAME");

    /** Constant <code>TEST_IDS="Objects.isNull(FrameworkActions.getProp"{trunked}</code> */
    public static final String TEST_IDS = Objects.isNull(CoreFrameworkActions.getProperty("TEST_IDS")) ?
            ConfigReader.getConfigValue(CONFIGURATION_FILE_NAME, "TEST_IDS") : CoreFrameworkActions.getProperty("TEST_IDS");
    /** Constant <code>DISABLE_TEST_IDS="Objects.isNull(FrameworkActions.getProp"{trunked}</code> */
    public static final String DISABLE_TEST_IDS = Objects.isNull(CoreFrameworkActions.getProperty("DISABLE_TEST_IDS")) ?
            ConfigReader.getConfigValue(CONFIGURATION_FILE_NAME, "DISABLE_TEST_IDS") : CoreFrameworkActions.getProperty("DISABLE_TEST_IDS");

    /** Constant <code>GROUPS="Objects.isNull(FrameworkActions.getProp"{trunked}</code> */
    public static final String GROUPS = Objects.isNull(CoreFrameworkActions.getProperty("GROUPS")) ?
            ConfigReader.getConfigValue(CONFIGURATION_FILE_NAME, "GROUPS") : CoreFrameworkActions.getProperty("GROUPS");

    /** Constant <code>TEST_DATA_SECTIONS="Objects.isNull(FrameworkActions.getProp"{trunked}</code> */
    public static final String TEST_DATA_SECTIONS = Objects.isNull(CoreFrameworkActions.getProperty("TEST_DATA_SECTIONS")) ?
            ConfigReader.getConfigValue(CONFIGURATION_FILE_NAME, "TEST_DATA_SECTIONS") : CoreFrameworkActions.getProperty("TEST_DATA_SECTIONS");
}
