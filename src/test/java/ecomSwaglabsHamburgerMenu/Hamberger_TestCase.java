package ecomSwaglabsHamburgerMenu;

import org.testng.Assert;
import org.testng.annotations.Test;

import BaseClass.BaseClass;
import ObjRepo.HamburgerMenuPage;
import ObjRepo.ProductPage;
import ObjRepo.cartPage;

public class Hamberger_TestCase extends BaseClass {

    // TC-HAM-01
    // Verify Hamburger menu options are displayed
    @Test
    public void verifyHamburgerMenuOptionsDisplayed() {

        ProductPage product = new ProductPage(driver);
        HamburgerMenuPage hamburger = new HamburgerMenuPage(driver);

        product.getHamburger().click();

        Assert.assertTrue(hamburger.isAllItemsDisplayed(),
                "All Items option is not displayed");

        Assert.assertTrue(hamburger.isLogoutDisplayed(),
                "Logout option is not displayed");

        Assert.assertTrue(hamburger.isResetAppStateDisplayed(),
                "Reset App State option is not displayed");
    }


    // TC-HAM-02
    // Verify Reset App State removes added product
    @Test
    public void verifyResetAppStateRemovesProduct() {

        ProductPage product = new ProductPage(driver);
        HamburgerMenuPage hamburger = new HamburgerMenuPage(driver);
        cartPage cart = new cartPage(driver);

        product.addFirstProductToCart();

        product.getHamburger().click();

        hamburger.resetAppState();

        product.clickCart();

        Assert.assertEquals(cart.getCartItemCount(), 0,
                "Product was not removed after Reset App State");
    }


    // TC-HAM-03
    // Verify Logout navigates to Login Page
    @Test
    public void verifyLogoutNavigatesToLoginPage() {

        ProductPage product = new ProductPage(driver);
        HamburgerMenuPage hamburger = new HamburgerMenuPage(driver);

        product.getHamburger().click();

        hamburger.getLogout();

        String currentUrl = driver.getCurrentUrl();

        Assert.assertTrue(currentUrl.contains("saucedemo.com"),
                "Logout did not navigate to the Login Page");
    }


    // TC-HAM-04
    // Verify Reset App State with no cart item
    @Test
    public void verifyResetAppStateWithEmptyCart() {

        ProductPage product = new ProductPage(driver);
        HamburgerMenuPage hamburger = new HamburgerMenuPage(driver);
        cartPage cart = new cartPage(driver);

        product.getHamburger().click();

        hamburger.resetAppState();

        product.clickCart();

        Assert.assertEquals(cart.getCartItemCount(), 0,
                "Cart is not empty after Reset App State");
    }


    // TC-HAM-05
    // Verify menu action after logout is not accessible
    @Test
    public void verifyHamburgerNotAccessibleAfterLogout() {

        ProductPage product = new ProductPage(driver);
        HamburgerMenuPage hamburger = new HamburgerMenuPage(driver);

        product.getHamburger().click();

        hamburger.getLogout();

        Assert.assertFalse(driver.getCurrentUrl().contains("inventory.html"),
                "User is still on Product Page after Logout");
    }


    // TC-HAM-06
    // Hamburger menu smoke test
    @Test
    public void hamburgerMenuSmokeTest() {

        ProductPage product = new ProductPage(driver);
        HamburgerMenuPage hamburger = new HamburgerMenuPage(driver);

        product.getHamburger().click();

        Assert.assertTrue(hamburger.isAllItemsDisplayed(),
                "All Items option is not displayed");

        Assert.assertTrue(hamburger.isLogoutDisplayed(),
                "Logout option is not displayed");

        Assert.assertTrue(hamburger.isResetAppStateDisplayed(),
                "Reset App State option is not displayed");
    }


    // TC-HAM-07
    // Verify Reset App State smoke test
    @Test
    public void resetAppStateSmokeTest() {

        ProductPage product = new ProductPage(driver);
        HamburgerMenuPage hamburger = new HamburgerMenuPage(driver);
        cartPage cart = new cartPage(driver);

        product.addFirstProductToCart();

        product.getHamburger().click();

        hamburger.resetAppState();

        product.clickCart();

        Assert.assertEquals(cart.getCartItemCount(), 0,
                "Cart was not reset successfully");
    }


