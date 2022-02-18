package gui.pages.herokuapp;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import gui.pages.common.AbstractPageObject;

public class LoginPage extends AbstractPageObject {

    @FindBy(id = "username")
    public WebElement usernameField;

    @FindBy(id = "password")
    public WebElement passwordField;

    @FindBy(css = "#login button")
    public WebElement loginButton;


    public LoginPage(WebDriver webDriver) {
        super(webDriver);
    }


    public void setUsername(String username) {
        enter(usernameField, username);

    }


    public void setPassword(String password) {
        enter(passwordField, password);
    }


    public SecureAreaPage clickLoginButton() {
        clickOn(loginButton);
        return new SecureAreaPage(webDriver);
    }
}
