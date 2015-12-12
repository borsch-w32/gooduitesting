package test.mailru;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * Created by cqi on 12.12.15.
 * Educate. Grow. Satan.
 */
public class EmailDrafts extends AbstractPage {

    @FindBy(xpath = ".//*[@id='b-nav_folders']/div/div[3]/a/span")
    public WebElement drafts;

    @FindBy(xpath = "//*[contains(@data-bem, 'b-datalist__item') and parent::div[contains(@class,'b-datalist__body')]][1]")
    public WebElement lastEmail;

    @FindBy(xpath = ".//*[@id='compose__header__content']//span[contains(@class, 'compose__labels__label__text js-label-text') and parent::span[not(contains(@style,'none'))]][contains(text(),'cqi90@mail.ru')]")
    public WebElement emailRecepient;

    @FindBy(xpath = ".//*[@class='compose__header__field' and parent::div[contains(@class,'compose__header__field__box')]][@name='Subject']")
    public WebElement emailSubject;

    @FindBy(xpath = ".//*[@id='b-toolbar__right']//span[contains(text(), 'Отправить')]")
    public WebElement send;

    EmailDrafts(WebDriver webDriver) {
        super(webDriver);
        PageFactory.initElements(this.webDriver, this);
    }
}
