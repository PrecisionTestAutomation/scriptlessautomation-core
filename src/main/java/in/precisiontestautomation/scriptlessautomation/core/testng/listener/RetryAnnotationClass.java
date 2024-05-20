package in.precisiontestautomation.scriptlessautomation.core.testng.listener;

import org.testng.IAnnotationTransformer;
import org.testng.annotations.ITestAnnotation;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

/**
 * <p>RetryAnnotationClass class.</p>
 *
 * @author PTA-dev
 * @version $Id: $Id
 * @since 2024-05-02
 */
public class RetryAnnotationClass implements IAnnotationTransformer {

    /**
     * {@inheritDoc}
     *
     * Set retry analyser on suit level
     */
    @SuppressWarnings("rawtypes")
    public void transform(ITestAnnotation annotation, Class testClass, Constructor testConstructor, Method testMethod) {
        annotation.setRetryAnalyzer(RetryTestClass.class);
    }
}
