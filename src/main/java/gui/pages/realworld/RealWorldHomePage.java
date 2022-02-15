package gui.pages.realworld;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class RealWorldHomePage {


    private By isLoggedIn = By.xpath("//ul[@show-authed='true']//a/img");

    private WebDriver driver;


    public RealWorldHomePage(WebDriver driver) {
        this.driver = driver;
    }


    public RegisterPage clickSignUp() {
        clickLink("Sign up");
        return new RegisterPage(driver);
    }


    private void clickLink(String linkText) {
        driver.findElement(By.linkText(linkText)).click();
    }


    public boolean isLoggedIn() {
        return driver.findElement(isLoggedIn).isEnabled();
    }
}
