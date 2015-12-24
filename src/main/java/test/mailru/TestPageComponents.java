package test.mailru;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

/**
 * Created by Sergei_Sergeenko on 12/24/2015.
 */
public class TestPageComponents {

    private final String incomingPageTitle = "Входящие - borsch.w32@mail.ru - Почта Mail.Ru";
    private final String notMatch = "Actual Result does not match expected";
    private final String personalAccountLink = "borsch.w32@mail.ru";
    private final String newEmailPageTitle = "Новое письмо - borsch.w32@mail.ru - Почта Mail.Ru";

    private WebDriver webDriver;

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
        assertEquals(title, incomingPageTitle, notMatch);
    }

    @Test(testName = "Check personal mail hyperlink")
    public void testHyperLink() {
        PersonalAccountPage personalAccountPage = new PersonalAccountPage(webDriver);
        personalAccountPage.enterData();
        String hyperLink = webDriver.findElement(By.id("PH_user-email")).getText();
        assertEquals(hyperLink, personalAccountLink, notMatch);
    }

    @Test(testName = "Check title of the create new email page")
    public void testNewEmailPageTitle() throws InterruptedException {
        PersonalAccountPage personalAccountPage = new PersonalAccountPage(webDriver);
        personalAccountPage.enterData();
        ComposeEmail composeEmail = new ComposeEmail(webDriver);
        composeEmail.fillInEmailAndSave();
        String actualNewEmailPageTitle = webDriver.getTitle();
        assertEquals(actualNewEmailPageTitle, newEmailPageTitle, notMatch);
    }

    @AfterMethod(description = "Shutdown page")
    public void shutDown() {
        webDriver.quit();

    }
}
