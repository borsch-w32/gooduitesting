package ru.mail.patterns.singleton;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

/**
 * Created by cqi on 24.1.16. Educate. Grow. Satan.
 */
public class WebDriverSingleton
{
    public static WebDriver webDriver;

    private WebDriverSingleton()
    {
    }

    public static WebDriver getWebDriverInstance()
    {
        if (null == webDriver)
        {
            webDriver = new FirefoxDriver();
        }
        return webDriver;
    }

    public void tearDown()
    {
        try
        {
            webDriver.close();
            webDriver.quit();
        }
        catch (Exception e)
        {
            System.out.println("Error occurred while closing WebDriver");
        }
        finally
        {
            webDriver = null;
        }
    }
}