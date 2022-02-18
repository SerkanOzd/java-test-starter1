package gui.realword.registration;

import java.util.Locale;
import org.junit.jupiter.api.Test;
import core.utils.testdata.TestDataGenerator;
import core.utils.testdata.typs.User;
import gui.pages.realworld.RegisterPage;
import gui.realword.base.RealWorldGUIBaseTest;
import static org.assertj.core.api.Assertions.assertThat;


class RealWorldRegistrationTest extends RealWorldGUIBaseTest {
    TestDataGenerator generator;
    User user;


    @Test
    void testSuccessfulRegistration() {
        generator = new TestDataGenerator("de");
        user = generator.generateSimpleUser();

        RegisterPage registerPage = realWorldHomePage.clickSignUp();
        registerPage.setUsername(user.getFistName().toLowerCase(Locale.ROOT));
        registerPage.setEmail(generator.getFaker().internet().emailAddress());
        registerPage.setPassword(generator.getFaker().internet().password());

        assertThat(realWorldHomePage.isUserLoggedIn()).withFailMessage("User isn't registered").isTrue();
    }
}
