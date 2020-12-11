package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class CheckoutStepTwoPage extends BasePage {

    private final By finishButton = By.className("cart_button");

    public CheckoutStepTwoPage(WebDriver webDriver) {
        super(webDriver);
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("checkout_summary_container")));
    }

    public CheckoutCompletePage gotoStepTwo() {
        webDriver.findElement(finishButton).click();
        return new CheckoutCompletePage(webDriver);
    }

}
