package ru.mail.tests;

import static org.testng.Assert.assertEquals;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import ru.mail.pages.PersonalAccountPage;
import ru.mail.patterns.decorator.DecoratorPattern;
import ru.mail.patterns.singleton.WebDriverSingleton;
import ru.mail.utils.PropertiesParser;
import ru.mail.utils.TakeScreenshotOnFailure;

/**
 * Created by Sergei_Sergeenko on 1/29/2016.
 */
public class EmailTestsUsingDecorator
{
    private WebDriver webDriver;

    private static final Logger logger = LogManager.getLogger(EmailTestsUsingDecorator.class);

    @BeforeMethod(description = "Open new page")
    public void setUp() throws IOException
    {
        PropertiesParser propertiesParser = new PropertiesParser();
        webDriver = WebDriverSingleton.getWebDriverInstance();
        webDriver.get(propertiesParser.getSite());
        webDriver.manage().window().maximize();
    }

    @Test(testName = "Check personal mail hyperlink")
    public void testHyperLink() throws IOException
    {
        PersonalAccountPage personalAccountPage = new DecoratorPattern(webDriver);
        personalAccountPage.enterData();
        WebElement userEmailHyperlink = (new WebDriverWait(webDriver, 10))
                .until(ExpectedConditions.presenceOfElementLocated(By.id("PH_user-email")));
        String hyperLink = webDriver.findElement(By.id("PH_user-email")).getText();
        assertEquals(hyperLink, "borsch.w32@mail.ru");
    }

    @AfterMethod(description = "Take screenshot on fail")
    public void takeScreenShotOnFailure(ITestResult testResult) throws IOException
    {
        if (testResult.getStatus() == ITestResult.FAILURE)
        {
            logger.error("Error occured!");
            TakeScreenshotOnFailure.takeScreenshot(webDriver);
        }
    }

    @AfterMethod(description = "Close FF Instance")
    public void tearDown()
    {
        WebDriverSingleton webDriverSingleton = new WebDriverSingleton();
        webDriverSingleton.tearDown();
    }
}
