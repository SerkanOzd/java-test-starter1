package gui.alerts;

import gui.base.GUIBaseTest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class AlertsTests extends GUIBaseTest {

    @Test
    public void testAcceptAlert() {
        var alertsPage = homePage.clickJavaScriptAlterts();
        alertsPage.triggerAlert();
        alertsPage.alert_clickToAccept();

        assertEquals(alertsPage.getResult(), "You successfully clicked an alert");
    }

    @Test
    public void testGetTextFromAlert(){
        var alertsPage = homePage.clickJavaScriptAlterts();
        alertsPage.triggerConfirm();
        String text = alertsPage.alert_getText();
        alertsPage.alert_clickToDismiss();

        assertEquals(text, "I am a JS Confirm", "Alert text incorrect");
    }

    @Test
    public void testSetInputInAlert(){
        var alertsPage = homePage.clickJavaScriptAlterts();
        alertsPage.triggerPrompt();

        String text = "TAU rocks!";
        alertsPage.alert_setInput(text);
        alertsPage.alert_clickToAccept();

        assertEquals(alertsPage.getResult(),"You entered: " + text,"Results text incorrect");
    }
}
