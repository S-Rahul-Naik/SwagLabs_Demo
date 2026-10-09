package ObjRepo;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CheckoutPageOverview {
	WebDriver driver;
    public CheckoutPageOverview(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    // Checkout Overview title
    @FindBy(xpath = "//span[@class=\"title\"]")
    private WebElement CheckoutOverviewTitle;

    // Products added to checkout
    @FindBy(xpath = "//div[@class=\"cart_item\"]")
    private List<WebElement> addedProducts;

    // Finish button
    @FindBy(xpath = "//button[@id='finish']")
    private WebElement finishBtn;

    // Cancel button
    @FindBy(xpath = "//button[@id='cancel']")
    private WebElement CancelBtn;

    public String getCheckoutTitle() {

        return CheckoutOverviewTitle.getText();
    }

    // Get products displayed in overview
    public List<String> getaddedProducts() {

        List<String> products = new ArrayList<>();

        for (WebElement ci : addedProducts) {
            products.add(ci.getText());
        }

        return products;
    }

    // Click Finish
    public void clickFinish() {

        finishBtn.click();
    }

    // Click Cancel
    public void clickCancel() {

        CancelBtn.click();
    }

    // Check Finish button
    public boolean isFinishDisplayed() {

        return finishBtn.isDisplayed();
    }

    // Check Cancel button
    public boolean isCancelDisplayed() {

        return CancelBtn.isDisplayed();
    }
}