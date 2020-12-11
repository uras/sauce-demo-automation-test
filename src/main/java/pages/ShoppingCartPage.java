package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;
import java.util.stream.Collectors;

public class ShoppingCartPage extends BasePage {

    private final By inventoryItemTextList = By.className("inventory_item_name");
    private final By checkoutButton = By.className("checkout_button");

    public ShoppingCartPage(WebDriver webDriver) {
        super(webDriver);
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("cart_contents_container")));
    }

    public List<String> getInventoryItemTexts() {
        return inventoryItemTextList.findElements(webDriver)
                .stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
    }

    public CheckoutStepOnePage goToCheckout() {
        checkoutButton.findElement(webDriver).click();
        return new CheckoutStepOnePage(webDriver);
    }


}
