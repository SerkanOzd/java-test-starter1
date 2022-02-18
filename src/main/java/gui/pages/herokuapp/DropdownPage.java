package gui.pages.herokuapp;

import java.util.List;
import java.util.stream.Collectors;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import gui.pages.common.AbstractPageObject;

public class DropdownPage extends AbstractPageObject {

    @FindBy(id = "dropdown")
    public WebElement dropdown;


    public DropdownPage(WebDriver driver) {
        super(driver);
    }


    public void selectOptionFromDropDown(String option) {
        selectByText(dropdown, option);
    }


    public List<String> getSelectedOptions() {
        List<WebElement> selectedElements = new Select(dropdown).getAllSelectedOptions();
        return selectedElements.stream().map(WebElement::getText).collect(Collectors.toList());
    }

}
