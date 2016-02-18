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

import ru.mail.pages.ComposeEmailPage;
import ru.mail.pages.PersonalAccountPage;
import ru.mail.patterns.singleton.WebDriverSingleton;
import ru.mail.utils.PropertiesParser;
import ru.mail.utils.TakeScreenshotOnFailure;

/**
 * Created by Sergei_Sergeenko on 12/24/2015.
 */
public class PageComponentsTests
{
    private WebDriver webDriver;

    private static final Logger logger = LogManager.getLogger(PageComponentsTests.class);

    @BeforeMethod(description = "Open new page")
    public void setUp() throws IOException
    {
        PropertiesParser propertiesParser = new PropertiesParser();
        // uncomment the lines below to run tests in GRID
        // DesiredCapabilities capability = DesiredCapabilities.firefox();
        // capability.setBrowserName("firefox");
        // capability.setVersion("43.0");
        // capability.setPlatform(Platform.LINUX);
        // webDriver = new RemoteWebDriver(new
        // URL("http://192.168.10.4:4444/wd/hub"), capability);
        // webDriver.get(propertiesParser.getSite());
        webDriver = WebDriverSingleton.getWebDriverInstance();
        webDriver.get(propertiesParser.getSite());
        webDriver.manage().window().maximize();
    }

    @Test(testName = "Check incoming emails page title")
    public void testTitle() throws IOException
    {
        PersonalAccountPage personalAccountPage = new PersonalAccountPage(webDriver);
        personalAccountPage.enterData();
        String title = webDriver.getTitle();
        assertEquals(title, "Входящие - borsch.w32@mail.ru - Почта Mail.Ru");
    }

    @Test(testName = "Check personal mail hyperlink")
    public void testHyperLink() throws IOException
    {
        PersonalAccountPage personalAccountPage = new PersonalAccountPage(webDriver);
        logger.info("Entering user's personal data");
        personalAccountPage.enterData();
        WebElement userEmailHyperlink = (new WebDriverWait(webDriver, 10))
                .until(ExpectedConditions.presenceOfElementLocated(By.id("PH_user-email")));
        String hyperLink = webDriver.findElement(By.id("PH_user-email")).getText();
        assertEquals(hyperLink, "borsch.w32@mail.ru");
    }

    @Test(testName = "Check title of the create new email page")
    public void testNewEmailPageTitle() throws InterruptedException, IOException
    {
        PersonalAccountPage personalAccountPage = new PersonalAccountPage(webDriver);
        logger.info("Entering user's personal data");
        personalAccountPage.enterData();
        ComposeEmailPage composeEmailPage = new ComposeEmailPage(webDriver);
        composeEmailPage.fillInEmailAndSave();
        String actualNewEmailPageTitle = webDriver.getTitle();
        assertEquals(actualNewEmailPageTitle, "Новое письмо - borsch.w32@mail.ru - Почта Mail.Ru");
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
        logger.info("Firefox was killed");
    }
}
