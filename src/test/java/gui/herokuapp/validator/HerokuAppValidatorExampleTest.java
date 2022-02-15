package gui.herokuapp.validator;

import org.junit.jupiter.api.Test;
import core.utils.testdata.TestDataValidator;
import gui.herokuapp.base.HerokuAppGUIBaseTest;
import static org.junit.jupiter.api.Assertions.assertEquals;


class HerokuAppValidatorExampleTest extends HerokuAppGUIBaseTest {


    @Test
    void testSetInputInAlert() {
        TestDataValidator validator = new TestDataValidator();
        var alertsPage = herokuApphomePage.clickJavaScriptAlerts();
        alertsPage.triggerPrompt();

        String text = "TAU rocks!";
        alertsPage.alertSetInput(text);
        alertsPage.alertClickToAccept();
        validator.validateRegex("TAU rocks!", alertsPage.getResult()); // if the string is in the text
        assertEquals(alertsPage.getResult(), "You entered: " + text, "Results text incorrect");
    }

}
