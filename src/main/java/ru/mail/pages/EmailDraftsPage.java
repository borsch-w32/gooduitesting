package ru.mail.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * Created by cqi on 12.12.15. Educate. Grow. Satan.
 */
public class EmailDraftsPage extends AbstractPage
{
    @FindBy(xpath = ".//*[@id='b-toolbar__right']//div/a[contains(@href, '/messages/drafts')][contains(@rel, 'history')][contains(@class, 'toolbar__message_info__link')]")
    public WebElement drafts;

    @FindBy(xpath = ".//*[@id='b-letters']//div[parent::div[contains(@class,'b-datalist__body')]][1]//a")
//    @FindBy(css = ".b-datalist__item__subj")
//    @FindBy(xpath = ".//*[@id='b-letters']/div/div[3]/div/div[2]/div[1]/div/a/div[4]")
    public WebElement lastEmail;

    @FindBy(xpath = ".//*[@id='compose__header__content']//span[contains(@class, 'compose__labels__label__text js-label-text') and parent::span[not(contains(@style,'none'))]][contains(text(),'cqi90@mail.ru')]")
    public WebElement emailRecepient;

    @FindBy(xpath = ".//*[@class='compose__header__field' and parent::div[contains(@class,'compose__header__field__box')]][@name='Subject']")
    public WebElement emailSubject;

    @FindBy(xpath = ".//*[@id='b-toolbar__right']//span[contains(text(), 'Отправить')]")
    public WebElement send;

    @FindBy(xpath = ".//*[@id='b-compose__sent']//div/span")
    public WebElement incoming;

    public EmailDraftsPage(WebDriver webDriver)
    {
        super(webDriver);
        PageFactory.initElements(this.webDriver, this);
    }
}
