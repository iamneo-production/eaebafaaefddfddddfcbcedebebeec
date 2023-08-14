package ai.iamneo.testing.Testing_Selenium_TestNg;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;
import java.io.IOException;
import java.net.URL;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;

public class AppTest {
    WebDriver driver = null;
    String url = "https://www.snapdeal.com";
    ChromeOptions options = new ChromeOptions();

    @BeforeTest
    public void beforeTest() throws IOException {
        System.setProperty("webdriver.chrome.driver", "/home/coder/project/workspace/chromedriver");
        driver = new RemoteWebDriver(new URL("http://localhost:8080"), options);
    }

    @Test
    public void testSnapdealLogin() {
        // Navigate to Snapdeal site
        driver.get(url);

        // Move to Sign In Button and click
        WebElement signInButton = driver.findElement(By.xpath("//div[@class='accountInner']/span[contains(text(),'Sign In')]"));
        Actions actions = new Actions(driver);
        actions.moveToElement(signInButton).click().perform();

        // Enter a valid Email Id and click continue
        WebElement emailInput = driver.findElement(By.id("userName"));
        emailInput.sendKeys("your_valid_email@example.com");
        WebElement continueButton = driver.findElement(By.id("checkUser"));
        continueButton.click();

        // Enter the valid password and click LOGIN
        WebElement passwordInput = driver.findElement(By.id("j_password_login_uc"));
        passwordInput.sendKeys("your_valid_password");
        WebElement loginButton = driver.findElement(By.id("submitLoginUC"));
        loginButton.click();

        // Verify if user is logged in successfully
        try {
            WebElement successMessage = driver.findElement(By.xpath("//div[@class='accountInner']/span[contains(text(),'My Account')]"));
            System.out.println("User is logged in successfully!");
        } catch (Exception e) {
            System.out.println("Login failed!");
        }
    }

    @AfterTest
    public void afterTest() {
        driver.quit();
    }
}
