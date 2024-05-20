package in.precisiontestautomation.scriptlessautomation.core.testng.xmlgenerator;

import in.precisiontestautomation.scriptlessautomation.core.configurations.TestNgConfig;
import org.testng.TestNG;
import org.testng.xml.XmlClass;
import org.testng.xml.XmlSuite;
import org.testng.xml.XmlTest;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

/**
 * <p>GenerateTestNg class.</p>
 *
 * @author PTA-dev
 * @version $Id: $Id
 * @since 2024-05-02
 */
public class GenerateTestNg {

    private static final String PROJECT_DIRECTORY = System.getProperty("user.dir");
    private static final String TEST_FLOW = PROJECT_DIRECTORY + File.separator + "test_data" +File.separator+ "%s"+File.separator+"test_case_flows";
    private static final String RETRY_ANNOTATION_CLASS = "in.precisiontestautomation.scriptlessautomation.core.testng.listener.RetryAnnotationClass";
    private static final String TESTNG_LISTENER = "in.precisiontestautomation.scriptlessautomation.core.testng.listener.TestNgListener";
    private static final String RUNNER_PACKAGE = "in.precisiontestautomation.tests.";

    private void runTestNgTest(Map<String, String> testngParams, String platform, boolean pomRunner) {
        List<String> listenerPackages = new ArrayList<>();
        listenerPackages.add(RETRY_ANNOTATION_CLASS);
        listenerPackages.add(TESTNG_LISTENER);

        TestNG myTestNg = new TestNG();

        XmlSuite xmlSuite = new XmlSuite();
        xmlSuite.setName(TestNgConfig.SET_TEST_SUITE_NAME);
        xmlSuite.setListeners(listenerPackages);
        xmlSuite.setThreadCount(0);
        xmlSuite.setDataProviderThreadCount(TestNgConfig.ThreadCount);

        XmlTest map = new XmlTest(xmlSuite);
        List<XmlClass> xmlClassList = new ArrayList<>();
        xmlClassList.add(new XmlClass(RUNNER_PACKAGE+ platform.toUpperCase()));
        map.setParameters(testngParams);
        map.setName(TestNgConfig.SET_TEST_NAME);
        map.setXmlClasses(xmlClassList);

        List<XmlSuite> mySuites = new ArrayList<>();
        mySuites.add(xmlSuite);

        myTestNg.setXmlSuites(mySuites);


        if (!pomRunner) {
            myTestNg.run();
        }

        for (XmlSuite suite : mySuites) {
            createXmlFiles(suite);
        }
    }

    private void createXmlFiles(XmlSuite xmlSuite) {
        String testNgFilePath = PROJECT_DIRECTORY + File.separator + "target" + File.separator + "testngenerator.xml";
        try (FileWriter writer = new FileWriter(testNgFilePath)) {
            writer.write(xmlSuite.toXml());
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * <p>collectTestData.</p>
     *
     * @param args an array of {@link String} objects
     */
    public void collectTestData(String[] args,String platform) {
        boolean pomEnable = (args.length != 0) && Boolean.parseBoolean(args[0]);

        Map<String, String> folderDataMap = new HashMap<>();
        folderDataMap.put("Platform", platform);
        File[] directories = new File(String.format(TEST_FLOW, platform.toLowerCase())).listFiles();

        try {
            Arrays.stream(directories).forEach(directory -> {
                String fileName = directory.getAbsoluteFile().getName();
                String filePath = directory.getAbsoluteFile().getPath();
                if (!fileName.startsWith(".DS")) {
                    folderDataMap.put(fileName, filePath);
                }
            });
        }catch (NullPointerException exception){
            System.err.println("Error: Test data is not available in the current workspace. " +
                    "\nExpected location: " + String.format(TEST_FLOW, platform.toLowerCase())+".....");
            System.exit(1);
        }
        runTestNgTest(folderDataMap, platform, pomEnable);
    }
}
