package gui.pages.herokuapp;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import gui.pages.common.AbstractPageObject;

public class HoverPage extends AbstractPageObject {

    @FindBy(className = "figure")
    public List<WebElement> figureBox;

    @FindBy(className = "figcaption")
    public WebElement boxCaption;


    public HoverPage(WebDriver webDriver) {
        super(webDriver);
    }


    public FigureCaption hoverOverFigure(int index) {
        WebElement figure = figureBox.get(index - 1);

        Actions actions = new Actions(webDriver);
        actions.moveToElement(figure).perform();

        return new FigureCaption(boxCaption);

    }


    public class FigureCaption {

        private WebElement caption;
        private By header = By.tagName("h5");
        private By link = By.tagName("a");


        public FigureCaption(WebElement caption) {
            this.caption = caption;
        }


        public boolean isCaptionDisplayed() {
            return caption.isDisplayed();
        }


        public String getTitle() {
            return caption.findElement(header).getText();
        }


        public String getLink() {
            return caption.findElement(link).getAttribute("href");
        }


        public String getLinkText() {
            return caption.findElement(link).getText();
        }
    }
}
