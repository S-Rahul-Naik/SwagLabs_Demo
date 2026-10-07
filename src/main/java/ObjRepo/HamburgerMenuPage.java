package ObjRepo;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HamburgerMenuPage {
	WebDriver driver;
	public HamburgerMenuPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	//logout
	@FindBy(xpath = "//a[@id=\"logout_sidebar_link\"]")
	private WebElement logout;
	public void getLogout() {
		logout.click();
	}
}
