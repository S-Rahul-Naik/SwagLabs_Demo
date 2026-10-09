package BaseClass;

import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
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
	@BeforeSuite(groups = {"SMT", "FT", "Integration", "Regression", "BVA", "System"})
	public void dbConnection() {
	    Reporter.log("Before Suite", true);
	}

	@BeforeTest(groups = {"SMT", "FT", "Integration", "Regression", "BVA", "System"})
	public void bt() {
	    Reporter.log("Before Test", true);
	}

	@Parameters("browser")
	@BeforeMethod(alwaysRun = true)
	public void navigateTOApp(@Optional("chrome") String browser) throws Exception {

	    Reporter.log("Before Method", true);

	    driver = wu.launchBrowser(browser);

	    driver.get(fu.getDataFromPropertyFile("url"));

	    LoginPage lp = new LoginPage(driver);

	    lp.login(
	        fu.getDataFromPropertyFile("username"),
	        fu.getDataFromPropertyFile("password")
	    );
	}

	@AfterMethod(groups = {"SMT", "FT", "Integration", "Regression", "BVA", "System"})
	public void closeBrowser()  {

	    if (driver != null) {
	       
	        driver.quit();
	    }
	}
	
//	@AfterClass
//	public void Logout() {
//		Reporter.log("Before Class",true);
//		driver.quit();
//	}
	
}
