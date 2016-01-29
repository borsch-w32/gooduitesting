package ru.mail.patterns.factory_method;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

/**
 * Created by Sergei_Sergeenko on 1/29/2016.
 */
public class ChromeDriverCreator extends WebDriverCreator {

    @Override
    public WebDriver factoryMethod() throws IOException {
        System.setProperty("webdriver.chrome.driver", "D:\\git\\gooduitesting\\chromedriver.exe");
        ChromeDriver chromeDriver = new ChromeDriver();
        return chromeDriver;
    }
}
