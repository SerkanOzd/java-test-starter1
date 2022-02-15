package gui.herokuapp.base;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.openqa.selenium.WebDriver;
import gui.pages.herokuapp.HerokuAppHomePage;
import gui.selenium.ChromeBrowserExtension;

@SuppressWarnings("squid:S2187")
public class HerokuAppGUIBaseTest {

    @RegisterExtension
    public static ChromeBrowserExtension chromeBrowserExtension = new ChromeBrowserExtension();

    protected static HerokuAppHomePage herokuApphomePage;


    @BeforeEach
    void goHome() {
        WebDriver driver = chromeBrowserExtension.getWebDriver();
        herokuApphomePage = new HerokuAppHomePage(driver);
        driver.get("https://the-internet.herokuapp.com/");
    }
}
