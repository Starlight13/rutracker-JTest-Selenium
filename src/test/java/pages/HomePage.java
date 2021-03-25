package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class HomePage {
    private WebDriver driver;
    private WebDriverWait waiter;

    private By byLoginTab = By.xpath("/html/body/div[4]/div[1]/div[1]/div[3]/table/tbody/tr/td/div/a[2]");
    private By byUsernameInput = By.id("top-login-uname");
    private By byPasswordInput = By.id("top-login-pwd");
    private By byLoginButton = By.id("top-login-btn");
    private By byUsername = By.id("logged-in-username");
    private By bySearchBar = By.id("search-text");
    private By bySearch = By.id("search-submit");

    public HomePage(WebDriver driver, WebDriverWait waiter){
        this.driver = driver;
        this.waiter = waiter;
        driver.get("https://rutracker.net/forum/index.php");
    }

    public void login(String username, String password){
        driver.findElement(byLoginTab).click();
        waiter.until(ExpectedConditions.visibilityOfElementLocated(byUsernameInput));
        driver.findElement(byUsernameInput).sendKeys(username);
        driver.findElement(byPasswordInput).sendKeys(password);
        driver.findElement(byLoginButton).click();
    }

    public HomePage loginValidUser(String username, String password) {
        login(username, password);
        return new HomePage(driver, waiter);
    }

    public LoginPage loginInvalidUser(String username, String password) {
        login(username,password);
        return new LoginPage(driver, waiter);
    }

    public String getUsername() {
        waiter.until(ExpectedConditions.visibilityOfElementLocated(byUsername));
        return driver.findElement(byUsername).getText();
    }

    public HomePage search(String keyword) {
        waiter.until(ExpectedConditions.visibilityOfElementLocated(bySearchBar));
        driver.findElement(bySearchBar).sendKeys(keyword);
        driver.findElement(bySearch).click();
        return this;
    }

    public List<String> getTitles(){
        waiter.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".row4.med.tLeft.t-title-col")));
        List<WebElement> titleDivs = driver.findElements(By.cssSelector(".row4.med.tLeft.t-title-col"));
        List<String> titles = new ArrayList<String>();
        for (WebElement element : titleDivs){
            titles.add(element.findElement(By.tagName("a")).getText());
        }
        return titles;
    }
}
