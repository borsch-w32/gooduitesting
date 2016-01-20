package ru.mail.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.mail.pages.*;
import ru.mail.utils.ElementHighlighter;
import ru.mail.utils.PropertiesParser;
import ru.mail.utils.TakeScreenshotOnFailure;

import java.io.IOException;
import java.net.MalformedURLException;

import static org.testng.Assert.assertEquals;

/**
 * Created by cqi on 11.12.15. Educate. Grow. Satan.
 */

public class EmailOperationsTests
{
    private WebDriver webDriver;

    @BeforeMethod(description = "Open new page")
    public void setUp() throws MalformedURLException, IOException
    {
        PropertiesParser propertiesParser = new PropertiesParser();
        // uncomment the lines below to run tests in GRID
        // DesiredCapabilities capability = DesiredCapabilities.firefox();
        // capability.setPlatform(Platform.LINUX);
        // capability.setBrowserName("firefox");
        // capability.setVersion("43.0");
        // webDriver = new RemoteWebDriver(new
        // URL("http://192.168.10.4:4444/wd/hub"), capability);
        // webDriver.get(PersonalAccountPage.mailUrl);
        webDriver = new FirefoxDriver();
        webDriver.get(propertiesParser.getSite());
        webDriver.manage().window().maximize();
    }

    @Test(testName = "Check last email from drafts", priority = 1)
    public void testLastEmailInDrafts() throws InterruptedException, IOException
    {
        PersonalAccountPage personalAccountPage = new PersonalAccountPage(webDriver);
        personalAccountPage.enterData();
        ComposeEmailPage composeEmailPage = new ComposeEmailPage(webDriver);
        composeEmailPage.fillInEmailAndSave();
        EmailDraftsPage emailDraftsPage = new EmailDraftsPage(webDriver);
        Actions clickDrafts = new Actions(webDriver);
        ElementHighlighter.highlightElem(webDriver, emailDraftsPage.drafts);
        clickDrafts.moveToElement(emailDraftsPage.drafts).click().build().perform();
        webDriver.findElement(By.cssSelector("body")).sendKeys(Keys.CONTROL, Keys.PAGE_DOWN);
        String winHandleBefore = webDriver.getWindowHandle();
        for (String winHandle : webDriver.getWindowHandles())
        {
            webDriver.switchTo().window(winHandle);
        }
        WebElement draftsHyperlink = (new WebDriverWait(webDriver, 10))
                .until(ExpectedConditions.visibilityOfElementLocated(
                        By.xpath(".//*[@id='b-nav_folders']//div/a/span[contains(text(), 'Черновики')]")));
        emailDraftsPage.lastEmail.click();
        String actualDeliverTo = emailDraftsPage.emailRecepient.getText();
        String actualSubject = emailDraftsPage.emailSubject.getAttribute("value");
        assertEquals(actualDeliverTo, "cqi90@mail.ru");
        assertEquals(actualSubject, "Test me if you can");
        emailDraftsPage.send.click();
    }

    @Test(testName = "Check Sent Emails", priority = 2)
    public void testSentEmailsFolder() throws IOException
    {
        PersonalAccountPage personalAccountPage = new PersonalAccountPage(webDriver);
        personalAccountPage.enterData();
        SentEmailsPage sentEmailsPage = new SentEmailsPage(webDriver);
        sentEmailsPage.sent.click();
        assertEquals(sentEmailsPage.title, "Входящие - borsch.w32@mail.ru - Почта Mail.Ru");
    }

    @Test(testName = "Log Off", priority = 3)
    public void logOffFromAccount() throws IOException
    {
        PersonalAccountPage personalAccountPage = new PersonalAccountPage(webDriver);
        personalAccountPage.enterData();
        WebElement logOffHyperLink = (new WebDriverWait(webDriver, 10)).until(
                ExpectedConditions.presenceOfElementLocated(By.xpath(".//*[@id='PH_logoutLink'][@title='выход']")));
        LogOffPage logOffPage = new LogOffPage(webDriver);
        assertEquals(logOffPage.exit.getAttribute("text"), "выход");
        logOffPage.exit.click();
    }

    @AfterMethod(description = "Take screenshot on fail")
    public void takeScreenShotOnFailure(ITestResult testResult) throws IOException
    {
        if (testResult.getStatus() == ITestResult.FAILURE)
        {
            TakeScreenshotOnFailure.takeScreenshot(webDriver);
        }
    }

    @AfterMethod(description = "Close FF Instance")
    public void tearDown()
    {
        try {
            webDriver.close();
            webDriver.quit();
        }catch (Exception e){
            System.out.println("Error occured while closing WebDriver");
        }finally {
            webDriver = null;
        }
    }
}