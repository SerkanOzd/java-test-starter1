package gui.herokuapp.alerts;

import org.junit.jupiter.api.Test;
import gui.herokuapp.base.HerokuAppGUIBaseTest;
import static org.assertj.core.api.Assertions.assertThat;


class AlertsTests extends HerokuAppGUIBaseTest {

    @Test
    void testAcceptAlert() {
        var alertsPage = herokuApphomePage.clickJavaScriptAlerts();
        alertsPage.triggerAlert();
        alertsPage.alertClickToAccept();

        assertThat(alertsPage.getResult()).isEqualTo("You successfully clicked an alert");
    }


    @Test
    void testGetTextFromAlert() {
        var alertsPage = herokuApphomePage.clickJavaScriptAlerts();
        alertsPage.triggerConfirm();
        String text = alertsPage.alertGetText();
        alertsPage.alertClickToDismiss();

        assertThat(text).isEqualTo("I am a JS Confirm");
    }


    @Test
    void testSetInputInAlert() {
        var alertsPage = herokuApphomePage.clickJavaScriptAlerts();
        alertsPage.triggerPrompt();

        String text = "TAU rocks!";
        alertsPage.alertSetInput(text);
        alertsPage.alertClickToAccept();
        assertThat(alertsPage.getResult()).isEqualTo("You entered: " + text);
    }
}
