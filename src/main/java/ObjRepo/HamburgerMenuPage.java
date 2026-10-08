package ObjRepo;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HamburgerMenuPage {

    WebDriver driver;
    WebDriverWait wait;

    public HamburgerMenuPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    // All Items
    @FindBy(id = "inventory_sidebar_link")
    private WebElement allItems;

    public boolean isAllItemsDisplayed() {
        return wait.until(
                ExpectedConditions.visibilityOf(allItems)
        ).isDisplayed();
    }

    // Logout
    @FindBy(id = "logout_sidebar_link")
    private WebElement logout;

    public boolean isLogoutDisplayed() {
        return wait.until(
                ExpectedConditions.visibilityOf(logout)
        ).isDisplayed();
    }

    public void getLogout() {
        wait.until(
                ExpectedConditions.elementToBeClickable(logout)
        ).click();
    }

    // Reset App State
    @FindBy(id = "reset_sidebar_link")
    private WebElement resetAppState;

    public boolean isResetAppStateDisplayed() {
        return wait.until(
                ExpectedConditions.visibilityOf(resetAppState)
        ).isDisplayed();
    }

    public void resetAppState() {
        wait.until(
                ExpectedConditions.elementToBeClickable(resetAppState)
        ).click();
    }
    @FindBy(id = "react-burger-cross-btn")
    private WebElement closeMenu;

    public void closeMenu() {
        closeMenu.click();
    }
}