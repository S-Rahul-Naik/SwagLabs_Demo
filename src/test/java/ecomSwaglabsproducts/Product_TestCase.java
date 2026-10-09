package ecomSwaglabsproducts;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.poi.EncryptedDocumentException;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import BaseClass.BaseClass;
import ObjRepo.ProductPage;
import ObjRepo.cartPage;
import genericUtility.ExcelUtility;

public class Product_TestCase extends BaseClass {

    // =========================================================
    // TC-PROD-01
    // Verify products are visible on Product Page
    // =========================================================
    @Test(priority=1,groups = {"FT", "Pos"})
    public void verifyProductsAreVisible() {

        ProductPage pp = new ProductPage(driver);

        Assert.assertEquals(
                pp.getProductsTitle(),
                "Products",
                "Product Page is not displayed"
        );

        Assert.assertFalse(
                pp.getProductsList().isEmpty(),
                "No products are displayed"
        );

        Assert.assertFalse(
                pp.getProductNames().isEmpty(),
                "Product names are not displayed"
        );

        Assert.assertFalse(
                pp.getProductPrices().isEmpty(),
                "Product prices are not displayed"
        );

        Reporter.log("Products, names and prices are displayed", true);
    }


    // =========================================================
    // Product names from Excel
    // =========================================================
    @DataProvider(name = "ProductName")
    public Object[] ProductName() throws EncryptedDocumentException, IOException {

        ExcelUtility eu = new ExcelUtility();

        return eu.ProductNames();
    }


    // =========================================================
    // TC-PROD-02
    // Verify Add to Cart and Cart Logo behavior
    // =========================================================
    @Test(priority=2,
            dataProvider = "ProductName",
            	groups = {"FT", "Pos"}
    )
    public void verifyAddToCartAndCartLogo(String productName) {

        ProductPage pp = new ProductPage(driver);

        pp.addProductToCart(productName);

        Assert.assertTrue(
                pp.isCartBadgeDisplayed(),
                "Cart quantity badge is not displayed"
        );

        Assert.assertEquals(
                pp.getCartBadgeText(),
                "1",
                "Cart quantity is not 1"
        );

        Reporter.log(
                "Product added successfully: " + productName,
                true
        );
    }


    // =========================================================
    // TC-PROD-03
    // Product Page -> Cart Page
    // =========================================================
    @Test(priority=3,
            dataProvider = "ProductName",
            	groups = {"Integration"}
    )
    public void verifyProductPageToCartPage(String productName) {

        ProductPage pp = new ProductPage(driver);

        pp.addProductToCart(productName);

        pp.clickCart();

        cartPage cp = new cartPage(driver);

        Assert.assertEquals(
                cp.getCarttitle(),
                "Your Cart",
                "Cart Page is not displayed"
        );

        Assert.assertEquals(
                cp.getCartItemCount(),
                1,
                "Selected product is not present in cart"
        );

        Reporter.log(
                "Product successfully navigated to Cart: "
                        + productName,
                true
        );
    }


    // =========================================================
    // TC-PROD-04
    // Verify Add to Cart after product removal
    // =========================================================
    @Test(priority=4,
            dataProvider = "ProductName",
            	groups = {"FT", "Neg"}
    )
    public void verifyAddToCartAfterProductRemoval(String productName) {

        ProductPage pp = new ProductPage(driver);

        // Add product
        pp.addProductToCart(productName);

        // Go to Cart
        pp.clickCart();

        cartPage cp = new cartPage(driver);

        Assert.assertEquals(
                cp.getCartItemCount(),
                1,
                "Product was not added to cart"
        );

        // Remove product
        cp.getRemoveButtons().get(0).click();

        Assert.assertEquals(
                cp.getCartItemCount(),
                0,
                "Product was not removed from cart"
        );

        // Return to Product Page
        cp.getContinuebtn().click();

        ProductPage productPage = new ProductPage(driver);

        // Add same product again
        productPage.addProductToCart(productName);

        Assert.assertTrue(
                productPage.isCartBadgeDisplayed(),
                "Product could not be added again"
        );

        Reporter.log(
                "Product successfully added again after removal",
                true
        );
    }


