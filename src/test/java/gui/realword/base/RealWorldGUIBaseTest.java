package gui.realword.base;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.openqa.selenium.WebDriver;
import gui.pages.realworld.RealWorldHomePage;
import gui.selenium.ChromeBrowserExtension;

@SuppressWarnings("squid:S2187")
public class RealWorldGUIBaseTest {

    @RegisterExtension
    public static ChromeBrowserExtension chromeBrowserExtension = new ChromeBrowserExtension();

    protected static RealWorldHomePage realWorldHomePage;


    @BeforeEach
    void goHome() {
        WebDriver driver = chromeBrowserExtension.getWebDriver();
        realWorldHomePage = new RealWorldHomePage(driver);
        driver.get("https://demo.realworld.io/#/");
    }
}
