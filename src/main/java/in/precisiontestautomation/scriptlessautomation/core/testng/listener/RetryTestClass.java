package in.precisiontestautomation.scriptlessautomation.core.testng.listener;

import in.precisiontestautomation.scriptlessautomation.core.configurations.TestNgConfig;
import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

/**
 * <p>RetryTestClass class.</p>
 *
 * @author PTA-dev
 * @version $Id: $Id
 * @since 2024-05-02
 */
public class RetryTestClass implements IRetryAnalyzer {

    private int retryCnt = 0;

    /**
     * {@inheritDoc}
     *
     * Retry failed test case : method leveel
     */
    public boolean retry(ITestResult result) {
        if (retryCnt < TestNgConfig.MAX_RETRY_CNT) {
            //System.out.println("Retrying " + result.getName() + " again and the count is " + (retryCnt+1));
            result.setStatus(3);
            retryCnt++;
            return true;
        }
        return false;
    }

}
