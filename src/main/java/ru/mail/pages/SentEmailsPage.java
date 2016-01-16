package ru.mail.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import ru.mail.pages.AbstractPage;

/**
 * Created by cqi on 12.12.15. Educate. Grow. Satan.
 */
public class SentEmailsPage extends AbstractPage
{
    public String title = webDriver.getTitle();

    @FindBy(xpath = ".//*[@id='b-nav_folders']//div/a/span[contains(text(), 'Отправленные')]")
    public WebElement sent;

    SentEmailsPage(WebDriver webDriver)
    {
        super(webDriver);
        PageFactory.initElements(this.webDriver, this);
    }
}
