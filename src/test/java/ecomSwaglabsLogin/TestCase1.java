package ecomSwaglabsLogin;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import BaseClass.BaseClass;
import ObjRepo.HamburgerMenuPage;
import ObjRepo.LoginPage;
import ObjRepo.ProductPage;
import genericUtility.ExcelUtility;

public class TestCase1 extends BaseClass {

    @DataProvider(name = "ValidCredentials")
    public Object[][] validCredentials() throws EncryptedDocumentException, IOException {
        ExcelUtility eu = new ExcelUtility();
        return eu.ValidCredentials();
    }

    @Test(dataProvider = "ValidCredentials")
    public void validLoginTest(String username, String password) throws InterruptedException {
    		LoginPage lg = new LoginPage(driver);
    		lg.login(username, password);
    		Thread.sleep(3000);
    		ProductPage pp = new ProductPage(driver);
    		pp.getHamburger().click();
    		HamburgerMenuPage HM = new HamburgerMenuPage(driver);
    		HM.getLogout();
    }
    @DataProvider(name = "InvalidCredentials")
	public Object[][] InvalidCredentials() throws EncryptedDocumentException, IOException {
		ExcelUtility eu = new ExcelUtility();
	    return eu.InvalidCredentials();
	    }
	@Test(dataProvider = "InvalidCredentials",priority = 1)
	public void invalidLoginTest(String username, String password) throws InterruptedException {
	    LoginPage lp = new LoginPage(driver);
	    lp.login(username, password);

	    
	    	}
    
}