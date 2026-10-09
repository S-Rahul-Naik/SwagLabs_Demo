package ecomSwaglabsCart;

import java.util.List;


import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import BaseClass.BaseClass;
import ObjRepo.ProductPage;
import ObjRepo.cartPage;

public class Cart_TestCase extends BaseClass {

    // TC-CART-01
    // Verify added product is visible in Cart Page
    @Test(priority = 1, groups = {"FT", "Pos"})
    public void verifyAddedProductVisibleInCart() {

        ProductPage pp = new ProductPage(driver);

        // Add product
        List<WebElement> addButtons = pp.getAddToCartButtons();
        addButtons.get(0).click();

        // Open Cart
        pp.getCartlogo().click();

        cartPage cp = new cartPage(driver);

        Assert.assertEquals(cp.getCarttitle(), "Your Cart");

        Assert.assertFalse(
                cp.getCartitems().isEmpty(),
                "Cart is empty. Product was not added."
        );

        Reporter.log("TC-CART-01 Passed - Product is visible in Cart", true);
    }


    // TC-CART-02
    // Verify Remove, Checkout and Continue Shopping controls
    @Test(priority = 2, groups = {"FT", "Pos"})
    public void verifyCartControls() {

        ProductPage pp = new ProductPage(driver);

        // Add product
        pp.getAddToCartButtons().get(0).click();

        // Open cart
        pp.getCartlogo().click();

        cartPage cp = new cartPage(driver);

        // Verify Remove
        Assert.assertFalse(
                cp.getRemoveButtons().isEmpty(),
                "Remove button is not available."
        );

        // Verify Checkout
        Assert.assertTrue(
                cp.getCheckoutbtn().isDisplayed(),
                "Checkout button is not displayed."
        );

        // Verify Continue Shopping
        Assert.assertTrue(
                cp.getContinuebtn().isDisplayed(),
                "Continue Shopping button is not displayed."
        );

        Reporter.log("TC-CART-02 Passed", true);
    }


    // TC-CART-03
    // Verify Cart Page to Checkout Page
    @Test(priority = 3, groups = {"Integration"})
    public void verifyCartToCheckout() {

        ProductPage pp = new ProductPage(driver);

        // Add product
        pp.getAddToCartButtons().get(0).click();

        // Open cart
        pp.getCartlogo().click();

        cartPage cp = new cartPage(driver);

        // Click Checkout
        cp.getCheckoutbtn().click();

        // Verify Checkout Information page
        Assert.assertTrue(
                driver.getCurrentUrl().contains("checkout-step-one"),
                "Checkout Information page did not open."
        );

        Reporter.log("TC-CART-03 Passed - Checkout page opened", true);
    }


    // TC-CART-04
    // Verify empty cart
    @Test(priority = 4, groups = {"FT", "Neg"})
    public void verifyEmptyCart() {

        ProductPage pp = new ProductPage(driver);

        // Open Cart without adding anything
        pp.getCartlogo().click();

        cartPage cp = new cartPage(driver);

        // Cart should contain zero products
        Assert.assertEquals(
                cp.getCartItemCount(),
                0,
                "Cart is not empty."
        );

        Reporter.log("TC-CART-04 Passed - Empty cart verified", true);
    }


    // TC-CART-05
    // Verify removed product cannot remain as stale cart item
    @Test(priority = 5, groups = {"FT", "Neg"})
    public void verifyRemovedProductNotPresent() {

        ProductPage pp = new ProductPage(driver);

        // Add product
        pp.getAddToCartButtons().get(0).click();

        // Open cart
        pp.getCartlogo().click();

        cartPage cp = new cartPage(driver);

        // Verify product exists first
        Assert.assertEquals(cp.getCartItemCount(), 1);

        // Remove product
        cp.getRemoveButtons().get(0).click();

        // Verify cart is empty
        Assert.assertEquals(
                cp.getCartItemCount(),
                0,
                "Removed product is still present in cart."
        );

        // Refresh
        driver.navigate().refresh();

        cartPage refreshedCart = new cartPage(driver);

        Assert.assertEquals(
                refreshedCart.getCartItemCount(),
                0,
                "Removed product came back after refresh."
        );

        Reporter.log("TC-CART-05 Passed", true);
    }


