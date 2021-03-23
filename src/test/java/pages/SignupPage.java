package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.lang.reflect.Array;
import java.util.*;

public class SignupPage {
    private WebDriver driver;
    private WebDriverWait waiter;

    private By byTerms = By.cssSelector(".bold.x-long");
    private By byUsername = By.id("reg-username");
    private By byUserExistsError = By.id("reg-error-username");
    private By byCaptcha = By.xpath("/html/body/div[4]/div[1]/div[2]/table/tbody/tr/td/div/form/table/tbody/tr[6]/td[2]/div[2]/input[2]");

    public SignupPage(WebDriver driver, WebDriverWait waiter){
        this.driver = driver;
        this.waiter = waiter;
        driver.get("https://rutracker.net/forum/profile.php?mode=register");
    }

    public String signupExistingUser(String username){
        driver.findElement(byTerms).click();
        waiter.until(ExpectedConditions.visibilityOfElementLocated(byUsername));
        driver.findElement(byUsername).sendKeys(username);
        driver.findElement(byCaptcha).click();
        waiter.until(ExpectedConditions.visibilityOfElementLocated(byUserExistsError));
        return driver.findElement(byUserExistsError).getText();
    }
}
