package gui.pages.herokuapp;

import org.openqa.selenium.WebDriver;
import gui.pages.common.AbstractPageObject;

public class HerokuAppHomePage extends AbstractPageObject {

    public HerokuAppHomePage(WebDriver webDriver) {
        super(webDriver);
    }


    public LoginPage clickFormAuthentication() {
        clickLink("Form Authentication");
        return new LoginPage(webDriver);
    }


    public DropdownPage clickDropDown() {
        clickLink("Dropdown");
        return new DropdownPage(webDriver);
    }


    public HoverPage clickHovers() {
        clickLink("Hovers");
        return new HoverPage(webDriver);
    }


    public KeyPressesPage clickKeyPresses() {
        clickLink("Key Presses");
        return new KeyPressesPage(webDriver);
    }


    public AlertsPage clickJavaScriptAlerts() {
        clickLink("JavaScript Alerts");
        return new AlertsPage(webDriver);
    }


}
