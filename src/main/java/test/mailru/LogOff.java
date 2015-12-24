package test.mailru;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * Created by cqi on 12.12.15.
 * Educate. Grow. Satan.
 */
public class LogOff extends AbstractPage {

    @FindBy(xpath = ".//*[@id='PH_logoutLink'][@title='выход']")
    public WebElement exit;

    LogOff(WebDriver webDriver) {
        super(webDriver);
        PageFactory.initElements(this.webDriver, this);
    }

}
