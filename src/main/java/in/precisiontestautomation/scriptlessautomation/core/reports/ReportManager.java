package in.precisiontestautomation.scriptlessautomation.core.reports;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.aventstack.extentreports.reporter.configuration.ViewName;
import in.precisiontestautomation.scriptlessautomation.core.configurations.ExtentReportConfig;
import in.precisiontestautomation.scriptlessautomation.core.configurations.TestNgConfig;
import in.precisiontestautomation.scriptlessautomation.core.utils.ImageUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.*;
import java.util.Objects;

/**
 * <p>ReportManager class.</p>
 *
 * @author PTA-dev
 * @version $Id: $Id
 * @since 2024-05-02
 */
public class ReportManager {

    static ExtentReports reports;
    /** Constant <code>fileName="ExtentReportConfig.ReportName"</code> */
    public static String fileName = ExtentReportConfig.ReportName;
    /** Constant <code>filePath="System.getProperty(user.dir) + File.sep"{trunked}</code> */
    public static String filePath = System.getProperty("user.dir") + File.separator + "Reports";
    private static String reportLocation = filePath + File.separator + fileName + ".html";

    /**
     * <p>getInstance.</p>
     */
    public static synchronized void getInstance() {
        if (Objects.isNull(reports)) {
            createInstance();
        }
    }

    private static synchronized ExtentReports createInstance() {
        String path = getReportPath(filePath);

        ExtentSparkReporter extentHtmlReporter = new ExtentSparkReporter(path);
        extentHtmlReporter.viewConfigurer().viewOrder().as(new ViewName[]{ViewName.DASHBOARD, ViewName.CATEGORY, ViewName.TEST, ViewName.EXCEPTION});
        extentHtmlReporter.config().setTimelineEnabled(false);
        extentHtmlReporter.config().setTheme(Theme.valueOf(ExtentReportConfig.Report_Theme.toUpperCase()));
        extentHtmlReporter.config().setJs(ExtentReportConfig.Report_JS);
        extentHtmlReporter.config().setCss(ExtentReportConfig.Report_CSS);

        if (TestNgConfig.ENV != null)
            extentHtmlReporter.config().setReportName(ExtentReportConfig.ReportName + " - " + TestNgConfig.ENV.toUpperCase() + " Environment");

        reports = new ExtentReports();
        reports.attachReporter(extentHtmlReporter);

        if (System.getProperty("os.name").contains("Win")) {
            reports.setSystemInfo("Machine_OS", "Windows 10");
        } else {
            reports.setSystemInfo("Machine_OS", "Mac OSX");
        }

        return reports;

    }

    private synchronized static String getReportPath(String path) {
        File reportDirectory = new File(path);
        if (!reportDirectory.exists()) {
            if (reportDirectory.mkdirs()) {
                return reportLocation;
            } else {
                return System.getProperty("user.dir");
            }
        }
        return reportLocation;
    }

    /**
     * <p>setReportInfo.</p>
     *
     * @param key a {@link String} object
     * @param value a {@link String} object
     */
    public static synchronized void setReportInfo(String key, String value) {
        reports.setSystemInfo(key, value);
    }

    /**
     * <p>logoIntegration.</p>
     */
    public static void logoIntegration(){
        File file = new File(reportLocation);
        Document doc = null;
        try {
            doc = Jsoup.parse(file, "UTF-8", "");
        } catch (IOException e) {
            e.printStackTrace();
        }

        Element els = doc.head();
        String logoPath = ExtentReportConfig.Report_logo;
        try {
            els.html(els.html().replaceAll("https://cdn.jsdelivr.net/gh/extent-framework/extent-github-cdn@b00a2d0486596e73dd7326beacf352c639623a0e/commons/img/logo.png", ImageUtils.convertImageToBase64Url(System.getProperty("user.dir") + logoPath)));
        } catch (Exception e){
                e.printStackTrace();
        }

        Element body = doc.body();
        body.html(body.html().replaceAll("https://cdn.jsdelivr.net/gh/extent-framework/extent-github-cdn@b00a2d0486596e73dd7326beacf352c639623a0e/commons/img/logo.png", ImageUtils.convertImageToBase64Url(System.getProperty("user.dir")+ logoPath)));

        BufferedWriter htmlWriter = null;
        try {
            htmlWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(reportLocation), "UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        try {
            htmlWriter.write(doc.html());
            htmlWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
