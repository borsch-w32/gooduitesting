package ru.mail.runner;

import java.util.ArrayList;
import java.util.List;

import org.testng.TestNG;
import org.testng.xml.XmlClass;
import org.testng.xml.XmlSuite;
import org.testng.xml.XmlTest;

/**
 * Created by cqi on 17.1.16. Educate. Grow. Satan.
 */
public class TestNGRunner
{
    public static void main(String[] args)
    {
        XmlSuite suite = new XmlSuite();
        suite.setName("Test mail.ru");

        XmlTest test = new XmlTest(suite);
        test.setName("testMailRu");
        List<XmlClass> classes = new ArrayList<XmlClass>();
        classes.add(new XmlClass("ru.mail.tests.EmailOperationsTests"));
        classes.add(new XmlClass("ru.mail.tests.PageComponentsTests"));
        test.setXmlClasses(classes);

        List<XmlSuite> suites = new ArrayList<XmlSuite>();
        suites.add(suite);
        TestNG testNG = new TestNG();
        testNG.setXmlSuites(suites);
        testNG.run();
    }
}
