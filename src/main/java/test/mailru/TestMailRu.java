package test.mailru;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

/**
 * Created by cqi on 11.12.15.
 * Educate. Grow. Satan.
 */

public class TestMailRu {

    private WebDriver webDriver;
    private final String notMatch = "Actual Result does not match expected";


    EmailDrafts emailDrafts = new EmailDrafts(webDriver);

    @BeforeMethod(description = "Open new page")
    public void setUp() {
        webDriver = new FirefoxDriver();
        webDriver.get(PersonalAccountPage.mailUrl);

    }

    @Test(testName = "Check incoming emails page title")
    public void testTitle() {
        PersonalAccountPage personalAccountPage = new PersonalAccountPage(webDriver);
        personalAccountPage.enterData();
        String title = webDriver.getTitle();
        assertEquals(title, "Входящие - borsch.w32@mail.ru - Почта Mail.Ru", notMatch);
    }

    @Test(testName = "Check personal mail hyperlink")
    public void testHyperLink() {
        PersonalAccountPage personalAccountPage = new PersonalAccountPage(webDriver);
        personalAccountPage.enterData();
        String hyperLink = webDriver.findElement(By.id("PH_user-email")).getText();
        assertEquals(hyperLink, "borsch.w32@mail.ru", notMatch);
    }

    @Test(testName = "Check title of the create new email page")
    public void testNewEmailPageTitle() throws InterruptedException {
        PersonalAccountPage personalAccountPage = new PersonalAccountPage(webDriver);
        personalAccountPage.enterData();
        ComposeEmail composeEmail = new ComposeEmail(webDriver);
        composeEmail.fillInEmailAndSave();
        String title = webDriver.getTitle();
        assertEquals(title, "Новое письмо - borsch.w32@mail.ru - Почта Mail.Ru", notMatch);
    }

    @Test(testName = "Check last email from drafts")
    public void testLastEmailInDrafts() throws InterruptedException {
        PersonalAccountPage personalAccountPage = new PersonalAccountPage(webDriver);
        personalAccountPage.enterData();
        ComposeEmail composeEmail = new ComposeEmail(webDriver);
        composeEmail.fillInEmailAndSave();
        EmailDrafts emailDrafts = new EmailDrafts(webDriver);
        emailDrafts.drafts.click();
        emailDrafts.lastEmail.click();
        String actRec = emailDrafts.emailRecepient.getText();
        String actSub = emailDrafts.emailSubject.getAttribute("value");
        assertEquals(actRec, "cqi90@mail.ru", notMatch);
        assertEquals(actSub, "Test me if you can", notMatch);
        emailDrafts.send.click();
    }

    @Test(testName = "Check Sent Emails")
    public void testSentEmailsFolder() {
        PersonalAccountPage personalAccountPage = new PersonalAccountPage(webDriver);
        personalAccountPage.enterData();
        SentEmails sentEmails = new SentEmails(webDriver);
        sentEmails.sent.click();
        assertEquals(sentEmails.title, "Входящие - borsch.w32@mail.ru - Почта Mail.Ru", notMatch);
    }

    @Test(testName = "Log Off")
    public void logOffFromAccount() {
        PersonalAccountPage personalAccountPage = new PersonalAccountPage(webDriver);
        personalAccountPage.enterData();
        LogOff logOff = new LogOff(webDriver);
        assertEquals(logOff.exit.getAttribute("text"), "выход", notMatch);
        logOff.exit.click();
    }

    @AfterMethod(description = "Shutdown page")
    public void shutDown() {
        webDriver.quit();

    }
}