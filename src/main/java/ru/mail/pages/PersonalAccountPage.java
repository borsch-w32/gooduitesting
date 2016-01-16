package ru.mail.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * Created by cqi on 11.12.15. Educate. Grow. Satan.
 */
public class PersonalAccountPage extends AbstractPage
{
    public static final String mailUrl = "https://www.mail.ru";
    private static final String log = "borsch.w32";
    private static final String password = "55tnxadmin";

    @FindBy(name = "Login")
    private WebElement login;

    @FindBy(name = "Password")
    private WebElement passwd;

    @FindBy(id = "mailbox__auth__button")
    private WebElement authButton;

    public PersonalAccountPage(WebDriver webDriver)
    {
        super(webDriver);
        PageFactory.initElements(this.webDriver, this);
    }

    public PersonalAccountPage enterData()
    {
        login.sendKeys(log);
        passwd.sendKeys(password);
        authButton.click();
        return PageFactory.initElements(webDriver, PersonalAccountPage.class);
    }
}
