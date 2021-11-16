package gui;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.Browser;

import javax.lang.model.element.Element;
import java.util.List;

public interface SeleniumDriver {
    public abstract void start(Browser browser);
    public abstract void quit();
    public abstract void goToUrl(String url);
    public abstract Element findElement(By locator);
    public abstract List<Element> findElements(By locator);
} 