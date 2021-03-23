import org.junit.*;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.HomePage;
import pages.LoginPage;
import pages.SignupPage;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class SeleniumTests {
    private static final String username = "Starlight1313";
    private static final String password = "kNOpKA123";
    private static final String invalidUsername = "asdfeadw";
    private static final String invalidPassword = "dsfrgwef";
    private static final String keyword = "spelunky";

    private static final long timeOutInSeconds = 10;

    private WebDriverWait waiter;
    private WebDriver driver;

    @Before
    public void start() {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver");
        driver = createWebDriver();
        waiter = createWebDriverWait(driver);
        System.out.println("Test start");
    }

    @Test
    public void aTestLoginValidData() {
        HomePage homePage = new HomePage(driver, waiter);
        String loggedIn = homePage.loginValidUser(username, password).getUsername();

        Assert.assertEquals(loggedIn, username);
    }

    @Test
    public void bTestSearch() {
        HomePage homePage = new HomePage(driver, waiter);
        homePage.login(username, password);
        List<String> searchResults = homePage.search(keyword).getTitles();
        boolean flag = true;
        for (String searchResult : searchResults) {
            if (!searchResult.toLowerCase().contains(keyword))
                flag = false;
        }

        Assert.assertEquals(true, flag);
    }

    @Test
    public void cTestLoginInvalidData() {
        HomePage homePage = new HomePage(driver,waiter);
        String error = homePage.loginInvalidUser(invalidUsername, invalidPassword).getError();

        Assert.assertEquals("Вы ввели неверное/неактивное имя пользователя или неверный пароль", error );
    }

    @Test
    public void dTestLoginInvalidCaptcha() {
        LoginPage loginPage = new LoginPage(driver, waiter);
        String error = loginPage.loginWithInvalidCaptcha(invalidUsername, invalidPassword, "sed").getError();

        Assert.assertEquals(error, "Пожалуйста, введите код подтверждения (символы, изображенные на картинке)");
    }

    @Test
    public void eTestSignupExistingUser() {
        SignupPage signupPage = new SignupPage(driver, waiter);
        String error = signupPage.signupExistingUser(username);

        Assert.assertEquals("Пользователь с таким именем уже существует", error);
    }



    @After
    public void close() {
        driver.quit();
        System.out.println("Test end");
    }




    private WebDriver createWebDriver() {
        return new ChromeDriver(createChromeOptions());
    }

    private WebDriverWait createWebDriverWait(WebDriver driver) {
        return new WebDriverWait(driver, timeOutInSeconds);
    }

    private ChromeOptions createChromeOptions() {
        Map<String, Object> prefs = new HashMap();
        prefs.put("profile.default_content_setting_values.notifications", 2);
        ChromeOptions options = new ChromeOptions();
        options.setExperimentalOption("prefs", prefs);
        options.setPageLoadStrategy(PageLoadStrategy.EAGER);
        return options;
    }



}
