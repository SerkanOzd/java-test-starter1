package gui.alerts;

import org.junit.jupiter.api.Test;
import gui.base.GUIBaseTest;
import static org.assertj.core.api.Assertions.assertThat;


class AlertsTests extends GUIBaseTest {

    @Test
    void testAcceptAlert() {
        var alertsPage = homePage.clickJavaScriptAlterts();
        alertsPage.triggerAlert();
        alertsPage.alert_clickToAccept();

        assertThat(alertsPage.getResult()).isEqualTo("You successfully clicked an alert");
    }


    @Test
    void testGetTextFromAlert() {
        var alertsPage = homePage.clickJavaScriptAlterts();
        alertsPage.triggerConfirm();
        String text = alertsPage.alert_getText();
        alertsPage.alert_clickToDismiss();

        assertThat(text).isEqualTo("I am a JS Confirm");
    }


    @Test
    void testSetInputInAlert() {
        var alertsPage = homePage.clickJavaScriptAlterts();
        alertsPage.triggerPrompt();

        String text = "TAU rocks!";
        alertsPage.alert_setInput(text);
        alertsPage.alert_clickToAccept();
        assertThat(alertsPage.getResult()).isEqualTo("You entered: " + text);
    }
}
