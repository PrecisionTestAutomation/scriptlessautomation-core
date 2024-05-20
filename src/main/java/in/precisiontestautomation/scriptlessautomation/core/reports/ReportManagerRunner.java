package in.precisiontestautomation.scriptlessautomation.core.reports;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>ReportManagerRunner class.</p>
 *
 * @author PTA-dev
 * @version $Id: $Id
 * @since 2024-05-02
 */
public class ReportManagerRunner {

    static ThreadLocal<Map<Integer, ExtentTest>> reportTestMap = new ThreadLocal<>();
    static ThreadLocal<ExtentReports> extentReports = ThreadLocal.withInitial(()->ReportManager.reports);
    private static ThreadLocal<ExtentTest> test = new ThreadLocal<>();

    /**
     * to generate extentreport
     */
    public static synchronized void endTest() {
        extentReports.get().flush();
    }

    /**
     * To start extentreport
     *
     * @param testName a {@link String} object
     * @return ExtentTest
     */
    public static synchronized ExtentTest startTest(String testName) {
        test.set(extentReports.get().createTest(testName));
        Map<Integer,ExtentTest> map = new HashMap<>();
        map.put((int) (Thread.currentThread().getId()), test.get());
        reportTestMap.set(map);
        return test.get();
    }

    /**
     * To get extentreport
     *
     * @return ExtentTest
     */
    public static synchronized ExtentTest getTest() {
        return reportTestMap.get().get((int) (Thread.currentThread().getId()));
    }

    /**
     * To set Report categories
     *
     * @param suitName a {@link String} object
     */
    public static void setReportCategories(String suitName){
        getTest().assignCategory(suitName);
    }

    /**
     * <p>getExtentTestngStatus.</p>
     *
     * @return a {@link String} object
     */
    public static String getExtentTestngStatus() {
        return test.get().getStatus().getName();
    }
}
