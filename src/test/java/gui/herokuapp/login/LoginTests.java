package gui.herokuapp.login;

import org.junit.jupiter.api.Test;
import gui.herokuapp.base.HerokuAppGUIBaseTest;
import gui.pages.herokuapp.LoginPage;
import gui.pages.herokuapp.SecureAreaPage;
import static org.junit.jupiter.api.Assertions.assertTrue;

class LoginTests extends HerokuAppGUIBaseTest {

    @Test
    void testSuccessfulLogin() {
        LoginPage loginPage = herokuApphomePage.clickFormAuthentication();
        loginPage.setUsername("tomsmith");
        loginPage.setPassword("SuperSecretPassword!");
        SecureAreaPage secureAreaPage = loginPage.clickLoginButton();
        assertTrue(secureAreaPage.getAlerText().contains("You logged into a secure area!"), "Alert text is incorrect");

    }
}
