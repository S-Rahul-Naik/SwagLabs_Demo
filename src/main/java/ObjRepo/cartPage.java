package ObjRepo;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class cartPage {
	WebDriver driver;
	public cartPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	//title 
	@FindBy(xpath = "//span[@data-test=\"title\"]")
	private WebElement carttitle;
	public String getCarttitle() {
		return carttitle.getText();
	}
	//continue button
	@FindBy(xpath = "//button[@id=\"continue-shopping\"]")
	private WebElement continuebtn;
	public WebElement getContinuebtn() {
		return continuebtn;
	}
	//checkoutbutton
	@FindBy(xpath = "//button[@id=\"checkout\"]")
	private WebElement checkoutbtn;
	public WebElement getCheckoutbtn() {
		return checkoutbtn;
	}
	//cartitems
	@FindBy(xpath = "//div[@class=\"cart_item\"]")
	private List<WebElement> cartitems;
	
	public List<String> getCartitems() {
		List<String> cartitm = new ArrayList<>();
	    for (WebElement ci : cartitems) {
	    	cartitm.add(ci.getText());
	    }
	    return cartitm;
	}
	public int getCartItemCount() {
		// TODO Auto-generated method stub
		return cartitems.size();
	}
	// Remove buttons
	@FindBy(xpath = "//button[contains(@id,'remove')]")
	private List<WebElement> removeButtons;

	public List<WebElement> getRemoveButtons() {
	    return removeButtons;
	}
	
	public void clickCheckout() {
	    checkoutbtn.click();
	}



}