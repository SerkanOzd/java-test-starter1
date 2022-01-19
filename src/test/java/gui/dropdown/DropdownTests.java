package gui.dropdown;

import org.junit.jupiter.api.Test;
import gui.base.GUIBaseTest;
import static org.assertj.core.api.Assertions.assertThat;

class DropdownTests extends GUIBaseTest {

    @Test
    void testSelectOption() {
        var dropDownPage = homePage.clickDropDown();

        String option = "Option 1";
        dropDownPage.selectFromDropDown(option);
        var selectedOptions = dropDownPage.getSelectedOptions();

        assertThat(selectedOptions.size()).isEqualTo(1);
        assertThat(selectedOptions).contains(option);
    }
}
