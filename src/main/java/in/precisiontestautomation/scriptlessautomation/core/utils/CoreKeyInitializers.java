package in.precisiontestautomation.scriptlessautomation.core.utils;

import lombok.Getter;

/**
 * @author PTA-dev
 */
public class CoreKeyInitializers {

    @Getter
    private static ThreadLocal<AutomationAsserts> customSoftAssert = new ThreadLocal<>();
    @Getter
    private static final ThreadLocal<String> testManagementRunUrl = new ThreadLocal<>();

}
