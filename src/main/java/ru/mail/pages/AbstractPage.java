package ru.mail.pages;

import org.openqa.selenium.WebDriver;

/**
 * Created by cqi on 11.12.15. Educate. Grow. Satan.
 */
class AbstractPage
{
    protected WebDriver webDriver;
    AbstractPage(WebDriver webDriver)
    {
        this.webDriver = webDriver;
    }
}
