package ecomSwaglabsCheckout;

import org.testng.Assert;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import BaseClass.BaseClass;
import ObjRepo.CheckOutPageComplete;
import ObjRepo.CheckoutPageOverview;
import ObjRepo.CheckoutPageYourInfo;
import ObjRepo.ProductPage;
import ObjRepo.cartPage;
import genericUtility.ExcelUtility;

public class Checkout_TestCase extends BaseClass {

    ExcelUtility eu = new ExcelUtility();


    // =========================================================
    // Common navigation
    // Product → Cart → Checkout Information
    // =========================================================

    private void goToCheckoutInformation() {

        ProductPage pp = new ProductPage(driver);

        pp.addFirstProductToCart();
        pp.clickCart();

        cartPage cp = new cartPage(driver);

        cp.clickCheckout();
    }


    // =========================================================
    // TC-CHK-01
    // Verify Checkout Information fields and controls
    // =========================================================

    @Test(priority=1,groups = {"FT", "Pos"})
    public void verifyCheckoutInformationFields() {

        goToCheckoutInformation();

        CheckoutPageYourInfo cp =
                new CheckoutPageYourInfo(driver);

        Assert.assertEquals(
                cp.getCheckoutTitle(),
                "Checkout: Your Information");

        Assert.assertTrue(
                cp.isFirstNameDisplayed());

        Assert.assertTrue(
                cp.isLastNameDisplayed());

        Assert.assertTrue(
                cp.isPostalCodeDisplayed());

        Assert.assertTrue(
                cp.isContinueDisplayed());

        Assert.assertTrue(
                cp.isCancelDisplayed());
    }


    // =========================================================
    // TC-CHK-02
    // Verify valid checkout information is accepted
    // =========================================================

    @Test(priority=2,dataProvider = "positiveCheckoutData",groups = {"FT", "Pos"})
    public void verifyValidCheckoutInformation(
            String firstName,
            String lastName,
            String postalCode)
            throws Exception {

        goToCheckoutInformation();

        CheckoutPageYourInfo cp =
                new CheckoutPageYourInfo(driver);

        cp.Info(
                firstName,
                lastName,
                postalCode);

        CheckoutPageOverview overview =
                new CheckoutPageOverview(driver);

        Assert.assertEquals(
                overview.getCheckoutTitle(),
                "Checkout: Overview");
    }


    // =========================================================
    // TC-CHK-03
    // Verify Checkout Overview product visibility and controls
    // =========================================================

    @Test(priority=3,dataProvider = "positiveCheckoutData",groups = {"FT", "Pos"})
    public void verifyCheckoutOverview(
            String firstName,
            String lastName,
            String postalCode)
            throws Exception {

        goToCheckoutInformation();

        CheckoutPageYourInfo cp =
                new CheckoutPageYourInfo(driver);

        cp.Info(
                firstName,
                lastName,
                postalCode);

        CheckoutPageOverview overview =
                new CheckoutPageOverview(driver);

        Assert.assertEquals(
                overview.getCheckoutTitle(),
                "Checkout: Overview");

        Assert.assertTrue(
                overview.getaddedProducts().size() > 0);

        Assert.assertTrue(
                overview.isFinishDisplayed());

        Assert.assertTrue(
                overview.isCancelDisplayed());
    }


    // =========================================================
    // TC-CHK-04
    // Verify Checkout Complete page and Back Home
    // =========================================================

    @Test(priority=4,dataProvider = "positiveCheckoutData",groups = {"FT", "Pos"})
    public void verifyCheckoutCompleteAndBackHome(
            String firstName,
            String lastName,
            String postalCode)
            throws Exception {

        goToCheckoutInformation();

        CheckoutPageYourInfo cp =
                new CheckoutPageYourInfo(driver);

        cp.Info(
                firstName,
                lastName,
                postalCode);

        CheckoutPageOverview overview =
                new CheckoutPageOverview(driver);

        overview.clickFinish();

        CheckOutPageComplete complete =
                new CheckOutPageComplete(driver);

        Assert.assertEquals(
                complete.getCheckOutTitle(),
                "Checkout: Complete!");

        Assert.assertEquals(
                complete.getThankYou(),
                "Thank you for your order!");

        complete.clickBackHome();

        ProductPage pp =
                new ProductPage(driver);

        Assert.assertEquals(
                pp.getProductsTitle(),
                "Products");
    }


