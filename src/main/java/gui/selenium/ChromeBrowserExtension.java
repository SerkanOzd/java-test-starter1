package gui.selenium;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import org.junit.jupiter.api.extension.AfterTestExecutionCallback;
import org.junit.jupiter.api.extension.BeforeAllCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testcontainers.containers.BrowserWebDriverContainer;
import org.testcontainers.containers.DefaultRecordingFileFactory;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;
import io.qameta.allure.Attachment;
import static org.testcontainers.containers.BrowserWebDriverContainer.VncRecordingMode.RECORD_FAILING;
import static org.testcontainers.containers.VncRecordingContainer.VncRecordingFormat.MP4;

@Testcontainers
public class ChromeBrowserExtension implements BeforeAllCallback, AfterTestExecutionCallback {

    private final ChromeOptions chromeOptions = getChromeOptions();

    @Container
    private final BrowserWebDriverContainer chrome = new BrowserWebDriverContainer(getDockerImageName())
            .withCapabilities(chromeOptions)
            .withRecordingMode(RECORD_FAILING, new File("./target/"), MP4)
            .withRecordingFileFactory(new DefaultRecordingFileFactory());


    @Attachment("Screenshot")
    private static byte[] attachScreenshotToReport(byte[] screenShot) {
        return screenShot;
    }


    private static boolean isRunningOnLocalMachine() {
        return System.getenv("GITLAB_CI") == null;
    }


    @Override
    public void beforeAll(final ExtensionContext extensionContext) {
        if (!chrome.isRunning()) {
            chrome.start();
        }
    }


    @Override
    public void afterTestExecution(ExtensionContext context) {
        takeScreenShot();
    }


    public WebDriver getWebDriver() {
        return chrome.getWebDriver();
    }


    private void takeScreenShot() {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        try {
            outputStream.write(((TakesScreenshot) getWebDriver()).getScreenshotAs(OutputType.BYTES));
        } catch (IOException e) {
            e.printStackTrace();
        }
        attachScreenshotToReport(outputStream.toByteArray());
    }


    private ChromeOptions getChromeOptions() {
        ChromeOptions options = new ChromeOptions();
        HashMap<String, Object> chromePrefs = new HashMap<>();
        chromePrefs.put("intl.accept_languages", "en");
        options.setExperimentalOption("prefs", chromePrefs);
        options.addArguments("--whitelisted-ips");
        options.addArguments("--disable-dev-shm-usage");
        options.setAcceptInsecureCerts(true);
        return options;
    }


    private DockerImageName getDockerImageName() {
        String operatingSystem = System.getProperty("os.name");
        String architecture = System.getProperty("os.arch");
        //Apple with M1 silicon chips need a new docker image
        if (isRunningOnLocalMachine() && operatingSystem.contains("mac") && architecture.contains("aarch64")) {
            return DockerImageName.parse("seleniarm/standalone-chromium:4.0.0-beta-1-20210215").asCompatibleSubstituteFor("selenium/standalone-chrome-debug");
        } else {
            return DockerImageName.parse("selenium/standalone-chrome-debug");
        }
    }

}
