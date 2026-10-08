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
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }


    @FindBy(xpath = "//span[@class=\"title\"]")
    private WebElement CheckoutOverviewTitle;


    @FindBy(xpath = "//div[@class=\"cart_item\"]")
    private List<WebElement> addedProducts;


    @FindBy(xpath = "//button[@id='finish']")
    private WebElement finishBtn;


    @FindBy(xpath = "//button[@id='cancel']")
    private WebElement CancelBtn;


    public String getCheckoutTitle() {
        return CheckoutOverviewTitle.getText();
    }


    public List<String> getaddedProducts() {

        List<String> products = new ArrayList<>();

        for (WebElement ci : addedProducts) {
            products.add(ci.getText());
        }

        return products;
    }


    public void clickFinish() {
        finishBtn.click();
    }


    public void clickCancel() {
        CancelBtn.click();
    }


    public boolean isFinishDisplayed() {
        return finishBtn.isDisplayed();
    }


    public boolean isCancelDisplayed() {
        return CancelBtn.isDisplayed();
    }
}