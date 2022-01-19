package gui.base;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.openqa.selenium.WebDriver;
import gui.pages.HomePage;
import gui.selenium.ChromeBrowserExtension;

@SuppressWarnings("squid:S2187")
public class GUIBaseTest {

    @RegisterExtension
    public static ChromeBrowserExtension chromeBrowserExtension = new ChromeBrowserExtension();

    protected static HomePage homePage;


    @BeforeEach
    void goHome() {
        WebDriver driver = chromeBrowserExtension.getWebDriver();
        homePage = new HomePage(driver);
        driver.get("https://the-internet.herokuapp.com/");
    }
}
