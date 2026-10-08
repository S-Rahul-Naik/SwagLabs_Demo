package ecomSwaglabsLogin;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import BaseClass.LoginBaseClass;
import ObjRepo.LoginPage;
import ObjRepo.ProductPage;
import genericUtility.ExcelUtility;

public class Login_TestCase extends LoginBaseClass {


    // ============================================================
    // TC-LOGIN-01
    // Verify Username, Password and Login controls are displayed
    // ============================================================

    @Test(groups = {"FT", "SMT", "Pos"})
    public void verifyLoginControlsDisplayed() {

        LoginPage lp = new LoginPage(driver);

        Assert.assertTrue(
                lp.getUsername().isDisplayed(),
                "Username field is not displayed"
        );

        Assert.assertTrue(
                lp.getPassword().isDisplayed(),
                "Password field is not displayed"
        );

        Assert.assertTrue(
                lp.getLoginButton().isDisplayed(),
                "Login button is not displayed"
        );

        System.out.println("Login controls are displayed");
    }


    // ============================================================
    // VALID LOGIN DATA
    // ============================================================

    @DataProvider(name = "ValidCredentials")
    public Object[][] validCredentials()
            throws EncryptedDocumentException, IOException {

        ExcelUtility eu = new ExcelUtility();

        return eu.ValidCredentials();
    }


    // ============================================================
    // TC-LOGIN-02
    // Verify login with valid credentials
    // ============================================================

    @Test(
            dataProvider = "ValidCredentials",
            groups = {"FT", "SMT", "Pos"}
    )
    public void validLoginTest(
            String username,
            String password) throws InterruptedException {

        LoginPage lp = new LoginPage(driver);

        lp.login(username, password);

        ProductPage pp = new ProductPage(driver);

        Assert.assertTrue(
                pp.getCartlogo().isDisplayed(),
                "Product Page is not displayed"
        );

        System.out.println(
                "Valid login successful for: " + username
        );
    }


    // ============================================================
    // TC-LOGIN-03
    // Verify Login navigates to Product Page
    // ============================================================

    @Test(
            dataProvider = "ValidCredentials",
            groups = {"FT", "Pos"}
    )
    public void verifyLoginNavigatesToProductPage(
            String username,
            String password) throws InterruptedException {

        LoginPage lp = new LoginPage(driver);

        lp.login(username, password);

        ProductPage pp = new ProductPage(driver);

        Assert.assertEquals(
                pp.getProductsTitle(),
                "Products",
                "Login did not navigate to Product Page"
        );

        System.out.println("Product Page is displayed");
    }


    // ============================================================
    // INVALID LOGIN DATA
    // ============================================================

    @DataProvider(name = "InvalidCredentials")
    public Object[][] invalidCredentials()
            throws EncryptedDocumentException, IOException {

        ExcelUtility eu = new ExcelUtility();

        return eu.InvalidCredentials();
    }


    // ============================================================
    // TC-LOGIN-04
    // Verify login with invalid username
    //
    // TC-LOGIN-05
    // Verify login with invalid password
    //
    // Both are covered by InvalidData Excel sheet.
    // ============================================================

    @Test(
            dataProvider = "InvalidCredentials",
            groups = {"FT", "Neg"}
    )
    public void invalidLoginTest(
            String username,
            String password) throws InterruptedException {

        LoginPage lp = new LoginPage(driver);

        lp.login(username, password);

        Assert.assertTrue(
                lp.getErrormsg().isDisplayed(),
                "Error message is not displayed"
        );

        System.out.println(
                "Invalid login rejected for: " + username
        );
    }


    // ============================================================
    // BLANK LOGIN DATA
    // ============================================================

    @DataProvider(name = "BlankLoginData")
    public Object[][] blankLoginData()
            throws EncryptedDocumentException, IOException {

        ExcelUtility eu = new ExcelUtility();

        return eu.BlankLoginData();
    }


    // ============================================================
    // TC-LOGIN-06
    // Verify login with blank username
    //
    // TC-LOGIN-07
    // Verify login with blank password
    //
    // Blank values come ONLY from Excel.
    // ============================================================

    @Test(
            dataProvider = "BlankLoginData",
            groups = {"FT", "Neg"}
    )
    public void blankLoginTest(
            String username,
            String password) throws InterruptedException {

        LoginPage lp = new LoginPage(driver);

        lp.login(username, password);

        Assert.assertTrue(
                lp.getErrormsg().isDisplayed(),
                "Validation error is not displayed"
        );

        System.out.println("Blank login validation displayed");
    }


    // ============================================================
    // TC-LOGIN-08
    // Verify Login Smoke Test
    // ============================================================

    @Test(
            dataProvider = "ValidCredentials",
            groups = {"FT", "SMT", "Smoke", "Pos"}
    )
    public void loginSmokeTest(
            String username,
            String password) throws InterruptedException {

        LoginPage lp = new LoginPage(driver);

        lp.login(username, password);

        ProductPage pp = new ProductPage(driver);

        Assert.assertEquals(
                pp.getProductsTitle(),
                "Products",
                "Product Page did not open"
        );

        System.out.println("Login smoke test passed");
    }


    // ============================================================
    // TC-LOGIN-09
    // Verify Login Regression after application changes
    // ============================================================

    @Test(
            dataProvider = "ValidCredentials",
            groups = {"FT", "Regression", "Pos"}
    )
    public void loginRegressionTest(
            String username,
            String password) throws InterruptedException {

        LoginPage lp = new LoginPage(driver);

        lp.login(username, password);

        ProductPage pp = new ProductPage(driver);

        Assert.assertEquals(
                pp.getProductsTitle(),
                "Products",
                "Login regression failed"
        );

        System.out.println("Login regression test passed");
    }


    // ============================================================
    // BOUNDARY LOGIN DATA
    // ============================================================

    @DataProvider(name = "BoundaryLoginData")
    public Object[][] boundaryLoginData()
            throws EncryptedDocumentException, IOException {

        ExcelUtility eu = new ExcelUtility();

        return eu.BoundaryLoginData();
    }


    // ============================================================
    // TC-LOGIN-10
    // Verify Login input at boundary conditions
    // ============================================================

    @Test(
            dataProvider = "BoundaryLoginData",
            groups = {"FT", "BVA"}
    )
    public void loginBoundaryTest(
            String username,
            String password) throws InterruptedException {

        LoginPage lp = new LoginPage(driver);

        lp.login(username, password);

        /*
         * Boundary data is expected to be handled without
         * application crash.
         *
         * If authentication fails, error message should appear.
         * If authentication succeeds, Product Page should appear.
         */

        boolean productPageDisplayed = false;
        boolean errorDisplayed = false;

        try {

            ProductPage pp = new ProductPage(driver);

            productPageDisplayed =
                    pp.getProductsTitle().equals("Products");

        } catch (Exception e) {

            productPageDisplayed = false;
        }

        try {

            errorDisplayed =
                    lp.getErrormsg().isDisplayed();

        } catch (Exception e) {

            errorDisplayed = false;
        }

        Assert.assertTrue(
                productPageDisplayed || errorDisplayed,
                "Boundary input was not handled correctly"
        );

        System.out.println("Boundary login input handled successfully");
    }
}