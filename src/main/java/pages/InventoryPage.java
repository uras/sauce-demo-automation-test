package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

public class InventoryPage extends BasePage {

    private final By productSourceContainer = By.cssSelector(".product_sort_container");
    private final By itemList = By.cssSelector(".inventory_item_name");
    private final By imageList = By.cssSelector(".inventory_item_img");

    public InventoryPage(WebDriver webDriver) {
        super(webDriver);
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".inventory_list")));
    }

    public void sortByPrice() {
        final Select dropDown = new Select(productSourceContainer.findElement(webDriver));
        dropDown.selectByVisibleText("Price (low to high)");
    }

    public InventoryItemPage selectFirstItem() {
        itemList.findElements(webDriver)
                .get(0)
                .click();
        return new InventoryItemPage(webDriver);
    }

    public InventoryItemPage clickFirstItemImage() {
        imageList.findElements(webDriver).get(0).click();
        return new InventoryItemPage(webDriver);
    }

    public String getFirstItemName() {
        return itemList.findElements(webDriver).get(0).getText();
    }
}
