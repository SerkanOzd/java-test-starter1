package gui.pages.herokuapp;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import gui.pages.common.AbstractPageObject;

public class SecureAreaPage extends AbstractPageObject {


    @FindBy(id = "flash")
    public WebElement statusAlert;


    public SecureAreaPage(WebDriver webDriver) {
        super(webDriver);
    }


    public String getAlertText() {
        return getText(statusAlert);
    }
}
