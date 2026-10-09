package ObjRepo;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CheckOutPageComplete {
	WebDriver driver;
    public CheckOutPageComplete(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    // Checkout Complete title
    @FindBy(xpath = "//span[@data-test=\"title\"]")
    private WebElement checkOutTitle;

    // Thank you message
    @FindBy(xpath = "//h2[@data-test=\"complete-header\"]")
    private WebElement ThankYou;

    // Back Home button
    @FindBy(xpath = "//button[@id=\"back-to-products\"]")
    private WebElement backbtn;

    public String getCheckOutTitle() {

        return checkOutTitle.getText();
    }

    public String getThankYou() {

        return ThankYou.getText();
    }

    // Go back to Products page
    public void clickBackHome() {

        backbtn.click();
    }
}