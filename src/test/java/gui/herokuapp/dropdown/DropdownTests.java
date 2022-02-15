package gui.herokuapp.dropdown;

import org.junit.jupiter.api.Test;
import gui.herokuapp.base.HerokuAppGUIBaseTest;
import static org.assertj.core.api.Assertions.assertThat;

class DropdownTests extends HerokuAppGUIBaseTest {

    @Test
    void testSelectOption() {
        var dropDownPage = herokuApphomePage.clickDropDown();

        String option = "Option 1";
        dropDownPage.selectFromDropDown(option);
        var selectedOptions = dropDownPage.getSelectedOptions();

        assertThat(selectedOptions.size()).isEqualTo(1);
        assertThat(selectedOptions).contains(option);
    }
}
