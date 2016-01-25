package ru.mail.utils;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import ru.mail.pages.AbstractPage;

import java.io.File;
import java.io.IOException;

/**
 * Created by cqi on 16.1.16. Educate. Grow. Satan.
 */
public class TakeScreenshotOnFailure extends AbstractPage
{
    public TakeScreenshotOnFailure(WebDriver webDriver)
    {
        super(webDriver);
    }

    public static void takeScreenshot(WebDriver webDriver) throws IOException
    {
        PropertiesParser propertiesParser = new PropertiesParser();
        File scrFile = ((TakesScreenshot) webDriver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile, new File(propertiesParser.getScreenshotUbuntuPath()), true);
    }
}
