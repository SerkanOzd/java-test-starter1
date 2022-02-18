package gui.pages.herokuapp;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import gui.pages.common.AbstractPageObject;

public class KeyPressesPage extends AbstractPageObject {

    @FindBy(id = "target")
    public WebElement inputField;

    @FindBy(id = "result")
    public WebElement resultText;


    public KeyPressesPage(WebDriver webDriver) {
        super(webDriver);
    }


    public void enterText(String text) {
        enter(inputField, text);
    }


    public String getResult() {
        return getText(resultText);
    }
}
