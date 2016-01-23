package ru.mail.patterns;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

/**
 * Created by cqi on 23.1.16. Educate. Grow. Satan.
 */
public class WebDriverSingleton
{
    protected static WebDriver webDriver;

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
}
