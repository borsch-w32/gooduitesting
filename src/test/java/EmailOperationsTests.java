import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import ru.mail.pages.*;
import ru.mail.utils.ElementHighlighter;

import java.net.MalformedURLException;
import java.net.URL;

import static org.testng.Assert.assertEquals;

/**
 * Created by cqi on 11.12.15. Educate. Grow. Satan.
 */

public class EmailOperationsTests
{
    private WebDriver webDriver;
    private final String incomingPageTitle = "Входящие - borsch.w32@mail.ru - Почта Mail.Ru";
    private final String notMatch = "Actual Result does not match expected";
    private final String expDeliverTo = "cqi90@mail.ru";
    private final String expSubject = "Test me if you can";

    @BeforeClass(description = "Open new page")
    public void setUp() throws MalformedURLException
    {
        DesiredCapabilities capability = DesiredCapabilities.firefox();
        capability.setPlatform(Platform.LINUX);
        capability.setBrowserName("firefox");
        capability.setVersion("43.0");
        webDriver = new RemoteWebDriver(new URL("http://192.168.10.4:4444/wd/hub"), capability);
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
        emailDraftsPage.lastEmail.click();
        String actDeliverTo = emailDraftsPage.emailRecepient.getText();
        String actSubject = emailDraftsPage.emailSubject.getAttribute("value");
        assertEquals(actDeliverTo, expDeliverTo, notMatch);
        assertEquals(actSubject, expSubject, notMatch);
        String actContent = (String) ((JavascriptExecutor) webDriver).executeScript("tinyMCE.activeEditor.getContent();");
        assertEquals(actContent, "qd", notMatch);
        emailDraftsPage.send.click();
        String actTo = emailDraftsPage.incoming.getText();
        assertEquals(actTo, expDeliverTo, notMatch);
    }

    @Test(testName = "Check Sent Emails")
    public void testSentEmailsFolder()
    {
        PersonalAccountPage personalAccountPage = new PersonalAccountPage(webDriver);
        personalAccountPage.enterData();
        SentEmailsPage sentEmailsPage = new SentEmailsPage(webDriver);
        sentEmailsPage.sent.click();
        assertEquals(sentEmailsPage.title, incomingPageTitle, notMatch);
    }

    @Test(testName = "Log Off")
    public void logOffFromAccount()
    {
        PersonalAccountPage personalAccountPage = new PersonalAccountPage(webDriver);
        personalAccountPage.enterData();
        LogOffPage logOffPage = new LogOffPage(webDriver);
        assertEquals(logOffPage.exit.getAttribute("text"), "выход", notMatch);
        logOffPage.exit.click();
    }

    @AfterMethod(description = "Shutdown page")
    public void shutDown()
    {
        webDriver.quit();
    }
}