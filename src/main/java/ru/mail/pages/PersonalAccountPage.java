package ru.mail.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import ru.mail.utils.PropertiesParser;

import java.io.IOException;

/**
 * Created by cqi on 11.12.15. Educate. Grow. Satan.
 */
public class PersonalAccountPage extends AbstractPage
{
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

    public PersonalAccountPage enterData() throws IOException
    {
        PropertiesParser propertiesParser = new PropertiesParser();
        login.sendKeys(propertiesParser.getLogin());
        passwd.sendKeys(propertiesParser.getPassword());
        authButton.click();
        return PageFactory.initElements(webDriver, PersonalAccountPage.class);
    }
}
