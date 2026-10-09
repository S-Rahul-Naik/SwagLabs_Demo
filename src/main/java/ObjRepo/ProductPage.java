package ObjRepo;

import java.time.Duration;
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
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    // Products title
    @FindBy(className = "title")
    private WebElement productsTitle;

    public String getProductsTitle() {
        return productsTitle.getText();
    }

    // Check whether cart badge is present
    public boolean isCartBadgePresent() {

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(0));

        boolean present = !driver.findElements(
                By.cssSelector(".shopping_cart_badge")
        ).isEmpty();

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));

        return present;
    }

    // Product sorting dropdown
    @FindBy(className = "product_sort_container")
    private WebElement sortDropdown;

    // Product names
    @FindBy(className = "inventory_item_name")
    private List<WebElement> productNames;

    public List<String> getProductNames() {

        List<String> names = new ArrayList<>();

        for (WebElement product : productNames) {
            names.add(product.getText());
        }

        return names;
    }

    // Product prices
    @FindBy(className = "inventory_item_price")
    private List<WebElement> productPrices;

    public List<Double> getProductPrices() {

        List<Double> prices = new ArrayList<>();

        for (WebElement price : productPrices) {

            String text = price.getText();

            // Convert price text into number
            double value = Double.parseDouble(
                    text.replace("$", "")
            );

            prices.add(value);
        }

        return prices;
    }

    // Complete product list
    @FindBy(xpath = "//div[@class=\"inventory_item\"]")
    private List<WebElement> ProductsList;

    public List<WebElement> getProductsList() {
        return ProductsList;
    }

    // Sorting dropdown
    public WebElement getSortDropdown() {
        return sortDropdown;
    }

    // Add to cart buttons
    @FindBy(xpath = "//button[contains(text(),'Add to cart')]")
    private List<WebElement> addToCartButtons;

    public List<WebElement> getAddToCartButtons() {
        return addToCartButtons;
    }

    // Cart icon
    @FindBy(className = "shopping_cart_link")
    private WebElement cartIcon;

    // Cart logo
    @FindBy(xpath = "//a[@data-test=\"shopping-cart-link\"]")
    private WebElement cartlogo;

    public WebElement getCartlogo() {
        return cartlogo;
    }

    // Hamburger menu button
    @FindBy(xpath = "//button[@id=\"react-burger-menu-btn\"]")
    private WebElement hamburger;

    public WebElement getHamburger() {
        return hamburger;
    }

    // Sort products
    public void sortProductBy(String selectBy) {

        Select s = new Select(sortDropdown);

        s.selectByVisibleText(selectBy);
    }

    // Add first product to cart
    public void addFirstProductToCart() {

        addToCartButtons.get(0).click();
    }

    // Open cart
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
                    .findElement(
                            By.className("inventory_item_name")
                    )
                    .getText();

            if (name.equalsIgnoreCase(productName.trim())) {

                product.findElement(
                        By.xpath(
                            ".//button[contains(text(),'Add to cart')]"
                        )
                ).click();

                return;
            }
        }

        throw new RuntimeException(
                "Product not found: " + productName
        );
    }
}