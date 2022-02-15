package gui.herokuapp.keys;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;
import gui.herokuapp.base.HerokuAppGUIBaseTest;
import static org.assertj.core.api.Assertions.assertThat;

class KeysTests extends HerokuAppGUIBaseTest {

    @Test
    void testBackspace() {
        var keyPage = herokuApphomePage.clickKeyPresses();
        keyPage.enterText("A" + Keys.BACK_SPACE);

        assertThat(keyPage.getResult()).isEqualTo("You entered: BACK_SPACE");
    }


}
