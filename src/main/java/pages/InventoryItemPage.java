package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class InventoryItemPage extends BasePage {

    private final By addToCartButton = By.cssSelector(".btn_primary.btn_inventory");
    private final By goBackButton = By.cssSelector(".inventory_details_back_button");
    private final By proceedToCheckoutButton = By.cssSelector(".shopping_cart_link");

    public InventoryItemPage(WebDriver webDriver) {
        super(webDriver);
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.className("inventory_details_container")));
    }

    public void addToCart() {
        addToCartButton.findElement(webDriver).click();
    }

    public InventoryPage goBack() {
        goBackButton.findElement(webDriver).click();
        return new InventoryPage(webDriver);
    }

    public ShoppingCartPage proceedToShoppingCart() {
        proceedToCheckoutButton.findElement(webDriver).click();
        return new ShoppingCartPage(webDriver);
    }
}
