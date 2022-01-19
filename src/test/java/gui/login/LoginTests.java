package gui.login;

import org.junit.jupiter.api.Test;
import gui.base.GUIBaseTest;
import gui.pages.LoginPage;
import gui.pages.SecureAreaPage;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class LoginTests extends GUIBaseTest {

    @Test
    public void testSuccessfulLogin() {
        LoginPage loginPage = homePage.clickFormAuthentication();
        loginPage.setUsername("tomsmith");
        loginPage.setPassword("SuperSecretPassword!");
        SecureAreaPage secureAreaPage = loginPage.clickLoginButton();
        assertTrue(secureAreaPage.getAlerText().contains("You logged into a secure area!"), "Alert text is incorrect");

    }
}
