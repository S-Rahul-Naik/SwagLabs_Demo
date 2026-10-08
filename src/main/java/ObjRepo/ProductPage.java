package ObjRepo;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class ProductPage {
	WebDriver driver;
	public ProductPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	@FindBy(className = "title")
	private WebElement productsTitle;
	public String getProductsTitle() {
		return productsTitle.getText();
	}
	
	
	@FindBy(className = "product_sort_container")
	private WebElement sortDropdown;

	@FindBy(className = "inventory_item_name")
	private List<WebElement> productNames;
	

	public List<String> getProductNames() {

	    List<String> names = new ArrayList<>();

	    for (WebElement product : productNames) {
	        names.add(product.getText());
	    }

	    return names;
	}

	@FindBy(className = "inventory_item_price")
	private List<WebElement> productPrices;
	public List<Double> getProductPrices() {

	    List<Double> prices = new ArrayList<>();

	    for (WebElement price : productPrices) {

	        String text = price.getText();

	        // "$29.99" → "29.99"
	        double value = Double.parseDouble(text.replace("$", ""));

	        prices.add(value);
	    }

	    return prices;
	}
	
	@FindBy(xpath = "//div[@class=\"inventory_item\"]")
	private List<WebElement> ProductsList;
	
	

	public List<WebElement> getProductsList() {
		return ProductsList;
	}

	public WebDriver getDriver() {
		return driver;
	}

	public WebElement getSortDropdown() {
		return sortDropdown;
	}

	public WebElement getCartIcon() {
		return cartIcon;
	}

	public List<WebElement> getAddToCartButtons() {
		return addToCartButtons;
	}
	@FindBy(className = "shopping_cart_link")
	private WebElement cartIcon;
	
	
	
	@FindBy(xpath = "//button[contains(text(),'Add to cart')]")
	private List<WebElement> addToCartButtons;
	
		
	//cartlogo
	@FindBy(xpath = "//a[@data-test=\"shopping-cart-link\"]")
	private WebElement cartlogo;
	public WebElement getCartlogo() {
		return cartlogo;
	}
	//hamburger
	@FindBy(xpath = "//button[@id=\"react-burger-menu-btn\"]")
	private WebElement hamburger;
	public WebElement getHamburger() {
		return hamburger;
	}
	
	@FindBy(xpath = "//div[@class= \"bm-menu-wrap\"]")
	private WebElement hamburgerMenu;
	public WebElement getHamburgerMenu() {
		return hamburgerMenu;
	}
	
	public void sortProductBy(String selectBy) {
	    Select s = new Select(sortDropdown);
	    s.selectByVisibleText(selectBy);
	}
	
	public void addFirstProductToCart() {
	    addToCartButtons.get(0).click();
	}

	public void clickCart() {
	    cartIcon.click();
	}
	// Cart quantity badge
	@FindBy(className = "shopping_cart_badge")
	private WebElement cartBadge;

	public boolean isCartBadgeDisplayed() {
	    return cartBadge.isDisplayed();
	}

	public String getCartBadgeText() {
	    return cartBadge.getText();
	}

	// Add product by product name
	public void addProductToCart(String productName) {

	    for (WebElement product : ProductsList) {
	        String name = product
	                .findElement(By.className("inventory_item_name"))
	                .getText();
	        if (name.equalsIgnoreCase(productName.trim())) {
	            product.findElement(By.xpath(".//button[contains(text(),'Add to cart')]")).click();
	            return;
	        }
	    }
	    throw new RuntimeException("Product not found: " + productName);
	}
}