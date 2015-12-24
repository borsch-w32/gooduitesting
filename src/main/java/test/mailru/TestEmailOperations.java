package test.mailru;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

/**
 * Created by cqi on 11.12.15.
 * Educate. Grow. Satan.
 */

public class TestEmailOperations {

    private WebDriver webDriver;
    private final String incomingPageTitle = "Входящие - borsch.w32@mail.ru - Почта Mail.Ru";
    private final String notMatch = "Actual Result does not match expected";
    private final String expDeliverTo = "cqi90@mail.ru";
    private final String expSubject = "Test me if you can";

    @BeforeMethod(description = "Open new page")
    public void setUp() {
        webDriver = new FirefoxDriver();
        webDriver.get(PersonalAccountPage.mailUrl);
    }

    @Test(testName = "Check last email from drafts")
    public void testLastEmailInDrafts() throws InterruptedException {
        PersonalAccountPage personalAccountPage = new PersonalAccountPage(webDriver);
        personalAccountPage.enterData();
        ComposeEmail composeEmail = new ComposeEmail(webDriver);
        composeEmail.fillInEmailAndSave();
        EmailDrafts emailDrafts = new EmailDrafts(webDriver);
        Actions clickDrafts = new Actions(webDriver);
        clickDrafts.moveToElement(emailDrafts.drafts).click().build().perform();
        emailDrafts.lastEmail.click();
        String actDeliverTo = emailDrafts.emailRecepient.getText();
        String actSubject = emailDrafts.emailSubject.getAttribute("value");
        assertEquals(actDeliverTo, expDeliverTo, notMatch);
        assertEquals(actSubject, expSubject, notMatch);
        String actContent = (String) ((JavascriptExecutor)webDriver).executeScript("tinyMCE.activeEditor.getContent();");
        assertEquals(actContent, "qd", notMatch);
        emailDrafts.send.click();
        String actTo = emailDrafts.incoming.getText();
        assertEquals(actTo, expDeliverTo, notMatch);
    }

    @Test(testName = "Check Sent Emails")
    public void testSentEmailsFolder() {
        PersonalAccountPage personalAccountPage = new PersonalAccountPage(webDriver);
        personalAccountPage.enterData();
        SentEmails sentEmails = new SentEmails(webDriver);
        sentEmails.sent.click();
        assertEquals(sentEmails.title, incomingPageTitle, notMatch);
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