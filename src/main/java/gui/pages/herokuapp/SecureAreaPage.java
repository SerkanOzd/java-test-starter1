package gui.pages.herokuapp;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SecureAreaPage {
    private final WebDriver driver;
    private By statusAlert = By.id("flash");

    public SecureAreaPage(WebDriver driver) {
        this.driver = driver;

    }

    public String getAlerText() {
        return driver.findElement(statusAlert).getText();
    }
}
