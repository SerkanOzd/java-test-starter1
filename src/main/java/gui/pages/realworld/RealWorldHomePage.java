package gui.pages.realworld;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import gui.pages.common.AbstractPageObject;

public class RealWorldHomePage extends AbstractPageObject {

    @FindBy(xpath = "//ul[@show-authed='true']//a/img")
    public WebElement isLoggedIn;


    public RealWorldHomePage(WebDriver webDriver) {
        super(webDriver);
    }


    public RegisterPage clickSignUp() {
        clickLink("Sign up");
        return new RegisterPage(webDriver);
    }


    public boolean isUserLoggedIn() {
        //For demonstration purposes we let this test fail, so it will always return false
        return false;
    }
}
