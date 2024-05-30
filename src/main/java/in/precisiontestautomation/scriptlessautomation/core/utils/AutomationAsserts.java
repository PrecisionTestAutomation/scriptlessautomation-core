package in.precisiontestautomation.scriptlessautomation.core.utils;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;

import java.util.Objects;


/**
 * <p>AutomationAsserts class.</p>
 *
 * @author PTA-dev
 * @version $Id: $Id
 * @since 2024-05-02
 */
public class AutomationAsserts {

    public final ExtentTest test;

    /**
     * <p>Constructor for AutomationAsserts.</p>
     *
     * @param test a {@link ExtentTest} object
     */
    public AutomationAsserts(ExtentTest test) {
        this.test = test;
    }

    /**
     * <p>assertEquals.</p>
     *
     * @param elementName a {@link String} object
     * @param actual a {@link String} object
     * @param expected a {@link String} object
     */
    public void assertEquals(String elementName, String actual, String expected, boolean screenShotCapture, String imageBase64) {
        boolean stringsMatch = actual.replaceAll("\n", " ").equals(expected);
        test.log(stringsMatch ? Status.PASS : Status.FAIL,
                stringsMatch ? elementName + " -> Actual:<b><i>" + actual + "</i></b> match with Expected:<b><i>" + expected + "</i></b>" : elementName + " -> Actual:<i><b>" + actual + "</i></b> doesn't match with Expected:<i><b>" + expected + "</i></b>",
                screenShotCapture ? MediaEntityBuilder.createScreenCaptureFromBase64String(Objects.requireNonNull(imageBase64)).build() : null);
    }

    /**
     * <p>assertTrue.</p>
     *
     * @param elementName a {@link String} object
     * @param trueCondition a boolean
     * @param successMessage a {@link String} object
     * @param failureMessage a {@link String} object
     */
    public void assertTrue(String elementName, boolean trueCondition, String successMessage, String failureMessage,boolean screenShotCapture, String imageBase64) {
        test.log(trueCondition ? Status.PASS : Status.FAIL,
                trueCondition ? elementName + " -> " + successMessage : elementName + " -> " + failureMessage,
                screenShotCapture ? MediaEntityBuilder.createScreenCaptureFromBase64String(Objects.requireNonNull(imageBase64)).build() : null);
    }

    /**
     * <p>info.</p>
     *
     * @param message a {@link String} object
     */
    public void info(String message) {
        test.log(Status.INFO, message);
    }

    /**
     * <p>assertNotEquals.</p>
     *
     * @param elementName a {@link String} object
     * @param actual a {@link String} object
     * @param expected a {@link String} object
     */
    public void assertNotEquals(String elementName, String actual, String expected,boolean screenShotCapture, String imageBase64) {
        boolean stringsMatch = !actual.replaceAll("\n", " ").equals(expected);
        test.log(stringsMatch ? Status.PASS : Status.FAIL,
                stringsMatch ? elementName + " -> Actual:<b><i>" + actual + "</i></b> match with Expected:<b><i>" + expected + "</i></b>" : elementName + " -> Actual:<i><b>" + actual + "</i></b> doesn't match with Expected:<i><b>" + expected + "</i></b>",
                screenShotCapture ? MediaEntityBuilder.createScreenCaptureFromBase64String(Objects.requireNonNull(imageBase64)).build() : null);
    }

    /**
     * <p>assertEqualsIgnore.</p>
     *
     * @param elementName a {@link String} object
     * @param actual a {@link String} object
     * @param expected a {@link String} object
     */
    public void assertEqualsIgnore(String elementName, String actual, String expected,boolean screenShotCapture, String imageBase64) {
        boolean stringsMatch = actual.replaceAll("\n", " ").equalsIgnoreCase(expected);
        test.log(stringsMatch ? Status.PASS : Status.FAIL,
                stringsMatch ? elementName + " -> Actual:<b><i>" + actual + "</i></b> match with Expected:<b><i>" + expected + "</i></b>" : elementName + " -> Actual:<i><b>" + actual + "</i></b> doesn't match with Expected:<i><b>" + expected + "</i></b>",
                screenShotCapture ? MediaEntityBuilder.createScreenCaptureFromBase64String(Objects.requireNonNull(imageBase64)).build() : null);
    }

    /**
     * <p>assertContains.</p>
     *
     * @param elementName a {@link String} object
     * @param actual a {@link String} object
     * @param expected a {@link String} object
     */
    public void assertContains(String elementName, String actual, String expected,boolean screenShotCapture, String imageBase64) {
        boolean stringsMatch = actual.replaceAll("\n", " ").contains(expected);
        test.log(stringsMatch ? Status.PASS : Status.FAIL,
                stringsMatch ? elementName + " -> Actual:<b><i>" + actual + "</i></b> contains with Expected:<b><i>" + expected + "</i></b>" : elementName + " -> Actual:<i><b>" + actual + "</i></b> doesn't contains with Expected:<i><b>" + expected + "</i></b>",
                screenShotCapture ? MediaEntityBuilder.createScreenCaptureFromBase64String(Objects.requireNonNull(imageBase64)).build() : null);
    }


    public void assertEquals(String elementName, Object actual, Object expected,boolean screenShotCapture, String imageBase64) {
        boolean stringsMatch = actual.equals(expected);
        test.log(stringsMatch ? Status.PASS : Status.FAIL,
                stringsMatch ? elementName + " -> Actual:<b><i>" + actual + "</i></b> match with Expected:<b><i>" + expected + "</i></b>" :
                        elementName + " -> Actual:<i><b>" + actual + "</i></b> doesn't match with Expected:<i><b>" + expected + "</i></b>",
                screenShotCapture ? MediaEntityBuilder.createScreenCaptureFromBase64String(Objects.requireNonNull(imageBase64)).build() : null);
    }

    public void assertFalse(String elementName, boolean trueCondition, String successMessage, String failureMessage,boolean screenShotCapture, String imageBase64) {
        test.log(!trueCondition ? Status.PASS : Status.FAIL,
                !trueCondition ? elementName + " -> " + successMessage : elementName + " -> " + failureMessage,
                screenShotCapture ? MediaEntityBuilder.createScreenCaptureFromBase64String(Objects.requireNonNull(imageBase64)).build() : null);
    }


    public void assertEquals(String elementName, Object actual, Object expected) {
        boolean stringsMatch = actual.equals(expected);
        test.log(stringsMatch ? Status.PASS : Status.FAIL,
                stringsMatch ? elementName + " -> Actual:<b><i>" + actual + "</i></b> match with Expected:<b><i>" + expected + "</i></b>" :
                        elementName + " -> Actual:<i><b>" + actual + "</i></b> doesn't match with Expected:<i><b>" + expected + "</i></b>");
    }
}
