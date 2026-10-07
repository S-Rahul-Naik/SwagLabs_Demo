package ObjRepo;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
	WebDriver driver;
	public LoginPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	//username
	@FindBy(xpath = "//input[@id=\"user-name\"]")
	private WebElement username;
	
	
	//password
	@FindBy(xpath = "//input[@id=\"password\"]")
	private WebElement password;
	

	//loginButton
	@FindBy(xpath = "//input[@id=\"login-button\"]")
	private WebElement loginButton;
	
	
	public void login(String un, String pwd) throws InterruptedException {
		username.clear();
		username.sendKeys(un);
		password.clear();
		password.sendKeys(pwd);
		loginButton.click();
		Thread.sleep(2000);
		
	}

}