    // TC-CART-06
    // Cart Page smoke test
    @Test(priority = 6, groups = {"SMT", "Pos"})
    public void cartSmokeTest() {

        ProductPage pp = new ProductPage(driver);

        // Add product
        pp.getAddToCartButtons().get(0).click();

        // Open cart
        pp.getCartlogo().click();

        cartPage cp = new cartPage(driver);

        // Verify Cart page
        Assert.assertEquals(cp.getCarttitle(), "Your Cart");

        // Verify product
        Assert.assertFalse(
                cp.getCartitems().isEmpty(),
                "Product is not visible in cart."
        );

        // Verify checkout
        Assert.assertTrue(
                cp.getCheckoutbtn().isDisplayed(),
                "Checkout button is not displayed."
        );

        Reporter.log("TC-CART-06 Passed", true);
    }


    // TC-CART-07
    // Verify Continue Shopping
    @Test(priority = 7, groups = {"SMT", "Pos"})
    public void verifyContinueShopping() {

        ProductPage pp = new ProductPage(driver);

        // Open cart
        pp.getCartlogo().click();

        cartPage cp = new cartPage(driver);

        // Click Continue Shopping
        cp.getContinuebtn().click();

        // Verify Product Page
        Assert.assertTrue(
                driver.getCurrentUrl().contains("inventory"),
                "User did not return to Product Page."
        );

        Reporter.log("TC-CART-07 Passed", true);
    }


    // TC-CART-08
    // Verify cart contents after returning from Product Page
    @Test(priority = 8, groups = {"Regression", "Pos"})
    public void verifyCartContentsAfterReturning() {

        ProductPage pp = new ProductPage(driver);

        // Add product
        pp.getAddToCartButtons().get(0).click();

        // Open cart
        pp.getCartlogo().click();

        cartPage cp = new cartPage(driver);

        // Store cart contents
        List<String> beforeReturning = cp.getCartitems();

        // Continue Shopping
        cp.getContinuebtn().click();

        // Open Cart again
        ProductPage productPage = new ProductPage(driver);
        productPage.getCartlogo().click();

        cartPage cartAgain = new cartPage(driver);

        List<String> afterReturning = cartAgain.getCartitems();

        // Compare
        Assert.assertEquals(
                afterReturning,
                beforeReturning,
                "Cart contents changed after returning."
        );

        Reporter.log("TC-CART-08 Passed", true);
    }


    // TC-CART-09
    // Verify Remove after returning to Cart
    @Test(priority = 9, groups = {"Regression", "Pos"})
    public void verifyRemoveAfterReturningToCart() {

        ProductPage pp = new ProductPage(driver);

        // Add product
        pp.getAddToCartButtons().get(0).click();

        // Open cart
        pp.getCartlogo().click();

        cartPage cp = new cartPage(driver);

        // Continue Shopping
        cp.getContinuebtn().click();

        // Open cart again
        ProductPage productPage = new ProductPage(driver);
        productPage.getCartlogo().click();

        cartPage cartAgain = new cartPage(driver);

        // Remove
        cartAgain.getRemoveButtons().get(0).click();

        // Verify cart empty
        Assert.assertEquals(
                cartAgain.getCartItemCount(),
                0,
                "Product was not removed."
        );

        Reporter.log("TC-CART-09 Passed", true);
    }


    // TC-CART-10
    // Verify cart quantity boundary 0 and 1
    @Test(priority = 10, groups = {"BVA"})
    public void verifyCartQuantityBoundary() {

        ProductPage pp = new ProductPage(driver);

        // Initial cart should be empty
        pp.getCartlogo().click();

        cartPage cp = new cartPage(driver);

        Assert.assertEquals(
                cp.getCartItemCount(),
                0,
                "Initial cart is not empty."
        );

        // Go back to products
        cp.getContinuebtn().click();

        // Add exactly one product
        ProductPage productPage = new ProductPage(driver);
        productPage.getAddToCartButtons().get(0).click();

        // Open cart
        productPage.getCartlogo().click();

        cartPage cartAgain = new cartPage(driver);

        // Boundary = 1
        Assert.assertEquals(
                cartAgain.getCartItemCount(),
                1,
                "Cart does not contain exactly one product."
        );

        // Remove
        cartAgain.getRemoveButtons().get(0).click();

        // Boundary = 0
        Assert.assertEquals(
                cartAgain.getCartItemCount(),
                0,
                "Cart did not become empty after removing product."
        );

        Reporter.log("TC-CART-10 Passed", true);
    }
}