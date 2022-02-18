package gui.selenium;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.NoSuchElementException;
import java.util.logging.Level;
import java.util.stream.Stream;
import org.junit.jupiter.api.extension.AfterEachCallback;
import org.junit.jupiter.api.extension.BeforeAllCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testcontainers.containers.BrowserWebDriverContainer;
import org.testcontainers.containers.DefaultRecordingFileFactory;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.lifecycle.TestDescription;
import org.testcontainers.utility.DockerImageName;
import io.qameta.allure.Attachment;
import lombok.extern.java.Log;
import static org.testcontainers.containers.BrowserWebDriverContainer.VncRecordingMode.RECORD_FAILING;
import static org.testcontainers.containers.VncRecordingContainer.VncRecordingFormat.MP4;

@Testcontainers
@Log
@SuppressWarnings({"squid:S3740", "squid:S3655"})
public class ChromeBrowserExtension implements BeforeAllCallback, AfterEachCallback {

    private final ChromeOptions chromeOptions = getChromeOptions();

    private static final String TARGET = "./target/";

    @Container
    private final BrowserWebDriverContainer chrome = new BrowserWebDriverContainer(getDockerImageName())
            .withCapabilities(chromeOptions)
            .withRecordingMode(RECORD_FAILING, new File(TARGET), MP4)
            .withRecordingFileFactory(new DefaultRecordingFileFactory());


    private static boolean isRunningOnLocalMachine() {
        return System.getenv("GITLAB_CI") == null;
    }


    @Override
    public void beforeAll(final ExtensionContext extensionContext) {
        if (!chrome.isRunning()) {
            chrome.start();
        }
    }


    @Attachment(value = "Screen recording", type = "video/mp4")
    static byte[] attachScreenRecording(byte[] screenShot) {
        return screenShot;
    }


    private TestDescription toTestDescription(ExtensionContext context) {
        return new TestDescription() {
            public String getTestId() {
                return context.getDisplayName();
            }


            public String getFilesystemFriendlyName() {
                return context.getTestClass().get().getName() + "-" + context.getTestMethod().get().getName();
            }
        };
    }


    @Override
    public void afterEach(ExtensionContext context) {
        chrome.afterTest(toTestDescription(context), context.getExecutionException());
        if (context.getExecutionException().isPresent()) {
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            String fileName = String.format("FAILED-%s-%s", context.getTestClass().get().getName(), context.getTestMethod().get().getName());
            try (Stream<Path> paths = Files.walk(Paths.get(TARGET))) {
                outputStream.write(Files.readAllBytes(paths
                        .filter(file -> file.toString().contains(fileName)).findFirst()
                        .orElseThrow(NoSuchElementException::new)));
                attachScreenRecording(outputStream.toByteArray());
            } catch (IOException e) {
                log.log(Level.WARNING, e.getMessage());
            }
        }
    }


    public WebDriver getWebDriver() {
        return chrome.getWebDriver();
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
        String operatingSystem = System.getProperty("os.name").toLowerCase();
        String architecture = System.getProperty("os.arch").toLowerCase();
        //Apple with M1 silicon chips need a new docker image
        if (isRunningOnLocalMachine() && operatingSystem.contains("mac") && architecture.contains("aarch64")) {
            return DockerImageName.parse("seleniarm/standalone-chromium:4.0.0-beta-1-20210215").asCompatibleSubstituteFor("selenium/standalone-chrome-debug");
        } else {
            return DockerImageName.parse("selenium/standalone-chrome-debug");
        }
    }


}
