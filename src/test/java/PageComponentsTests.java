import org.openqa.selenium.By;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.mail.pages.ComposeEmailPage;
import ru.mail.pages.PersonalAccountPage;
import ru.mail.utils.TakeScreenshotOnFailure;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import static org.testng.Assert.assertEquals;

/**
 * Created by Sergei_Sergeenko on 12/24/2015.
 */
public class PageComponentsTests
{
    private WebDriver webDriver;

    @BeforeMethod(description = "Open new page")
    public void setUp() throws MalformedURLException
    {
        DesiredCapabilities capability = DesiredCapabilities.firefox();
        capability.setBrowserName("firefox");
        capability.setVersion("43.0");
        capability.setPlatform(Platform.LINUX);
        webDriver = new RemoteWebDriver(new URL("http://192.168.10.4:4444/wd/hub"), capability);
        webDriver.get(PersonalAccountPage.mailUrl);
    }

    @Test(testName = "Check incoming emails page title")
    public void testTitle()
    {
        PersonalAccountPage personalAccountPage = new PersonalAccountPage(webDriver);
        personalAccountPage.enterData();
        String title = webDriver.getTitle();
        assertEquals(title, "Входящие - borsch.w32@mail.ru - Почта Mail.Ru");
    }

    @Test(testName = "Check personal mail hyperlink")
    public void testHyperLink()
    {
        PersonalAccountPage personalAccountPage = new PersonalAccountPage(webDriver);
        personalAccountPage.enterData();
        String hyperLink = webDriver.findElement(By.id("PH_user-email")).getText();
        assertEquals(hyperLink, "borsch.w32@mail.ru");
    }

    @Test(testName = "Check title of the create new email page")
    public void testNewEmailPageTitle() throws InterruptedException
    {
        PersonalAccountPage personalAccountPage = new PersonalAccountPage(webDriver);
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
            TakeScreenshotOnFailure.takeScreenshot(webDriver);
            webDriver.close();
        }
    }
}
