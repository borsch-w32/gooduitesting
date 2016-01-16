package ru.mail.pages;

import org.openqa.selenium.WebDriver;

/**
 * Created by cqi on 11.12.15. Educate. Grow. Satan.
 */
public class AbstractPage
{
    public WebDriver webDriver;
    public AbstractPage(WebDriver webDriver)
    {
        this.webDriver = webDriver;
    }
}
