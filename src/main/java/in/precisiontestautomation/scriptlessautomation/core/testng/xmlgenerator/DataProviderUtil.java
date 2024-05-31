package in.precisiontestautomation.scriptlessautomation.core.testng.xmlgenerator;

import in.precisiontestautomation.scriptlessautomation.core.configurations.TestNgConfig;
import in.precisiontestautomation.scriptlessautomation.core.exceptionhandling.PrecisionTestException;
import org.testng.ITestContext;
import org.testng.annotations.DataProvider;

import java.io.File;
import java.lang.reflect.Method;
import java.util.*;
import java.util.stream.Collectors;

/**
 * <p>DataProviderUtil class.</p>
 *
 * @author PTA-dev
 * @version $Id: $Id
 * @since 2024-05-02
 */
public class DataProviderUtil {

    private DataProviderUtil() {
    }

    /**
     * <p>getData.</p>
     *
     * @param context a {@link ITestContext} object
     * @param method a {@link Method} object
     * @return an array of {@link Object} objects
     */
    @DataProvider(name = "dataProvide", parallel = true)
    public static Object[][] getData(ITestContext context, Method method) {
        List<String> list = new ArrayList<>();

        String TEST_TRAIL_SECTIONS = (Objects.nonNull(TestNgConfig.TEST_DATA_SECTIONS) && !TestNgConfig.TEST_DATA_SECTIONS.isEmpty()) ? TestNgConfig.TEST_DATA_SECTIONS : "ALL";
        String GROUPS = (Objects.nonNull(TestNgConfig.GROUPS) && !TestNgConfig.GROUPS.isEmpty()) ? TestNgConfig.GROUPS : "ALL";

        Map<String, String> allParams = context.getCurrentXmlTest().getAllParameters();

        allParams.entrySet().stream()
                .filter(entry -> !entry.getKey().equalsIgnoreCase("platform"))
                .iterator()
                .forEachRemaining(param -> {
                    String fileDirectory = param.getValue();
                    File file = new File(fileDirectory);

                    List<String> sectionList = Arrays.asList(TEST_TRAIL_SECTIONS.toUpperCase().split(","));

                    if(sectionList.contains("ALL") || sectionList.contains(file.getName())) {
                        File[] directories = file.listFiles();
                        if (directories == null) {
                            throw new IllegalArgumentException("Please create directories under `test_case_flows` before adding test cases. Ensure each directory corresponds to a specific feature.");
                        }
                        Arrays.stream(directories)
                                .filter(e -> !e.getName().startsWith(".DS")) // Filter out .DS_Store files or similar
                                .filter(e -> e.isFile() && e.getName().toLowerCase().endsWith(".csv")) // Ensure it's a file and ends with .csv
                                .forEach(directory -> {
                                    // Add the absolute path of the file to the list
                                    list.add(directory.getAbsoluteFile().toString());
                                });
                    }
                });

        boolean all = !TestNgConfig.TEST_IDS.equalsIgnoreCase("ALL") && !TestNgConfig.TEST_IDS.isBlank() && !TestNgConfig.TEST_IDS.isEmpty();
        if (all) {
            list.retainAll(list.stream().filter(l -> {
                        String testRailId = new File(l).getName().split("_")[0];
                        return Arrays.asList(TestNgConfig.TEST_IDS.split(",")).contains(testRailId);
                    })
                    .collect(Collectors.toList()));
            if (list.isEmpty()) {
                throw new PrecisionTestException("Given TestRail Id/Ids either invalid or nor implemented in the automation");
            }
        }

        if ((TestNgConfig.TEST_IDS.split(",").length> 1 || !all) && !GROUPS.equalsIgnoreCase("all")) {
            list.retainAll(list.stream().parallel().filter(l -> {
                        String testCaseName = new File(l).getName();
                        String testCaseWithOutException = testCaseName.substring(0,testCaseName.indexOf("."));
                        List<String> groupsList = new ArrayList<>(Arrays.asList(TestNgConfig.GROUPS.toLowerCase().split(",")));
                        List<String> testCaseNameList = new ArrayList<>(Arrays.asList(testCaseWithOutException.toLowerCase().split("_")));
                        testCaseNameList.retainAll(groupsList);
                        return testCaseNameList.size()>0;
                    })
                    .collect(Collectors.toList()));
            if (list.isEmpty()) {
                throw new PrecisionTestException("Given TestRail Id/Ids either invalid or nor implemented in the automation");
            }
        }

        if (!TestNgConfig.DISABLE_TEST_IDS.isBlank() && !TestNgConfig.DISABLE_TEST_IDS.isEmpty()) {
            list.retainAll(list.stream().filter(l -> {
                        String testRailId = new File(l).getName().split("_")[0];
                        return !Arrays.asList(TestNgConfig.DISABLE_TEST_IDS.split(",")).contains(testRailId);
                    })
                    .collect(Collectors.toList()));
        }

        Object[][] data = new Object[list.size()][2];
        for (int i = 0; i < list.size(); i++) {
            data[i][0] = list.get(i);
            data[i][1] = true;
        }

        return data;
    }
}
