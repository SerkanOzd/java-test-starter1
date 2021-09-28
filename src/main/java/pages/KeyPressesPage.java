package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;

public class KeyPressesPage {
    private final WebDriver driver;
    private By inputField = By.id("target");
    private By resultText = By.id("result");

    public KeyPressesPage(WebDriver driver) {
        this.driver = driver;
    }

    public void enterText(String text){
        driver.findElement(inputField).sendKeys(text);
    }

    public void enterPi(){
        /* mac
        enterText(Keys.chord(Keys.ALT, "p") + "=3.14");
        */
        /*windows*/
        enterText("π" + "=3.14");
    }

    public String getResult(){
        return driver.findElement(resultText).getText();
    }
}
