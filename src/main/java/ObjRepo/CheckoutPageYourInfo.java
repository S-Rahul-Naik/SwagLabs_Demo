package ObjRepo;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CheckoutPageYourInfo {

    WebDriver driver;

    public CheckoutPageYourInfo(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//span[@class=\"title\"]")
    private WebElement CheckoutTitle;

    @FindBy(xpath = "//input[@id='first-name']")
    private WebElement Fname;

    @FindBy(xpath = "//input[@id='last-name']")
    private WebElement Lname;

    @FindBy(xpath = "//input[@id='postal-code']")
    private WebElement Postalcode;

    @FindBy(xpath = "//input[@id='continue']")
    private WebElement ContinueBtn;

    @FindBy(xpath = "//button[@id='cancel']")
    private WebElement CancelBtn;

    @FindBy(xpath = "//h3[@data-test='error']")
    private WebElement errorMessage;


    public String getCheckoutTitle() {
        return CheckoutTitle.getText();
    }


    public void enterFirstName(String fname) {
        Fname.clear();
        Fname.sendKeys(fname);
    }


    public void enterLastName(String lname) {
        Lname.clear();
        Lname.sendKeys(lname);
    }


    public void enterPostalCode(String postalcode) {
        Postalcode.clear();
        Postalcode.sendKeys(postalcode);
    }


    public void clickContinue() {
        ContinueBtn.click();
    }


    public void clickCancel() {
        CancelBtn.click();
    }


    public boolean isFirstNameDisplayed() {
        return Fname.isDisplayed();
    }


    public boolean isLastNameDisplayed() {
        return Lname.isDisplayed();
    }


    public boolean isPostalCodeDisplayed() {
        return Postalcode.isDisplayed();
    }


    public boolean isContinueDisplayed() {
        return ContinueBtn.isDisplayed();
    }


    public boolean isCancelDisplayed() {
        return CancelBtn.isDisplayed();
    }


    public String getErrorMessage() {
        return errorMessage.getText();
    }


    public boolean isErrorDisplayed() {
        return errorMessage.isDisplayed();
    }


    public void Info(String fname, String lname, String postalcode)
            throws InterruptedException {

        enterFirstName(fname);
        enterLastName(lname);
        enterPostalCode(postalcode);

        clickContinue();

        Thread.sleep(2000);
    }
}