package parsers;

import model.Account;
import model.Loginable;
import model.MyCookieHandler;
import model.Searcher;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import parsers.ServiceParser;


public class ViaplayParser extends ServiceParser implements Searcher, Loginable {

    public ViaplayParser() {
        super("https://viaplay.se/#search=", "viaplayCookies");
        //super.cookieHandler = new MyCookieHandler(browser, "netflixCookies");
        account.setPassword("");
        account.setUserName("");
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
            System.out.println("Viaplay file exists");
            cookieHandler.loadCookies(browser);
            System.out.println("Loading cookies");
        } else {
            System.out.println("Viaplay file not found");
        }
        browser.get("https://viaplay.se/#search="); //searchUrl
        try {
         //   WebElement searchField = new WebDriverWait(browser, 2).
         //           until(ExpectedConditions.presenceOfElementLocated(By.className("SearchButton-icon-kNXgg")));
         //   searchField.click();

            WebElement itemSearchField = new WebDriverWait(browser, 2).
                    until(ExpectedConditions.presenceOfElementLocated(By.className("Search-input-2AQvs")));
            itemSearchField.sendKeys(movieTitle);

            WebElement movieNode = new WebDriverWait(browser, 2).
                    until(ExpectedConditions.presenceOfElementLocated(By.xpath(
                            "//a[contains(@href, '" + movieTitle.replace(' ', '-') + "')]")));

            String url = movieNode.getAttribute("href");
//            if (url.substring(7, 18).equalsIgnoreCase("kundservice"))
//                throw new TimeoutException();

            System.out.println(url);
//            System.out.println(url.length());
//            System.out.println("--------------------------");
//            System.out.println(movieTitle + " was found on Viaplay");
//            System.out.println("Url: " + url);
////            System.out.println("--------------------------");
//
            return new MovieInfo(movieTitle, url, "Viaplay");

        } catch (TimeoutException e) {
            //System.out.println(movieTitle + " was not found on Viaplay");
            //e.printStackTrace();

        } catch (Exception e) {
            System.out.println("something went wrong");
            System.out.println(e.getMessage());
            e.printStackTrace();
        } finally {
          //  browser.close();
        }
        return null;
    }

    @Override
    public void login() {
        System.out.println("Starting Viaplay login");
        browser = new ChromeDriver(options);
        browser.get("https://viaplay.se/#search=");
        try{
            WebElement logInButton1 = new WebDriverWait(browser,2).
                    until(ExpectedConditions.presenceOfElementLocated(By.className("LoginHeader-menu-3DV_r")));
            logInButton1.click();

            WebElement emailField = new WebDriverWait(browser, 2).
                    until(ExpectedConditions.presenceOfElementLocated(By.className("username")));
            WebElement passwordField = new WebDriverWait(browser, 2).
                    until(ExpectedConditions.presenceOfElementLocated(By.className("password")));

            emailField.sendKeys(account.getUserName());
            passwordField.sendKeys(account.getPassword());

            WebElement logInButton2 = new WebDriverWait(browser,2).
                    until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body/figure/section/div[3]/form[1]/div[4]/input")));
           // Thread.sleep(2000);
            logInButton2.click();
            Thread.sleep(2000);

            cookieHandler.saveCookies(browser);
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            browser.close();
        }
    }
}






















//
//    }
//
//    @Override
//    protected MovieInfo runScript(String movieTitle) {
//        browser.get(rootUrl);
//        try {
//            WebElement logInButton1 = new WebDriverWait(browser,2).
//                    until(ExpectedConditions.presenceOfElementLocated(By.className("LoginHeader-menu-3DV_r")));
//            logInButton1.click();

//
//import org.openqa.selenium.By;
//import org.openqa.selenium.TimeoutException;
//import org.openqa.selenium.WebElement;
//import org.openqa.selenium.support.ui.ExpectedConditions;
//import org.openqa.selenium.support.ui.WebDriverWait;
//
//public class ViaplayParser extends ServiceParser {
//
//    private String userName = "";
//    private String password = "";
//
//    public ViaplayParser() {
//        super("https://www.viaplay.se");
//
//    }
//
//    @Override
//    protected MovieInfo search(String movieTitle) {
//        browser.get(searchUrl);
//        try {

////




//            WebElement searchField = new WebDriverWait(browser, 5).
//                            until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@name='url']")));
//            searchField.sendKeys(movieTitle);
//            WebElement searchButton = new WebDriverWait(browser, 5).
//                            until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@type='submit']")));
//            searchButton.click();
//

//
//            WebElement movieNode = new WebDriverWait(browser, 2).
//                    until(ExpectedConditions.presenceOfElementLocated(By.xpath(
//                            "//a[contains(@href, '" + movieTitle.replace(' ','-') + "')]")));
//



