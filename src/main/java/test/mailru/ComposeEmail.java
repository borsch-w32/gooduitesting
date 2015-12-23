package test.mailru;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * Created by cqi on 12.12.15.
 * Educate. Grow. Satan.
 */
public class ComposeEmail extends AbstractPage {

    public static final String testAddress = "cqi90@mail.ru";
    String testSubject = "Test me if you can";
    int sleepTime = 1500;

    @FindBy(xpath = ".//*[@id='b-toolbar__left']//div/div[2]/div/a/span")
    private WebElement composeButton;

    @FindBy(xpath = ".//*[@id='compose__header__content']//div/textarea[2][@data-original-name='To']")
    private WebElement forWhom;

    @FindBy(xpath = ".//*[contains(@id,'ab_compose_subj')]")
    private WebElement subject;

    public ComposeEmail(WebDriver webDriver) {
        super(webDriver);
        PageFactory.initElements(this.webDriver, this);
    }

    public ComposeEmail fillInEmailAndSave() throws InterruptedException{
        Actions action = new Actions(webDriver);
        composeButton.click();
        forWhom.sendKeys(testAddress);
        subject.sendKeys(testSubject);
        ((JavascriptExecutor) webDriver).executeScript("tinyMCE.activeEditor.selection.setContent('Test content message!')");
        action.keyDown(Keys.CONTROL).sendKeys(String.valueOf('\u0073')).perform();
        Thread.sleep(sleepTime);
        return PageFactory.initElements(webDriver, ComposeEmail.class);
    }

}
