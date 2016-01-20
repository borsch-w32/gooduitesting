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
    @FindBy(xpath = "//*[@id='b-nav_folders']//div/a/span[contains(@class, 'b-nav__item__text')][contains(text(), 'Черновики')]")
    public WebElement drafts;

    @FindBy(xpath = "//div[@class='b-datalist__body']/div[1]//a[contains(@class, 'b-datalist__item__link')]")
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
