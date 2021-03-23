package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage {
    private WebDriver driver;
    private WebDriverWait waiter;

    private By byErrorMessage = By.cssSelector(".warnColor1");
    private By byCaptcha = By.xpath("/html/body/div[4]/div[1]/div[2]/table/tbody/tr/td/div/form/table/tbody/tr[2]/td/div/table/tbody/tr[3]/td[2]/div[2]/input[2]");
    private By byUsername = By.xpath("/html/body/div[4]/div[1]/div[2]/table/tbody/tr/td/div/form/table/tbody/tr[2]/td/div/table/tbody/tr[1]/td[2]/input");
    private By byPassword = By.xpath("/html/body/div[4]/div[1]/div[2]/table/tbody/tr/td/div/form/table/tbody/tr[2]/td/div/table/tbody/tr[2]/td[2]/input");
    private By byLogin = By.xpath("/html/body/div[4]/div[1]/div[2]/table/tbody/tr/td/div/form/table/tbody/tr[2]/td/div/table/tbody/tr[5]/td/input");

    public LoginPage(WebDriver driver, WebDriverWait waiter){
        this.driver = driver;
        this.waiter = waiter;
    }


    public LoginPage loginWithInvalidCaptcha(String username, String password, String captcha) {
        driver.get("https://rutracker.net/forum/login.php");
        driver.findElement(byUsername).sendKeys(username);
        driver.findElement(byPassword).sendKeys(password);
        driver.findElement(byCaptcha).sendKeys(captcha);
        driver.findElement(byLogin).click();
        return this;
    }

    public String getError() {
        waiter.until(ExpectedConditions.visibilityOfElementLocated(byErrorMessage));
        return driver.findElement(byErrorMessage).getText();
    }
}
