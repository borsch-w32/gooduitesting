package test.mailru;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * Created by cqi on 25.12.15.
 * Educate. Grow. Satan.
 */
public class ElementHighlighter extends AbstractPage {
    ElementHighlighter(WebDriver webDriver) {
        super(webDriver);
    }
    public static void highlightElem(WebDriver webDriver, WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) webDriver;
        js.executeScript("arguments[0].setAttribute('style','border: solid 2px red')", element);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }
        js.executeScript("arguments[0].setAttribute('style','border: solid 2px white')", element);
    }
}
