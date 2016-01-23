package ru.mail.pages;

import java.io.IOException;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import ru.mail.utils.PropertiesParser;

/**
 * Created by cqi on 12.12.15. Educate. Grow. Satan.
 */
public class ComposeEmailPage extends AbstractPage
{

    @FindBy(xpath = ".//*[@id='b-toolbar__left']//div/a/span[contains(text(), 'Написать письмо')]")
    private WebElement composeButton;

    @FindBy(xpath = ".//*[@id='compose__header__content']//div/textarea[2][@data-original-name='To']")
    private WebElement forWhom;

    @FindBy(xpath = ".//*[contains(@id,'ab_compose_subj')]")
    private WebElement subject;

    public ComposeEmailPage(WebDriver webDriver)
    {
        super(webDriver);
        PageFactory.initElements(this.webDriver, this);
    }

    public ComposeEmailPage fillInEmailAndSave() throws InterruptedException, IOException
    {
        PropertiesParser propertiesParser = new PropertiesParser();
        Actions action = new Actions(webDriver);
        WebElement composeEmailButton = (new WebDriverWait(webDriver, 10))
                .until(ExpectedConditions.presenceOfElementLocated(
                        By.xpath(".//*[@id='b-toolbar__left']//div/a/span[contains(text(), 'Написать письмо')]")));
        composeButton.click();
        forWhom.sendKeys(propertiesParser.getTestAddress());
        subject.sendKeys(propertiesParser.getTestSubject());
        ((JavascriptExecutor) webDriver)
                .executeScript("tinyMCE.activeEditor.selection.setContent('Test content message!')");
        action.keyDown(Keys.CONTROL).sendKeys(String.valueOf('\u0073')).perform();
        WebElement myDynamicElement = (new WebDriverWait(webDriver, 10)).until(ExpectedConditions.elementToBeClickable(
                By.xpath(".//*[@id='b-toolbar__right']//div/a[contains(@class, 'toolbar__message_info__link')]")));
        return PageFactory.initElements(webDriver, ComposeEmailPage.class);
    }
}