    // TC-HAM-08
    // Verify Logout after navigating through Product and Cart
    @Test
    public void verifyLogoutAfterProductAndCartNavigation() {

        ProductPage product = new ProductPage(driver);
        HamburgerMenuPage hamburger = new HamburgerMenuPage(driver);
        cartPage cart = new cartPage(driver);

        product.clickCart();

        Assert.assertEquals(cart.getCarttitle(), "Your Cart",
                "Cart page was not opened");

        cart.getContinuebtn().click();

        product.getHamburger().click();

        hamburger.getLogout();

        Assert.assertFalse(driver.getCurrentUrl().contains("inventory.html"),
                "Logout failed after Product and Cart navigation");
    }


    // TC-HAM-09
    // Verify Reset App State after adding/removing products
    @Test
    public void verifyResetAfterCartStateTransitions() {

        ProductPage product = new ProductPage(driver);
        HamburgerMenuPage hamburger = new HamburgerMenuPage(driver);
        cartPage cart = new cartPage(driver);

        // Add first product
        product.addFirstProductToCart();

        // Go to cart
        product.clickCart();

        Assert.assertEquals(cart.getCartItemCount(), 1,
                "First product was not added");

        // Remove product
        cart.getRemoveButtons().get(0).click();

        Assert.assertEquals(cart.getCartItemCount(), 0,
                "Product was not removed");

        // Return to Product Page
        cart.getContinuebtn().click();

        // Add another product
        product.addFirstProductToCart();

        // Open Hamburger
        product.getHamburger().click();

        // Reset application
        hamburger.resetAppState();

        // Verify reset
        product.clickCart();

        Assert.assertEquals(cart.getCartItemCount(), 0,
                "Reset App State did not clear the cart");
    }


    // TC-HAM-10
    // Verify Reset App State boundary states
    @Test
    public void verifyResetAppStateBoundaryStates() {

        ProductPage product = new ProductPage(driver);
        HamburgerMenuPage hamburger = new HamburgerMenuPage(driver);
        cartPage cart = new cartPage(driver);

        // Boundary 0
        product.clickCart();

        Assert.assertEquals(cart.getCartItemCount(), 0,
                "Initial cart is not empty");

        // Return to Product Page
        cart.getContinuebtn().click();

        // Boundary 1
        product.addFirstProductToCart();

        product.clickCart();

        Assert.assertEquals(cart.getCartItemCount(), 1,
                "Single product was not added");

        // Return to Product Page
        cart.getContinuebtn().click();

        // Reset back to boundary 0
        product.getHamburger().click();

        hamburger.resetAppState();

        product.clickCart();

        Assert.assertEquals(cart.getCartItemCount(), 0,
                "Reset did not return cart to empty state");
    }


    // TC-HAM-11
    // Complete Hamburger menu workflow
    @Test
    public void completeHamburgerMenuWorkflow() {

        ProductPage product = new ProductPage(driver);
        HamburgerMenuPage hamburger = new HamburgerMenuPage(driver);
        cartPage cart = new cartPage(driver);

        // Open Hamburger
        product.getHamburger().click();

        // Verify menu options
        Assert.assertTrue(hamburger.isAllItemsDisplayed(),
                "All Items option is not displayed");

        Assert.assertTrue(hamburger.isLogoutDisplayed(),
                "Logout option is not displayed");

        Assert.assertTrue(hamburger.isResetAppStateDisplayed(),
                "Reset App State option is not displayed");

        // Close/open menu through product page navigation
        product.getHamburger().click();

        // Add product
        product.addFirstProductToCart();

        // Verify product in cart
        product.clickCart();

        Assert.assertEquals(cart.getCartItemCount(), 1,
                "Product was not added to cart");

        // Return to Product Page
        cart.getContinuebtn().click();

        // Reset App State
        product.getHamburger().click();

        hamburger.resetAppState();

        // Verify cart is empty
        product.clickCart();

        Assert.assertEquals(cart.getCartItemCount(), 0,
                "Reset App State did not clear the cart");

        // Return to Product Page
        cart.getContinuebtn().click();

        // Logout
        product.getHamburger().click();

        hamburger.getLogout();

        Assert.assertFalse(driver.getCurrentUrl().contains("inventory.html"),
                "Logout failed");
    }
}