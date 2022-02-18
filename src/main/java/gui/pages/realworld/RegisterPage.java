package gui.pages.realworld;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import gui.pages.common.AbstractPageObject;
import gui.pages.herokuapp.SecureAreaPage;

public class RegisterPage extends AbstractPageObject {

    @FindBy(xpath = "//input[@type='text']")
    public WebElement usernameField;
    @FindBy(xpath = "//input[@type='email']")
    public WebElement emailField;
    @FindBy(xpath = "//input[@type='password']")
    public WebElement passwordField;
    @FindBy(xpath = "//button")
    public WebElement registerButton;


    public RegisterPage(WebDriver webDriver) {
        super(webDriver);
    }


    public void setUsername(String username) {
        enter(usernameField, username);
    }


    public void setEmail(String email) {
        enter(emailField, email);
    }


    public void setPassword(String password) {
        enter(passwordField, password);
    }


    public SecureAreaPage clickRegisterButton() {
        registerButton.click();
        return new SecureAreaPage(webDriver);
    }
}
