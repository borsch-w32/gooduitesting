package ru.mail.patterns.singleton;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.internal.ProfilesIni;

/**
 * Created by cqi on 24.1.16. Educate. Grow. Satan.
 */
public class WebDriverSingleton
{

    private static WebDriver webDriver;

    private WebDriverSingleton()
    {
    }

    public static WebDriver getWebDriverInstance()
    {
        if (null == webDriver)
        {
            ProfilesIni profile = new ProfilesIni();
            FirefoxProfile myprofile = profile.getProfile("uitesting");
            webDriver = new FirefoxDriver(myprofile);
        }
        return webDriver;
    }
}