    // =========================================================
    // TC-PROD-05
    // Verify cart navigation without selected product
    // =========================================================
    @Test(priority=5,groups = {"FT", "Neg"})
    public void verifyEmptyCartNavigation() {

        ProductPage pp = new ProductPage(driver);

        Assert.assertEquals(
                pp.getProductsTitle(),
                "Products",
                "Product Page is not displayed"
        );

        // Cart should be empty initially
        pp.clickCart();

        cartPage cp = new cartPage(driver);

        Assert.assertEquals(
                cp.getCartItemCount(),
                0,
                "Cart is not empty"
        );

        Assert.assertEquals(
                cp.getCarttitle(),
                "Your Cart",
                "Cart Page is not displayed"
        );

        Reporter.log("Empty cart handled successfully", true);
    }


    // =========================================================
    // TC-PROD-06
    // Product Page Smoke Test
    // =========================================================
    @Test(priority=6,
            dataProvider = "ProductName",
            	groups = {"SMT", "Pos"}
    )
    public void productPageSmokeTest(String productName) {

        ProductPage pp = new ProductPage(driver);

        Assert.assertFalse(
                pp.getProductsList().isEmpty(),
                "Products are not displayed"
        );

        pp.addProductToCart(productName);

        Assert.assertTrue(
                pp.isCartBadgeDisplayed(),
                "Cart Logo did not update"
        );

        Reporter.log("Product Page smoke test passed", true);
    }


    // =========================================================
    // TC-PROD-07
    // Filter dropdown smoke test
    // =========================================================
    @Test(priority=7,groups = {"SMT", "Pos"})
    public void verifyFilterDropdownSmokeTest() {

        ProductPage pp = new ProductPage(driver);

        Assert.assertTrue(
                pp.getSortDropdown().isDisplayed(),
                "Filter dropdown is not displayed"
        );

        pp.sortProductBy("Name (A to Z)");

        Assert.assertEquals(
                pp.getProductNames(),
                getSortedProductNames(pp.getProductNames()),
                "Name A to Z sorting failed"
        );

        Reporter.log("Filter dropdown smoke test passed", true);
    }


    // =========================================================
    // TC-PROD-08
    // Verify product visibility after sorting
    // =========================================================
    @Test(priority=8,groups = {"Regression", "Pos"})
    public void verifyProductVisibilityAfterSorting() {

        ProductPage pp = new ProductPage(driver);

        // =========================================================
        // BEFORE SORTING
        // =========================================================
        List<String> beforeSorting = pp.getProductNames();

        System.out.println("=================================");
        System.out.println("PRODUCTS BEFORE SORTING");
        System.out.println("=================================");

        for (String name : beforeSorting) {
            System.out.println(name);
        }


        // =========================================================
        // NAME A TO Z
        // =========================================================
        pp.sortProductBy("Name (A to Z)");

        List<String> actualNameAZ = pp.getProductNames();

        System.out.println("\n=================================");
        System.out.println("AFTER SORTING - NAME A TO Z");
        System.out.println("=================================");

        for (String name : actualNameAZ) {
            System.out.println(name);
        }

        List<String> expectedNameAZ = new ArrayList<>(actualNameAZ);
        Collections.sort(expectedNameAZ);

        Assert.assertEquals(
                actualNameAZ,
                expectedNameAZ,
                "Name A to Z sorting failed"
        );


        // =========================================================
        // NAME Z TO A
        // =========================================================
        pp.sortProductBy("Name (Z to A)");

        List<String> actualNameZA = pp.getProductNames();

        System.out.println("\n=================================");
        System.out.println("AFTER SORTING - NAME Z TO A");
        System.out.println("=================================");

        for (String name : actualNameZA) {
            System.out.println(name);
        }

        List<String> expectedNameZA = new ArrayList<>(actualNameZA);
        Collections.sort(expectedNameZA, Collections.reverseOrder());

        Assert.assertEquals(
                actualNameZA,
                expectedNameZA,
                "Name Z to A sorting failed"
        );


        // =========================================================
        // PRICE LOW TO HIGH
        // =========================================================
        pp.sortProductBy("Price (low to high)");

        List<Double> actualPriceLow = pp.getProductPrices();

        System.out.println("\n=================================");
        System.out.println("AFTER SORTING - PRICE LOW TO HIGH");
        System.out.println("=================================");

        for (Double price : actualPriceLow) {
            System.out.println(price);
        }

        List<Double> expectedPriceLow = new ArrayList<>(actualPriceLow);
        Collections.sort(expectedPriceLow);

        Assert.assertEquals(
                actualPriceLow,
                expectedPriceLow,
                "Price Low to High sorting failed"
        );


        // =========================================================
        // PRICE HIGH TO LOW
        // =========================================================
        pp.sortProductBy("Price (high to low)");

        List<Double> actualPriceHigh = pp.getProductPrices();

        System.out.println("\n=================================");
        System.out.println("AFTER SORTING - PRICE HIGH TO LOW");
        System.out.println("=================================");

        for (Double price : actualPriceHigh) {
            System.out.println(price);
        }

        List<Double> expectedPriceHigh = new ArrayList<>(actualPriceHigh);
        Collections.sort(expectedPriceHigh, Collections.reverseOrder());

        Assert.assertEquals(
                actualPriceHigh,
                expectedPriceHigh,
                "Price High to Low sorting failed"
        );


        Assert.assertFalse(
                pp.getProductsList().isEmpty(),
                "Products disappeared after sorting"
        );

        Reporter.log(
                "All sorting options verified successfully",
                true
        );
    }
    

