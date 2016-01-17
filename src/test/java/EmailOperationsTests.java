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
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import ru.mail.pages.*;
import ru.mail.utils.ElementHighlighter;
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

    @BeforeClass(description = "Open new page")
    public void setUp() throws MalformedURLException
    {
//        uncomment the lines below to run tests in GRID
//        DesiredCapabilities capability = DesiredCapabilities.firefox();
//        capability.setPlatform(Platform.LINUX);
//        capability.setBrowserName("firefox");
//        capability.setVersion("43.0");
//        webDriver = new RemoteWebDriver(new URL("http://192.168.10.4:4444/wd/hub"), capability);
//        webDriver.get(PersonalAccountPage.mailUrl);
        webDriver = new FirefoxDriver();
        webDriver.get(PersonalAccountPage.mailUrl);
    }

    @Test(testName = "Check last email from drafts")
    public void testLastEmailInDrafts() throws InterruptedException
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
        WebElement draftsHyperlink = (new WebDriverWait(webDriver, 10))
                .until(ExpectedConditions.presenceOfElementLocated(By.xpath(".//*[@id='b-nav_folders']//div/a/span[contains(text(), 'Черновики')]")));

        emailDraftsPage.lastEmail.click();
        String actDeliverTo = emailDraftsPage.emailRecepient.getText();
        String actSubject = emailDraftsPage.emailSubject.getAttribute("value");
        assertEquals(actDeliverTo, "cqi90@mail.ru");
        assertEquals(actSubject, "Test me if you can");

        emailDraftsPage.send.click();
        String actTo = emailDraftsPage.incoming.getText();
        assertEquals(actTo, "cqi90@mail.ru");
    }

    @Test(testName = "Check Sent Emails")
    public void testSentEmailsFolder()
    {
        PersonalAccountPage personalAccountPage = new PersonalAccountPage(webDriver);
        personalAccountPage.enterData();
        SentEmailsPage sentEmailsPage = new SentEmailsPage(webDriver);
        sentEmailsPage.sent.click();
        assertEquals(sentEmailsPage.title, "Входящие - borsch.w32@mail.ru - Почта Mail.Ru");
    }

    @Test(testName = "Log Off")
    public void logOffFromAccount()
    {
        PersonalAccountPage personalAccountPage = new PersonalAccountPage(webDriver);
        personalAccountPage.enterData();
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
}