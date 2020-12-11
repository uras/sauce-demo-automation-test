package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.openqa.selenium.support.ui.ExpectedConditions.elementToBeClickable;

public class BasePage {

    private final By logoutButton = By.id("logout_sidebar_link");
    private final By hamburgerMenu = By.cssSelector(".bm-burger-button button");

    WebDriver webDriver;
    WebDriverWait webDriverWait;

    public BasePage(WebDriver webDriver) {
        this.webDriver = webDriver;
        webDriverWait = new WebDriverWait(webDriver, 10);
    }

    public void logout() {
        webDriver.findElement(hamburgerMenu).click();
        webDriverWait.until(elementToBeClickable(logoutButton)).click();
    }
}
