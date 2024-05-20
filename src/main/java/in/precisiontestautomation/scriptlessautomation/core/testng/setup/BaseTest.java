package in.precisiontestautomation.scriptlessautomation.core.testng.setup;

import in.precisiontestautomation.scriptlessautomation.core.reports.ReportManager;
import in.precisiontestautomation.scriptlessautomation.core.reports.ReportManagerRunner;
import in.precisiontestautomation.scriptlessautomation.core.testng.listener.TestNgListener;

import in.precisiontestautomation.scriptlessautomation.core.utils.CoreKeyInitializers;
import org.testng.ITest;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.io.File;
import java.lang.reflect.Method;

/**
 * <p>BaseTest class.</p>
 *
 * @author PTA-dev
 * @version $Id: $Id
 * @since 2024-05-02
 */
@Listeners({TestNgListener.class})
public class BaseTest implements ITest {
    private ThreadLocal<String> testName = new ThreadLocal<>();

    /**
     * <p>beforeSuite.</p>
     */
    @BeforeSuite(alwaysRun = true)
    public void beforeSuite(){
        ReportManager.getInstance();
    }

    /**
     * <p>beforeTest.</p>
     *
     * @param ctx a {@link ITestContext} object
     */
    @BeforeTest(alwaysRun = true)
    public void beforeTest(ITestContext ctx) {
        System.out.println("----------------------------------INSIDE BEFORE TEST----------------------------------");
    }

    /**
     * <p>afterMethod.</p>
     *
     * @param result a {@link ITestResult} object
     */
    @AfterMethod(alwaysRun = true)
    public void afterMethod(ITestResult result){
        ReportManagerRunner.setReportCategories(result.getAttribute("suiteName").toString());
        CoreKeyInitializers.getCustomSoftAssert().remove();
    }

    /** {@inheritDoc} */
    @Override
    public String getTestName(){
        return testName.get();
    }

    /**
     * <p>beforeMethod.</p>
     *
     * @param method a {@link Method} object
     * @param context a {@link ITestContext} object
     * @param testData an array of {@link Object} objects
     */
    @BeforeMethod(alwaysRun = true)
    public void beforeMethod(Method method, ITestContext context, Object[] testData){
        if(testData.length > 0){
            testName.set(new File(testData[0].toString()).getName().split("_")[0]);
            context.setAttribute("testName",testName.get());
        } else {
            context.setAttribute("testName",method.getName());
        }
    }
}
