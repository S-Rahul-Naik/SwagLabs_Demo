package BaseClass;

import org.openqa.selenium.WebDriver;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import ObjRepo.LoginPage;
import genericUtility.FileUtility;
import genericUtility.WebdriverUtility;

public class BaseClass {

    public WebDriver driver = null;

    WebdriverUtility wu = new WebdriverUtility();
    FileUtility fu = new FileUtility();

    // Runs before the complete test suite
    @BeforeSuite(alwaysRun = true)
    public void dbConnection() {

        Reporter.log("Before Suite", true);
    }

    // Runs before the test tag in XML
    @BeforeTest(alwaysRun = true)
    public void bt() {

        Reporter.log("Before Test", true);
    }

    // Launch browser and login before every test
    @Parameters("browser")
    @BeforeMethod(alwaysRun = true)
    public void navigateTOApp(
            @Optional("chrome") String browser) throws Exception {

        Reporter.log("Before Method", true);

        driver = wu.launchBrowser(browser);

        driver.get(
                fu.getDataFromPropertyFile("url")
        );

        LoginPage lp = new LoginPage(driver);

        lp.login(
                fu.getDataFromPropertyFile("username"),
                fu.getDataFromPropertyFile("password")
        );
    }

    // Close browser after every test
    @AfterMethod(alwaysRun = true)
    public void closeBrowser() {

        System.out.println("========== AFTER METHOD START ==========");

        if (driver != null) {

            System.out.println("Closing browser...");

            driver.quit();

            driver = null;

            System.out.println("Browser closed successfully.");
        }

        System.out.println("========== AFTER METHOD END ==========");
    }
}