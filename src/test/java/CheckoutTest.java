import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.CheckoutCompletePage;
import pages.CheckoutStepOnePage;
import pages.CheckoutStepTwoPage;
import pages.InventoryItemPage;
import pages.InventoryPage;
import pages.LoginPage;
import pages.ShoppingCartPage;

import java.util.List;

import static org.testng.Assert.assertEquals;

public class CheckoutTest {

    public WebDriver webDriver;
    SoftAssert softAssert = new SoftAssert();

    @BeforeMethod
    public void setUp() {
        webDriver = new ChromeDriver();
        System.setProperty("webdriver.chrome.driver", "chromedriver");
        webDriver.get("https://www.saucedemo.com/");
    }

    @Test
    public void success_checkout_test() {
        final LoginPage loginPage = new LoginPage(webDriver);
        InventoryPage inventoryPage = loginPage.login("standard_user", "secret_sauce");
        inventoryPage.sortByPrice();
        final String firstItemName = inventoryPage.getFirstItemName();
        InventoryItemPage inventoryItemPage = inventoryPage.selectFirstItem();
        inventoryItemPage.addToCart();
        inventoryPage = inventoryItemPage.goBack();
        final String secondItemName = inventoryPage.getFirstItemName();
        inventoryItemPage = inventoryPage.clickFirstItemImage();
        inventoryItemPage.addToCart();
        ShoppingCartPage shoppingCartPage = inventoryItemPage.proceedToShoppingCart();
        final List<String> inventoryItemTexts = shoppingCartPage.getInventoryItemTexts();
        CheckoutStepOnePage checkoutStepOnePage = shoppingCartPage.goToCheckout();
        final CheckoutStepTwoPage checkoutStepTwoPage = checkoutStepOnePage.gotoStepTwo();
        final CheckoutCompletePage checkoutCompletePage = checkoutStepTwoPage.gotoStepTwo();
        String successMessageHeader = checkoutCompletePage.getSuccessMessageHeader();
        String successMessageText = checkoutCompletePage.getSuccessMessageText();
        checkoutCompletePage.logout();

        assertEquals(2, inventoryItemTexts.size());
        assertEquals(firstItemName, inventoryItemTexts.get(0));
        assertEquals(secondItemName, inventoryItemTexts.get(1));
        assertEquals("THANK YOU FOR YOUR ORDER", successMessageHeader);
        assertEquals("Your order has been dispatched, and will arrive just as fast as the pony can get there!", successMessageText);
    }

    @Test
    public void success_checkout_test_with_problem_user() {
        final LoginPage loginPage = new LoginPage(webDriver);
        InventoryPage inventoryPage = loginPage.login("problem_user", "secret_sauce");
        inventoryPage.sortByPrice();
        inventoryPage.getFirstItemName();
        InventoryItemPage inventoryItemPage = inventoryPage.selectFirstItem();
        inventoryItemPage.addToCart();
        inventoryPage = inventoryItemPage.goBack();
        inventoryPage.getFirstItemName();
        inventoryItemPage = inventoryPage.clickFirstItemImage();
        inventoryItemPage.addToCart();
        ShoppingCartPage shoppingCartPage = inventoryItemPage.proceedToShoppingCart();
        final List<String> inventoryItemTexts = shoppingCartPage.getInventoryItemTexts();
        softAssert.assertEquals(2, inventoryItemTexts.size());

        CheckoutStepOnePage checkoutStepOnePage = shoppingCartPage.goToCheckout();
        final CheckoutStepTwoPage checkoutStepTwoPage = checkoutStepOnePage.gotoStepTwo();
        final CheckoutCompletePage checkoutCompletePage = checkoutStepTwoPage.gotoStepTwo();
        String successMessageHeader = checkoutCompletePage.getSuccessMessageHeader();
        String successMessageText = checkoutCompletePage.getSuccessMessageText();
        checkoutCompletePage.logout();

        softAssert.assertEquals("THANK YOU FOR YOUR ORDER", successMessageHeader);
        softAssert.assertEquals("Your order has been dispatched, and will arrive just as fast as the pony can get there!", successMessageText);
    }

    @AfterMethod
    public void tearDown() {
        webDriver.quit();
    }
}
