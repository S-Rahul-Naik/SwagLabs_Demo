package ObjRepo;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {

    WebDriver driver;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    // Username
    @FindBy(xpath = "//input[@id='user-name']")
    private WebElement username;

    public WebElement getUsername() {
        return username;
    }

    // Password
    @FindBy(xpath = "//input[@id='password']")
    private WebElement password;

    public WebElement getPassword() {
        return password;
    }

    // Login button
    @FindBy(xpath = "//input[@id='login-button']")
    private WebElement loginButton;

    public WebElement getLoginButton() {
        return loginButton;
    }

    // Error message
    @FindBy(xpath = "//h3[@data-test='error']")
    private WebElement errormsg;

    public WebElement getErrormsg() {
        return errormsg;
    }

    public void login(String un, String pwd) throws InterruptedException {

        username.clear();
        username.sendKeys(un);

        password.clear();
        password.sendKeys(pwd);

        loginButton.click();

        Thread.sleep(2000);
    }
}