    // =========================================================
    // TC-CHK-05
    // Checkout Information → Overview integration
    // =========================================================

    @Test(priority=5,dataProvider = "positiveCheckoutData",groups = {"Integration"})
    public void verifyCheckoutInformationToOverview(
            String firstName,
            String lastName,
            String postalCode)
            throws Exception {

        goToCheckoutInformation();

        CheckoutPageYourInfo cp =
                new CheckoutPageYourInfo(driver);

        cp.Info(
                firstName,
                lastName,
                postalCode);

        CheckoutPageOverview overview =
                new CheckoutPageOverview(driver);

        Assert.assertEquals(
                overview.getCheckoutTitle(),
                "Checkout: Overview");

        Assert.assertTrue(
                overview.getaddedProducts().size() > 0);
    }


    // =========================================================
    // TC-CHK-06
    // Overview → Complete integration
    // =========================================================

    @Test(priority=6,dataProvider = "positiveCheckoutData",groups = {"Integration"})
    public void verifyOverviewToComplete(
            String firstName,
            String lastName,
            String postalCode)
            throws Exception {

        goToCheckoutInformation();

        CheckoutPageYourInfo cp =
                new CheckoutPageYourInfo(driver);

        cp.Info(
                firstName,
                lastName,
                postalCode);

        CheckoutPageOverview overview =
                new CheckoutPageOverview(driver);

        overview.clickFinish();

        CheckOutPageComplete complete =
                new CheckOutPageComplete(driver);

        Assert.assertEquals(
                complete.getThankYou(),
                "Thank you for your order!");
    }


    // =========================================================
    // TC-CHK-07
    // Complete → Home integration
    // =========================================================

    @Test(priority=7,dataProvider = "positiveCheckoutData",groups = {"Integration"})
    public void verifyCompleteToHome(
            String firstName,
            String lastName,
            String postalCode)
            throws Exception {

        goToCheckoutInformation();

        CheckoutPageYourInfo cp =
                new CheckoutPageYourInfo(driver);

        cp.Info(
                firstName,
                lastName,
                postalCode);

        CheckoutPageOverview overview =
                new CheckoutPageOverview(driver);

        overview.clickFinish();

        CheckOutPageComplete complete =
                new CheckOutPageComplete(driver);

        complete.clickBackHome();

        ProductPage pp =
                new ProductPage(driver);

        Assert.assertEquals(
                pp.getProductsTitle(),
                "Products");
    }


    // =========================================================
    // TC-CHK-08
    // Blank First Name
    // =========================================================

    @Test(priority=8,dataProvider = "blankFirstNameData",groups = {"FT", "Neg"})
    public void verifyBlankFirstName(
            String firstName,
            String lastName,
            String postalCode) {

        goToCheckoutInformation();

        CheckoutPageYourInfo cp =
                new CheckoutPageYourInfo(driver);

        cp.enterFirstName(firstName);
        cp.enterLastName(lastName);
        cp.enterPostalCode(postalCode);

        cp.clickContinue();

        Assert.assertEquals(
                cp.getCheckoutTitle(),
                "Checkout: Your Information");

        Assert.assertTrue(
                cp.isErrorDisplayed());
    }


    // =========================================================
    // TC-CHK-09
    // Blank Last Name
    // =========================================================

    @Test(priority=9,dataProvider = "blankLastNameData",groups = {"FT", "Neg"})
    public void verifyBlankLastName(
            String firstName,
            String lastName,
            String postalCode) {

        goToCheckoutInformation();

        CheckoutPageYourInfo cp =
                new CheckoutPageYourInfo(driver);

        cp.enterFirstName(firstName);
        cp.enterLastName(lastName);
        cp.enterPostalCode(postalCode);

        cp.clickContinue();

        Assert.assertEquals(
                cp.getCheckoutTitle(),
                "Checkout: Your Information");

        Assert.assertTrue(
                cp.isErrorDisplayed());
    }


    // =========================================================
    // TC-CHK-10
    // Invalid / Blank Postal Code
    // =========================================================

