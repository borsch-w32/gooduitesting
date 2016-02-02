package ru.mail.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * Created by Sergei_Sergeenko on 1/19/2016.
 */
public class PropertiesParser
{
    String site;
    String login;
    String password;
    String testAddress;
    String testSubject;

    String screenshotPath;

    public String getSite()
    {
        return site;
    }

    public String getLogin()
    {
        return login;
    }

    public String getPassword()
    {
        return password;
    }

    public String getTestAddress()
    {
        return testAddress;
    }

    public String getTestSubject()
    {
        return testSubject;
    }

    public String getScreenshotPath()
    {
        return screenshotPath;
    }

    public PropertiesParser() throws IOException
    {
        Properties properties = new Properties();
        properties.load(new FileInputStream(new File("src/main/resources/userdata.properties")));
        site = properties.getProperty("site");
        login = properties.getProperty("login");
        password = properties.getProperty("password");
        testAddress = properties.getProperty("testAddress");
        testSubject = properties.getProperty("testSubject");
        screenshotPath = properties.getProperty("screenshot.path");
    }
}