    // =========================================================
    // TC-PROD-09
    // Add to Cart after changing filter
    // =========================================================
    @Test(priority=9,
            dataProvider = "ProductName",
            	
            groups = {"Regression", "Pos"}
    )
    public void verifyAddToCartAfterChangingFilter(
            String productName) {

        ProductPage pp = new ProductPage(driver);

        pp.sortProductBy("Name (Z to A)");

        pp.addProductToCart(productName);

        Assert.assertTrue(
                pp.isCartBadgeDisplayed(),
                "Cart Logo did not update after sorting"
        );

        Assert.assertEquals(
                pp.getCartBadgeText(),
                "1",
                "Cart quantity is incorrect"
        );

        Reporter.log(
                "Product added successfully after changing filter",
                true
        );
    }


    // =========================================================
    // TC-PROD-10
    // Cart Logo Boundary: 0 -> 1 -> 0
    // =========================================================
    @Test(priority=10,
            dataProvider = "ProductName",
            	groups = {"BVA"}
    )
    public void verifyCartLogoBoundaryState(
            String productName) {

        ProductPage pp = new ProductPage(driver);

        // 0 items
        Assert.assertFalse(
                isCartBadgePresent(pp),
                "Cart should initially be empty"
        );

        // 0 -> 1
        pp.addProductToCart(productName);

        Assert.assertTrue(
                pp.isCartBadgeDisplayed(),
                "Cart badge is not displayed after adding product"
        );

        Assert.assertEquals(
                pp.getCartBadgeText(),
                "1",
                "Cart quantity should be 1"
        );

        // Go to Cart
        pp.clickCart();

        cartPage cp = new cartPage(driver);

        // 1 -> 0
        cp.getRemoveButtons().get(0).click();

        Assert.assertEquals(
                cp.getCartItemCount(),
                0,
                "Cart should be empty after removing product"
        );

        Reporter.log(
                "Cart boundary 0 -> 1 -> 0 verified",
                true
        );
    }


    // =========================================================
    // Helper method
    // =========================================================
    private List<String> getSortedProductNames(List<String> actualNames) {

        List<String> expected =
                new ArrayList<>(actualNames);

        Collections.sort(expected);

        return expected;
    }


    private boolean isCartBadgePresent(ProductPage pp) {

        try {
            return pp.isCartBadgeDisplayed();

        } catch (Exception e) {
            return false;
        }
    }
}