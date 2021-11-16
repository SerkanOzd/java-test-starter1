package gui.base;

import gui.pages.HomePage;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;


public class GUIBaseTest {

    private WebDriver driver;
    protected HomePage homePage;

    @BeforeAll
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "resources/chromedriver.exe");
        driver = new ChromeDriver();
        goHome();
        homePage = new HomePage(driver);
    }

    @BeforeEach
    public void goHome(){
        driver.get("https://the-internet.herokuapp.com/");
    }

    @AfterAll
    public void tearDown(){
        driver.quit();
    }
}
