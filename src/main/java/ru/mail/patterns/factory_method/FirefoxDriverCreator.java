package ru.mail.patterns.factory_method;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

/**
 * Created by Sergei_Sergeenko on 1/29/2016.
 */
public class FirefoxDriverCreator extends WebDriverCreator
{

    @Override
    public WebDriver factoryMethod()
    {
        WebDriver webDriver = new FirefoxDriver();
        return webDriver;
    }
}
