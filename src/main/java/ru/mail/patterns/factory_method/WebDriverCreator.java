package ru.mail.patterns.factory_method;

import org.openqa.selenium.WebDriver;

/**
 * Created by Sergei_Sergeenko on 1/29/2016.
 */
public abstract class WebDriverCreator {

    protected WebDriver driver;

    public abstract WebDriver factoryMethod();
}
