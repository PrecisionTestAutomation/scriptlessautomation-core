package in.precisiontestautomation.scriptlessautomation.core.testng.listener;

import com.aventstack.extentreports.Status;
import in.precisiontestautomation.scriptlessautomation.core.reports.ReportManager;
import in.precisiontestautomation.scriptlessautomation.core.reports.ReportManagerRunner;
import in.precisiontestautomation.scriptlessautomation.core.utils.AutomationAsserts;
import in.precisiontestautomation.scriptlessautomation.core.utils.CoreKeyInitializers;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

/**
 * <p>TestNgListener class.</p>
 *
 * @author PTA-dev
 * @version $Id: $Id
 * @since 2024-05-02
 */
public class TestNgListener implements ITestListener {

    private final ThreadLocal<String> testName = new ThreadLocal<>();

    /**
     * {@inheritDoc}
     */
    public void onTestStart(ITestResult result) {
        testName.set(result.getTestName());
        if (!testName.get().isEmpty()) {
            ReportManagerRunner.startTest(testName.get());
        } else {
            ReportManagerRunner.startTest(result.getMethod().getDescription() + " " + testName.get());
            ReportManagerRunner.getTest().log(Status.INFO, "Test Case " + testName.get() + " " + result.getMethod().getDescription());
        }
        CoreKeyInitializers.getCustomSoftAssert().set(new AutomationAsserts(ReportManagerRunner.getTest()));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onTestSuccess(ITestResult result) {
        if (ReportManagerRunner.getExtentTestngStatus().equals(Status.FAIL.getName())) {
            result.setStatus(ITestResult.FAILURE);
            ReportManagerRunner.getTest().log(Status.PASS, "Execution is Failed");
        } else {
            ReportManagerRunner.getTest().log(Status.PASS, "Execution is Passed");
        }
    }

    /**
     * {@inheritDoc}
     */
    public void onTestFailure(ITestResult result) {
        ReportManagerRunner.getTest().log(Status.FAIL, "Execution is Failed");
        ReportManagerRunner.getTest().log(Status.FAIL, result.getThrowable());
    }

    /**
     * {@inheritDoc}
     */
    public void onTestSkipped(ITestResult result) {
        ReportManagerRunner.getTest().log(Status.SKIP, "Execution is Skipped");
        ReportManagerRunner.getTest().log(Status.SKIP, result.getThrowable());
    }

    /**
     * {@inheritDoc}
     */
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        //TODO
    }

    /**
     * {@inheritDoc}
     */
    public void onStart(ITestContext iTestContext) {
        //TODO
    }

    /**
     * {@inheritDoc}
     */
    public void onFinish(ITestContext context) {
        ReportManagerRunner.endTest();
        ReportManager.logoIntegration();
    }
}
