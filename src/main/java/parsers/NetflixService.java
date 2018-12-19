package parsers;

import model.MyCookieHandler;
import model.UserAccount;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class NetflixService extends ServiceHandler {

    public NetflixService(){
        this(new MyCookieHandler("netflix_cookies"), new UserAccount("", ""));
    }

    public NetflixService(MyCookieHandler cookieHandler, UserAccount account) {
        super(cookieHandler, account);
    }

    @Override
    public UserAccount getAccount() {
        return super.getAccount();
    }

    @Override
    public MovieInfo search(String movieTitle) {
        browser = new ChromeDriver(options);
        //super.search(movieTitle);
        if (hasCookies()) {
            System.out.println("Netflix cookies exists");
            cookieHandler.loadCookies(browser);
            System.out.println("Loading cookies");
        } else {
            System.out.println("Netflix cookies not found");
        }
        browser.get("https://www.netflix.com/search"); //rootUrl
        try {
            try {
                WebElement userButton = new WebDriverWait(browser, 10).
                        until(ExpectedConditions.presenceOfElementLocated(By.className("profile-icon")));
                userButton.click();
            } catch (Exception e) {

            }

            WebElement searchTab = new WebDriverWait(browser, 10).
                    until(ExpectedConditions.presenceOfElementLocated(By.className("searchTab")));
            searchTab.click();

            WebElement itemSearchField = new WebDriverWait(browser, 10).
                    until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@data-uia='search-box-input']")));
            itemSearchField.sendKeys(movieTitle);

            WebElement movieNode = new WebDriverWait(browser, 3).until(ExpectedConditions.
                    presenceOfElementLocated(By.xpath("//a[translate(@aria-label," +
                            " 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz')='" + movieTitle.toLowerCase() + "']")));


            String title = movieNode.getAttribute("aria-label");
            String url = movieNode.getAttribute("href");

            movieNode = movieNode.findElement(By.xpath("//img[@class='boxart-image boxart-image-in-padded-container']"));
            //get image url from the remaining movie element
            String imgUrl = movieNode.getAttribute("src");
            return new MovieInfo(title, url, imgUrl, "Netflix.png");

        } catch (TimeoutException e) {
            System.out.println(movieTitle + " was not found on Netflix");

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            browser.close();
        }
        return null;
    }

    @Override
    public void login () {

        System.out.println("Starting netflix login");
        browser = new ChromeDriver(options);
        browser.get("https://www.netflix.com/se/search");
        try {
            WebElement emailField = new WebDriverWait(browser, 10).
                    until(ExpectedConditions.presenceOfElementLocated(By.id("id_userLoginId")));

            WebElement passwordField = new WebDriverWait(browser, 10).
                    until(ExpectedConditions.presenceOfElementLocated(By.id("id_password")));

            emailField.sendKeys(account.getUserName());
            passwordField.sendKeys(account.getPassword());

            List<WebElement> buttonList = browser.findElementsByTagName("Button");
            WebElement loginButton = buttonList.get(1);
            loginButton.click();

            WebElement userButton = new WebDriverWait(browser, 10).
                    until(ExpectedConditions.presenceOfElementLocated(By.className("profile-icon")));
            userButton.click();

            cookieHandler.saveCookies(browser);
            System.out.println("netflix logged in");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //browser.close();
        }
    }


    @Override
    public String toString() {
        return "Netflix";
    }
}
