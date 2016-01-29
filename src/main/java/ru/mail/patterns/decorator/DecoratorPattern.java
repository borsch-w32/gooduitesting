package ru.mail.patterns.decorator;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import ru.mail.pages.PersonalAccountPage;
import ru.mail.utils.PropertiesParser;

import java.io.IOException;
import java.util.logging.ConsoleHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Sergei_Sergeenko on 1/29/2016.
 */
public class DecoratorPattern extends PersonalAccountPage {

    private static final Logger LOGGER = Logger.getLogger(DecoratorPattern.class.getName());
    Handler consoleHandler = null;

    @FindBy(name = "Login")
    private WebElement login;

    @FindBy(name = "Password")
    private WebElement passwd;

    @FindBy(id = "mailbox__auth__button")
    private WebElement authButton;

    protected PersonalAccountPage  personalAccountPage;

    public DecoratorPattern(WebDriver webDriver) {
        super(webDriver);
    }

    public PersonalAccountPage enterData() throws IOException
    {
        PropertiesParser propertiesParser = new PropertiesParser();
        login.sendKeys(propertiesParser.getLogin());
        passwd.sendKeys(propertiesParser.getPassword());
        authButton.click();
        consoleHandler = new ConsoleHandler();
        LOGGER.addHandler(consoleHandler);
        consoleHandler.setLevel(Level.ALL);
        LOGGER.setLevel(Level.ALL);
        LOGGER.info("Logger Name: "+LOGGER.getName());
        return PageFactory.initElements(webDriver, PersonalAccountPage.class);
    }
}


