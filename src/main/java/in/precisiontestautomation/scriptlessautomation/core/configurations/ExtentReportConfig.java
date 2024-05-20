package in.precisiontestautomation.scriptlessautomation.core.configurations;


import in.precisiontestautomation.scriptlessautomation.core.utils.CoreFrameworkActions;

import java.io.File;
import java.util.Objects;

/**
 * ExtentReportConfig serves as a centralized repository for managing configurations related to Extent Reports used in automated testing.
 * This class retrieves configuration settings either from system properties set during runtime or from a properties file. This approach
 * allows for dynamic configuration adjustments without code changes. The configurations include report name, theme, custom JavaScript,
 * CSS settings, and more.
 *
 * @author PTA-dev
 * @version $Id: $Id
 * @since 2024-05-02
 */
public class ExtentReportConfig {

    private static final String CONFIGURATION_FILE_NAME = "reportConfiguration"+ File.separator+ "extentReportConfiguration.properties";

    /** Constant <code>ReportName="Objects.isNull(FrameworkActions.getProp"{trunked}</code> */
    public static String ReportName = Objects.isNull(CoreFrameworkActions.getProperty("ReportName")) ?
            ConfigReader.getConfigValue(CONFIGURATION_FILE_NAME,"ReportName") : CoreFrameworkActions.getProperty("ReportName");

    /** Constant <code>Report_Theme="Objects.isNull(FrameworkActions.getProp"{trunked}</code> */
    public static String Report_Theme = Objects.isNull(CoreFrameworkActions.getProperty("Report_Theme")) ?
            ConfigReader.getConfigValue(CONFIGURATION_FILE_NAME,"Report_Theme") : CoreFrameworkActions.getProperty("Report_Theme");

    /** Constant <code>Report_JS="Objects.isNull(FrameworkActions.getProp"{trunked}</code> */
    public static String Report_JS = Objects.isNull(CoreFrameworkActions.getProperty("Report_JS")) ?
            ConfigReader.getConfigValue(CONFIGURATION_FILE_NAME,"Report_JS") : CoreFrameworkActions.getProperty("Report_JS");

    /** Constant <code>Report_CSS="Objects.isNull(FrameworkActions.getProp"{trunked}</code> */
    public static String Report_CSS = Objects.isNull(CoreFrameworkActions.getProperty("Report_CSS")) ?
            ConfigReader.getConfigValue(CONFIGURATION_FILE_NAME,"Report_CSS") : CoreFrameworkActions.getProperty("Report_CSS");

    /** Constant <code>Report_logo="Objects.isNull(FrameworkActions.getProp"{trunked}</code> */
    public static String Report_logo = Objects.isNull(CoreFrameworkActions.getProperty("Report_logo")) ?
            ConfigReader.getConfigValue(CONFIGURATION_FILE_NAME,"Report_logo") : CoreFrameworkActions.getProperty("Report_logo");

    /** Constant <code>Report_captureScreenshotOnPass="Objects.isNull(FrameworkActions.getProp"{trunked}</code> */
    public static String Report_captureScreenshotOnPass = Objects.isNull(CoreFrameworkActions.getProperty("Report_captureScreenshotOnPass")) ?
            ConfigReader.getConfigValue(CONFIGURATION_FILE_NAME,"Report_captureScreenshotOnPass") : CoreFrameworkActions.getProperty("Report_captureScreenshotOnPass");
}
