package gui.hover;

import org.junit.jupiter.api.Test;
import gui.base.GUIBaseTest;
import static org.assertj.core.api.Assertions.assertThat;

class HoverTests extends GUIBaseTest {

    @Test
    void testHoverUser1() {
        var hoversPage = homePage.clickHovers();
        var caption = hoversPage.hoverOverFigure(1);

        assertThat(caption.isCaptionDisplayed()).isTrue();
        assertThat(caption.getTitle()).isEqualTo("name: user1");
        assertThat(caption.getLinkText()).isEqualTo("View profile");
        assertThat(caption.getLink()).endsWith("/users/1");
    }
}
