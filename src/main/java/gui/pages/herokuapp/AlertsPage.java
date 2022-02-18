package gui.pages.herokuapp;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import gui.pages.common.AbstractPageObject;

public class AlertsPage extends AbstractPageObject {

    @FindBy(xpath = ".//button[text()='Click for JS Alert']")
    public WebElement triggerAlertButton;

    @FindBy(xpath = ".//button[text()='Click for JS Confirm']")
    public WebElement triggerConfirmButton;

    @FindBy(xpath = ".//button[text()='Click for JS Prompt']")
    public WebElement triggerPromptButton;

    @FindBy(id = "result")
    public WebElement result;


    public AlertsPage(WebDriver webDriver) {
        super(webDriver);
    }


    public void triggerAlert() {
        clickOnWithoutScreenshot(triggerAlertButton);
    }


    public void triggerConfirm() {
        clickOnWithoutScreenshot(triggerConfirmButton);
    }


    public void triggerPrompt() {
        clickOnWithoutScreenshot(triggerPromptButton);
    }


    public void alertClickToAccept() {
        webDriver.switchTo().alert().accept();
    }


    public void alertClickToDismiss() {
        webDriver.switchTo().alert().dismiss();
    }


    public String alertGetText() {
        return webDriver.switchTo().alert().getText();
    }


    public void alertSetInput(String text) {
        webDriver.switchTo().alert().sendKeys(text);
    }


    public String getResult() {
        return result.getText();
    }
}
