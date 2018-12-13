package parsers;

import model.Account;
import model.Loginable;
import model.MyCookieHandler;
import model.Searcher;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.StringTokenizer;

public class NetflixParser extends ServiceParser implements Searcher, Loginable {

//    private Account account;
//    private MyCookieHandler cookieHandler;
    //"https://www.netflix.com/se/login"

    public NetflixParser() {
        super("https://www.netflix.com/se/search", "netflixCookies");
        //super.cookieHandler = new MyCookieHandler(browser, "netflixCookies");
        account.setPassword("gab11bel");
        account.setUserName("gbellak11@gmail.com");
    }

    public Account getAccount() {
        return account;
    }

    public MyCookieHandler getCookieHandler() {
        return cookieHandler;
    }

    @Override
    public MovieInfo search(String movieTitle) {
        if (cookieHandler.getFile().exists()) {
            System.out.println("Netflix file exists");
            cookieHandler.loadCookies(browser);
            System.out.println("Loading cookies");
        }
        else {
            System.out.println("Netflix file not found");
        }
        browser.get("https://www.netflix.com/search"); //searchUrl
        try{
//            WebElement emailField = new WebDriverWait(browser, 10).
//                    until(ExpectedConditions.presenceOfElementLocated(By.id("id_userLoginId")));
//            WebElement passwordField = new WebDriverWait(browser, 10).
//                    until(ExpectedConditions.presenceOfElementLocated(By.id("id_password")));
//
//            emailField.sendKeys(userName);
//            passwordField.sendKeys(password);
//
//            List<WebElement> buttonList = browser.findElementsByTagName("Button");
//            WebElement loginButton = buttonList.get(1);
//            loginButton.click();
//
//            WebElement userButton = new WebDriverWait(browser, 10).
//                    until(ExpectedConditions.presenceOfElementLocated(By.className("profile-icon")));
//            userButton.click();

            //example: move to movie url efter cookies
            //browser.get("https://www.netflix.com/watch/80021955?tctx=0%2C0%2C%2C%2C");

            WebElement searchTab = new WebDriverWait(browser, 10).
                    until(ExpectedConditions.presenceOfElementLocated(By.className("searchTab")));
            searchTab.click();

            WebElement itemSearchField = new WebDriverWait(browser, 10).
                    until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@data-uia='search-box-input']")));
            itemSearchField.sendKeys(movieTitle);

            WebElement movieNode = new WebDriverWait(browser, 3).until(ExpectedConditions.
                        presenceOfElementLocated(By.xpath("//a[translate(@aria-label," +
                        " 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz')='" + movieTitle.toLowerCase() + "']")));

            String url = movieNode.getAttribute("href");
//            System.out.println("--------------------------");
//            System.out.println(movieTitle + " was found on Netflix");
//            System.out.println("Url: " + url);
//            System.out.println("--------------------------");
            //browser.close();
            return new MovieInfo(movieNode.getAttribute("aria-label"), url, "Netflix");

        }catch(TimeoutException e){
            //System.out.println(movieTitle + " was not found on Netflix");
            //e.printStackTrace();

        }catch(Exception e){
            //System.out.println("netflix waiting for login fields failed");
            //System.out.println(e.getMessage());
            e.printStackTrace();
        }finally {
            browser.close();
        }
        return  null;
    }

    @Override
    public void login() {
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
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            browser.close();
        }
    }
}
