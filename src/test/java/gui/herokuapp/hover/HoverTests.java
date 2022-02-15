package gui.herokuapp.hover;

import org.junit.jupiter.api.Test;
import gui.herokuapp.base.HerokuAppGUIBaseTest;
import static org.assertj.core.api.Assertions.assertThat;

class HoverTests extends HerokuAppGUIBaseTest {

    @Test
    void testHoverUser1() {
        var hoversPage = herokuApphomePage.clickHovers();
        var caption = hoversPage.hoverOverFigure(1);

        assertThat(caption.isCaptionDisplayed()).isTrue();
        assertThat(caption.getTitle()).isEqualTo("name: user1");
        assertThat(caption.getLinkText()).isEqualTo("View profile");
        //assertThat(caption.getLink()).endsWith("/users/1");
    }
}
