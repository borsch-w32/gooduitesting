package ru.mail.utils;

import org.openqa.selenium.WebDriver;

import ru.mail.pages.AbstractPage;

/**
 * Created by cqi on 20.1.16. Educate. Grow. Satan.
 */
public class TabSwitcher extends AbstractPage
{

    public TabSwitcher(WebDriver webDriver)
    {
        super(webDriver);
    }

    public void tabsSwitcher()
    {
        webDriver.getWindowHandle();
        for (String winHandle : webDriver.getWindowHandles())
        {
            webDriver.switchTo().window(winHandle);
        }
    }
}
