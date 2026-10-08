package BaseClass;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import genericUtility.FileUtility;
import genericUtility.WebdriverUtility;

public class LoginBaseClass {

    public static WebDriver driver;

    FileUtility fu = new FileUtility();
    WebdriverUtility wu = new WebdriverUtility();

    @BeforeMethod
    public void launchApplication() throws Exception {

        driver = wu.launchBrowser("chrome");

        driver.get(
            fu.getDataFromPropertyFile("url")
        );
    }

    @AfterMethod
    public void closeBrowser() throws InterruptedException {

        if (driver != null) {
            Thread.sleep(2000);
            driver.quit();
            driver = null;
        }
    }
}