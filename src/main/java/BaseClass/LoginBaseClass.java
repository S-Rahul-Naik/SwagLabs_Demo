package BaseClass;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import genericUtility.FileUtility;
import genericUtility.WebdriverUtility;

public class LoginBaseClass {

    public static WebDriver driver;

    FileUtility fu = new FileUtility();
    WebdriverUtility wu = new WebdriverUtility();

    @Parameters("browser")
    @BeforeMethod(groups = {"SMT", "FT", "Integration", "Regression", "BVA", "System"})
    public void launchApplication(@Optional("chrome")String browser) throws Exception {

        driver = wu.launchBrowser(browser);

        driver.get(
            fu.getDataFromPropertyFile("url")
        );
    }

    @AfterMethod(groups = {"SMT", "FT", "Integration", "Regression", "BVA", "System"})
    public void closeBrowser() throws InterruptedException {

        if (driver != null) {
            
            driver.quit();
            driver = null;
        }
    }
}