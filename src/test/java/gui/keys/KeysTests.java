package gui.keys;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;
import gui.base.GUIBaseTest;
import static org.assertj.core.api.Assertions.assertThat;

class KeysTests extends GUIBaseTest {

    @Test
    void testBackspace() {
        var keyPage = homePage.clickKeyPresses();
        keyPage.enterText("A" + Keys.BACK_SPACE);

        assertThat(keyPage.getResult()).isEqualTo("You entered: BACK_SPACE");
    }


}
