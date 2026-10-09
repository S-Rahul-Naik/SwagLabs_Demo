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

    // Open application before every login test
    @Parameters("browser")
    @BeforeMethod(alwaysRun = true)
    public void launchApplication(
            @Optional("chrome") String browser) throws Exception {

        System.out.println("========== BEFORE METHOD START ==========");

        driver = wu.launchBrowser(browser);

        driver.get(
                fu.getDataFromPropertyFile("url")
        );

        System.out.println("Login page opened successfully.");
    }

    // Close browser after every login test
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