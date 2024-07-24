import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.TimeoutException;

import java.time.Duration;

public class LoginSwagLabsTests {
    private WebDriver driver;
    private WebDriverWait globalWait;
    private String URL_SWAGLABS = "https://www.saucedemo.com/";
    private By CAMPO_LOGIN = By.xpath("//input[@id='user-name']");
    private By CAMPO_SENHA = By.xpath("//input[@id='password']");
    private By BTN_LOGIN_FORMULARIO = By.xpath("//input[@id='login-button']");

    @BeforeEach
    public void setup() {
        driver = new ChromeDriver();
        globalWait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @Test
    public void testEfetuaLogin() {
        driver.get(URL_SWAGLABS);
        preencheFormularioSwagLabs("standard_user", "secret_sauce");
    }

    private void preencheFormularioSwagLabs(String user, String password) {
        sendKeys(CAMPO_LOGIN, user);
        sendKeys(CAMPO_SENHA, password);
        click(BTN_LOGIN_FORMULARIO);
    }

    private void sendKeys(By locator, String msg) {
        try {
            WebElement element = globalWait.until(ExpectedConditions.elementToBeClickable(locator));
            element.sendKeys(msg);
        } catch (TimeoutException e) {
            throw new AssertionError("Erro ao acessar o elemento");
        }
    }

    private void click(By locator) {
        try {
            WebElement element = globalWait.until(ExpectedConditions.elementToBeClickable(locator));
            element.click();
        } catch (TimeoutException e) {
            throw new AssertionError("Erro ao acessar o elemento");
        }
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
