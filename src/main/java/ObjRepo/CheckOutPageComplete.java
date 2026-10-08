package ObjRepo;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CheckOutPageComplete {

    WebDriver driver;

    public CheckOutPageComplete(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }


    @FindBy(xpath = "//span[@data-test=\"title\"]")
    private WebElement checkOutTitle;


    @FindBy(xpath = "//h2[@data-test=\"complete-header\"]")
    private WebElement ThankYou;


    @FindBy(xpath = "//button[@id=\"back-to-products\"]")
    private WebElement backbtn;


    @FindBy(xpath = "//button[@id=\"generate-pdf-order\"]")
    private WebElement pdfbtn;


    public String getCheckOutTitle() {
        return checkOutTitle.getText();
    }


    public String getThankYou() {
        return ThankYou.getText();
    }


    public void clickBackHome() {
        backbtn.click();
    }


    public boolean isPdfButtonDisplayed() {
        return pdfbtn.isDisplayed();
    }
}