    @Test(priority=10,dataProvider = "invalidPostalCodeData",groups = {"FT", "Neg"})
    public void verifyInvalidPostalCode(
            String firstName,
            String lastName,
            String postalCode) {

        goToCheckoutInformation();

        CheckoutPageYourInfo cp =
                new CheckoutPageYourInfo(driver);

        cp.enterFirstName(firstName);
        cp.enterLastName(lastName);
        cp.enterPostalCode(postalCode);

        cp.clickContinue();

        Assert.assertEquals(
                cp.getCheckoutTitle(),
                "Checkout: Your Information");

        Assert.assertTrue(
                cp.isErrorDisplayed());
    }


    // =========================================================
    // TC-CHK-11
    // Checkout regression flow
    // =========================================================

    @Test(priority=11,dataProvider = "positiveCheckoutData",groups = {"Regression", "Pos"})
    public void checkoutRegressionFlow(
            String firstName,
            String lastName,
            String postalCode)
            throws Exception {

        goToCheckoutInformation();

        CheckoutPageYourInfo cp =
                new CheckoutPageYourInfo(driver);

        cp.Info(
                firstName,
                lastName,
                postalCode);

        CheckoutPageOverview overview =
                new CheckoutPageOverview(driver);

        Assert.assertTrue(
                overview.getaddedProducts().size() > 0);

        overview.clickFinish();

        CheckOutPageComplete complete =
                new CheckOutPageComplete(driver);

        Assert.assertEquals(
                complete.getThankYou(),
                "Thank you for your order!");
    }


    // =========================================================
    // TC-CHK-12
    // Complete system purchase flow
    // =========================================================

    @Test(priority=12,dataProvider = "positiveCheckoutData",groups = {"System"})
    public void completeSystemPurchaseFlow(
            String firstName,
            String lastName,
            String postalCode)
            throws Exception {

        goToCheckoutInformation();

        CheckoutPageYourInfo cp =
                new CheckoutPageYourInfo(driver);

        cp.Info(
                firstName,
                lastName,
                postalCode);

        CheckoutPageOverview overview =
                new CheckoutPageOverview(driver);

        overview.clickFinish();

        CheckOutPageComplete complete =
                new CheckOutPageComplete(driver);

        Assert.assertEquals(
                complete.getThankYou(),
                "Thank you for your order!");
    }


    // =========================================================
    // TC-CHK-13
    // Boundary Value Analysis
    // =========================================================

    @Test(priority=13,dataProvider = "boundaryCheckoutData",groups = {"BVA"})
    public void verifyCheckoutBoundaryValues(
            String firstName,
            String lastName,
            String postalCode) {

        goToCheckoutInformation();

        CheckoutPageYourInfo cp =
                new CheckoutPageYourInfo(driver);

        cp.enterFirstName(firstName);
        cp.enterLastName(lastName);
        cp.enterPostalCode(postalCode);

        cp.clickContinue();

        String currentUrl =
                driver.getCurrentUrl();

        Assert.assertTrue(
                currentUrl.contains("checkout"));
    }


    // =========================================================
    // POSITIVE DATA PROVIDER
    // =========================================================

    @DataProvider(name = "positiveCheckoutData")
    public Object[][] positiveCheckoutData()
            throws Exception {

        return eu.CheckoutPositiveData();
    }


    // =========================================================
    // NEGATIVE DATA PROVIDER
    // =========================================================

    @DataProvider(name = "blankFirstNameData")
    public Object[][] blankFirstNameData()
            throws Exception {

        return eu.CheckoutBlankFirstNameData();
    }


    @DataProvider(name = "blankLastNameData")
    public Object[][] blankLastNameData()
            throws Exception {

        return eu.CheckoutBlankLastNameData();
    }


    @DataProvider(name = "invalidPostalCodeData")
    public Object[][] invalidPostalCodeData()
            throws Exception {

        return eu.CheckoutInvalidPostalCodeData();
    }


    // =========================================================
    // BOUNDARY DATA PROVIDER
    // =========================================================

    @DataProvider(name = "boundaryCheckoutData")
    public Object[][] boundaryCheckoutData()
            throws Exception {

        return eu.CheckoutBoundaryData();
    }
}