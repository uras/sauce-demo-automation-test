package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class CheckoutStepOnePage extends BasePage {

    private final By firstNameInput = By.id("first-name");
    private final By lastNameInput = By.id("last-name");
    private final By postalCodeInput = By.id("postal-code");
    private final By continueButton = By.className("cart_button");


    public CheckoutStepOnePage(WebDriver webDriver) {
        super(webDriver);
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("checkout_info_container")));
    }

    public CheckoutStepTwoPage gotoStepTwo() {
        final WebElement firstNameElement = webDriver.findElement(firstNameInput);
        firstNameElement.sendKeys("first-name");
        final WebElement lastNameElement = webDriver.findElement(lastNameInput);
        lastNameElement.sendKeys("last-name");
        final WebElement postalCodeElement = webDriver.findElement(postalCodeInput);
        postalCodeElement.sendKeys("34123");
        webDriver.findElement(continueButton).click();
        return new CheckoutStepTwoPage(webDriver);
    }

}
