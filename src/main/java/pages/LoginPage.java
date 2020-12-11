package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class LoginPage extends BasePage {

    private final By userNameLocator = By.id("user-name");
    private final By passwordLocator = By.id("password");
    private final By loginButtonLocator = By.id("login-button");
    private final By errorMessage = By.xpath("//h3[@data-test='error']");

    public LoginPage(WebDriver webDriver) {
        super(webDriver);
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("login-button")));
    }

    public InventoryPage login(String username, String password) {
        final WebElement userNameElement = webDriver.findElement(userNameLocator);
        userNameElement.sendKeys(username);
        final WebElement passwordElement = webDriver.findElement(passwordLocator);
        passwordElement.sendKeys(password);
        final WebElement loginButton = webDriver.findElement(loginButtonLocator);
        loginButton.click();
        return new InventoryPage(webDriver);
    }

    public LoginPage loginError(String username, String password) {
        final WebElement userNameElement = webDriver.findElement(userNameLocator);
        userNameElement.sendKeys(username);
        final WebElement passwordElement = webDriver.findElement(passwordLocator);
        passwordElement.sendKeys(password);
        final WebElement loginButton = webDriver.findElement(loginButtonLocator);
        loginButton.click();
        return this;
    }

    public String getErrorMessage() {
        return errorMessage.findElement(webDriver).getText();
    }
}
