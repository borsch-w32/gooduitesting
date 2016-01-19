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
    String emailTo;
    String emailSubject;
    String addressTo;
    String subjectTo;

    public String getAddressTo()
    {
        return addressTo;
    }

    public String getSubjectTo()
    {
        return subjectTo;
    }

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

    public String getEmailTo()
    {
        return emailTo;
    }

    public String getEmailSubject()
    {
        return emailSubject;
    }

    public PropertiesParser() throws IOException
    {
        Properties properties = new Properties();
        FileInputStream propertiesFile = new FileInputStream(
                // "D:\\git\\gooduitesting\\src\\main\\resources\\userdata.properties");
                "/home/cqi/IdeaProjects/gooduitesting/src/main/resources/userdata.properties");
        properties.load(propertiesFile);
        site = properties.getProperty("site");
        login = properties.getProperty("login");
        password = properties.getProperty("password");
        emailTo = properties.getProperty("emailTo");
        emailSubject = properties.getProperty("emailSubject");
        addressTo = properties.getProperty("testAddress");
        subjectTo = properties.getProperty("testSubject");
    }
}
