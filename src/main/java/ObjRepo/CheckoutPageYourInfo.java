package ObjRepo;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CheckoutPageYourInfo {
	WebDriver driver;
    public CheckoutPageYourInfo(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    // Checkout page title
    @FindBy(xpath = "//span[@class=\"title\"]")
    private WebElement CheckoutTitle;

    // First name field
    @FindBy(xpath = "//input[@id='first-name']")
    private WebElement Fname;

    // Last name field
    @FindBy(xpath = "//input[@id='last-name']")
    private WebElement Lname;

    // Postal code field
    @FindBy(xpath = "//input[@id='postal-code']")
    private WebElement Postalcode;

    // Continue button
    @FindBy(xpath = "//input[@id='continue']")
    private WebElement ContinueBtn;

    // Cancel button
    @FindBy(xpath = "//button[@id='cancel']")
    private WebElement CancelBtn;

    // Error message
    @FindBy(xpath = "//h3[@data-test='error']")
    private WebElement errorMessage;

    public String getCheckoutTitle() {
        return CheckoutTitle.getText();
    }

    // Enter first name
    public void enterFirstName(String fname) {

        Fname.clear();
        Fname.sendKeys(fname);
    }

    // Enter last name
    public void enterLastName(String lname) {

        Lname.clear();
        Lname.sendKeys(lname);
    }

    // Enter postal code
    public void enterPostalCode(String postalcode) {

        Postalcode.clear();
        Postalcode.sendKeys(postalcode);
    }

    // Click Continue
    public void clickContinue() {

        ContinueBtn.click();
    }

    // Click Cancel
    public void clickCancel() {

        CancelBtn.click();
    }

    // Check first name field
    public boolean isFirstNameDisplayed() {

        return Fname.isDisplayed();
    }

    // Check last name field
    public boolean isLastNameDisplayed() {

        return Lname.isDisplayed();
    }

    // Check postal code field
    public boolean isPostalCodeDisplayed() {

        return Postalcode.isDisplayed();
    }

    // Check Continue button
    public boolean isContinueDisplayed() {

        return ContinueBtn.isDisplayed();
    }

    // Check Cancel button
    public boolean isCancelDisplayed() {

        return CancelBtn.isDisplayed();
    }

    // Check error message
    public boolean isErrorDisplayed() {

        return errorMessage.isDisplayed();
    }

    // Enter all checkout information
    public void Info(
            String fname,
            String lname,
            String postalcode)
            throws InterruptedException {

        enterFirstName(fname);
        enterLastName(lname);
        enterPostalCode(postalcode);

        clickContinue();

        Thread.sleep(2000);
    }
}