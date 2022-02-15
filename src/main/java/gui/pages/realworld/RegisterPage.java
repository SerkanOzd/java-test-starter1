package gui.pages.realworld;

import gui.pages.herokuapp.SecureAreaPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class RegisterPage {

    private WebDriver driver;
    private By usernameField = By.xpath("//input[@type='text']");
    private By emailField = By.xpath("//input[@type='email']");
    private By passwordField = By.xpath("//input[@type='password']");

    private By registerButton = By.xpath("//button");

    public RegisterPage(WebDriver driver) {
        this.driver = driver;
    }

    public  void setUsername(String username){
        driver.findElement(usernameField).sendKeys(username);
    }
    public  void setEmail(String password){
        driver.findElement(emailField).sendKeys(password);
    }
    public  void setPassword(String password){
        driver.findElement(passwordField).sendKeys(password);
    }

    public SecureAreaPage clickLoginButton(){
        driver.findElement(registerButton).click();
        return new SecureAreaPage(driver);
    }
}
