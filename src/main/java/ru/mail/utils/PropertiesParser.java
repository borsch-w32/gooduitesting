package ru.mail.utils;

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

    public PropertiesParser() throws IOException
    {
        Properties properties = new Properties();
        FileInputStream propertiesFile = new FileInputStream(
//                "D:\\git\\gooduitesting\\src\\main\\resources\\userdata.properties");
                "/home/cqi/IdeaProjects/gooduitesting/src/main/resources/userdata.properties");
        properties.load(propertiesFile);
        site = properties.getProperty("site");
        login = properties.getProperty("login");
        password = properties.getProperty("password");
        testAddress = properties.getProperty("testAddress");
        testSubject = properties.getProperty("testSubject");
    }
}
