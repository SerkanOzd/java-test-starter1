package gui.pages.common;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.logging.Level;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import io.qameta.allure.Attachment;
import io.qameta.allure.Step;
import lombok.extern.java.Log;

/**
 * Abstract class that provides common Selenium actions
 */
@Log
public abstract class AbstractPageObject {

    protected WebDriver webDriver;


    /**
     * Initializes all web elements defined in the page objects.
     * Sets the web driver to perform all the actions on.
     *
     * @param webDriver the web driver instance to be used
     */
    protected AbstractPageObject(WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);
    }


    @Attachment("Screenshot")
    private static byte[] attachScreenshotToReport(byte[] screenShot) {
        return screenShot;
    }


    /**
     * Clicks on an element, then takes a screenshot.
     *
     * @param element the element that should be clicked on
     */
    @Step("Click on {0}")
    protected void clickOn(WebElement element) {
        element.click();
        takeScreenShot();
    }


    /**
     * Clicks on an element without taking a screenshot afterwards if it causes problems (e.g. open alerts).
     *
     * @param element the element that should be clicked on
     */
    @Step("Click on {0}")
    protected void clickOnWithoutScreenshot(WebElement element) {
        element.click();
    }


    /**
     * Deletes the content of an input field, enters a text and then takes screenshot.
     *
     * @param element the input field
     * @param input   the desired text
     */
    @Step("Enter {1} into {0}")
    protected void enter(WebElement element, CharSequence... input) {
        element.clear();
        element.sendKeys(input);
        takeScreenShot();
    }


    /**
     * Selects an option from a drop-down list by the visible text of the options, then takes a screenshot.
     *
     * @param element the drop-down list
     * @param text    the desired option
     */
    @Step("Select {1} from {0}")
    public void selectByText(WebElement element, String text) {
        new Select(element).selectByVisibleText(text);
        takeScreenShot();
    }


    /**
     * Creates a screenshot and gets the text value of an element on the webpage.
     *
     * @param element the desired web element
     * @return the text value of the element
     */
    @Step("Get the displayed text of {0}")
    protected String getText(WebElement element) {
        takeScreenShot();
        return element.getText();
    }


    /**
     * Cliks on a link with the provided link text, then takes a screenshot.
     *
     * @param linkText the text of the link
     */
    @Step("Click on link with text {0}")
    protected void clickLink(String linkText) {
        webDriver.findElement(By.linkText(linkText)).click();
        takeScreenShot();
    }


    /**
     * Creates a screenshot of the current state of the page and attaches it the generated report.
     */
    protected void takeScreenShot() {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        try {
            outputStream.write(((TakesScreenshot) webDriver).getScreenshotAs(OutputType.BYTES));
        } catch (IOException e) {
            log.log(Level.SEVERE, e.getMessage());
        }
        attachScreenshotToReport(outputStream.toByteArray());
    }

}

