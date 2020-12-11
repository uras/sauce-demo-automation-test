import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.LoginPage;

import static org.testng.Assert.assertEquals;

public class LoginTest {

    public WebDriver webDriver;

    @BeforeMethod
    public void setUp() {
        webDriver = new ChromeDriver();
        System.setProperty("webdriver.chrome.driver", "chromedriver");
        webDriver.get("https://www.saucedemo.com/");
    }

    @Test
    public void wrong_username_password_test() {
        LoginPage loginPage = new LoginPage(webDriver);
        loginPage = loginPage.loginError("wrong-user", "wrong-pass");
        final String errorMessage = loginPage.getErrorMessage();
        assertEquals("Epic sadface: Username and password do not match any user in this service", errorMessage);
    }

    @AfterMethod
    public void tearDown() {
        webDriver.quit();
    }
}
