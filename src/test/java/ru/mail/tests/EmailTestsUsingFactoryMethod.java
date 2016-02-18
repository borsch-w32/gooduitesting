package ru.mail.tests;

import static org.testng.Assert.assertEquals;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import ru.mail.patterns.factory_method.ChromeDriverCreator;
import ru.mail.patterns.factory_method.FirefoxDriverCreator;
import ru.mail.patterns.factory_method.WebDriverCreator;
import ru.mail.utils.PropertiesParser;

/**
 * Created by Sergei_Sergeenko on 1/29/2016.
 */
public class EmailTestsUsingFactoryMethod
{
    private static final Logger logger = LogManager.getLogger(EmailTestsUsingFactoryMethod.class);

    @Test(testName = "Chrome - test mail.ru main page logo is displayed")
    public void testMainPageLogoChrome() throws IOException
    {
        PropertiesParser propertiesParser = new PropertiesParser();
        WebDriverCreator creator = new ChromeDriverCreator();
        WebDriver webDriver = creator.factoryMethod();
        webDriver.get(propertiesParser.getSite());
        webDriver.manage().window().maximize();
        WebElement logo = webDriver.findElement(By.xpath(".//*[@id='logo']/img[2]"));
        assertEquals(true, logo.isDisplayed());
        webDriver.close();
        webDriver.quit();
        logger.info("Chrome was killed");
    }

    @Test(testName = "Firefox - test mail.ru main page logo is displayed")
    public void testMainPageLogoFirefox() throws IOException
    {
        PropertiesParser propertiesParser = new PropertiesParser();
        WebDriverCreator creator = new FirefoxDriverCreator();
        WebDriver webDriver = creator.factoryMethod();
        webDriver.get(propertiesParser.getSite());
        webDriver.manage().window().maximize();
        WebElement logo = webDriver.findElement(By.xpath(".//*[@id='logo']/img[2]"));
        assertEquals(true, logo.isDisplayed());
        webDriver.close();
        webDriver.quit();
        logger.info("Firefox was killed");
    }
}
