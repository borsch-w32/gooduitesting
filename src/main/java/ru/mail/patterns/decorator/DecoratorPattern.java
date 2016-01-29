package ru.mail.patterns.decorator;

import java.io.IOException;
import java.util.logging.Logger;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import ru.mail.pages.PersonalAccountPage;
import ru.mail.utils.PropertiesParser;

/**
 * Created by Sergei_Sergeenko on 1/29/2016.
 */
public class DecoratorPattern extends PersonalAccountPage
{
    private static Logger logger = Logger.getLogger(DecoratorPattern.class.getName());

    @FindBy(name = "Login")
    private WebElement login;

    @FindBy(name = "Password")
    private WebElement passwd;

    @FindBy(id = "mailbox__auth__button")
    private WebElement authButton;

    protected PersonalAccountPage personalAccountPage;

    public DecoratorPattern(WebDriver webDriver)
    {
        super(webDriver);
    }

    public PersonalAccountPage enterData() throws IOException
    {
        PropertiesParser propertiesParser = new PropertiesParser();
        login.sendKeys(propertiesParser.getLogin());
        passwd.sendKeys(propertiesParser.getPassword());
        authButton.click();
        logger.info("Logged into personal account");
        return PageFactory.initElements(this.webDriver, PersonalAccountPage.class);
    }
}
