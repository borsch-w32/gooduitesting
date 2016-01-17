package ru.mail.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Created by cqi on 12.12.15. Educate. Grow. Satan.
 */
public class ComposeEmailPage extends AbstractPage {
    public static final String testAddress = "cqi90@mail.ru";
    String testSubject = "Test me if you can";
    int sleepTime = 1500;

    @FindBy(xpath = ".//*[@id='b-toolbar__left']//div/a/span[contains(text(), 'Написать письмо')]")
    private WebElement composeButton;

    @FindBy(xpath = ".//*[@id='compose__header__content']//div/textarea[2][@data-original-name='To']")
    private WebElement forWhom;

    @FindBy(xpath = ".//*[contains(@id,'ab_compose_subj')]")
    private WebElement subject;

    public ComposeEmailPage(WebDriver webDriver) {
        super(webDriver);
        PageFactory.initElements(this.webDriver, this);
    }

    public ComposeEmailPage fillInEmailAndSave() throws InterruptedException {
        Actions action = new Actions(webDriver);
        WebElement composeEmailButton = (new WebDriverWait(webDriver, 10))
                .until(ExpectedConditions.presenceOfElementLocated(By.xpath(".//*[@id='b-toolbar__left']//div/a/span[contains(text(), 'Написать письмо')]")));
        composeButton.click();
        forWhom.sendKeys(testAddress);
        subject.sendKeys(testSubject);
        ((JavascriptExecutor) webDriver).executeScript("tinyMCE.activeEditor.selection.setContent('Test content message!')");
        action.keyDown(Keys.CONTROL).sendKeys(String.valueOf('\u0073')).perform();
        Thread.sleep(sleepTime);
        return PageFactory.initElements(webDriver, ComposeEmailPage.class);
    }
}
