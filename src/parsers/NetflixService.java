package parsers;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class NetflixService extends ServiceHandler {

    public NetflixService() {
        super("netflix_cookies");
    }

    @Override
    public MovieInfo search(String movieTitle) {
        if (cookieHandler.hasExpired()) {
            System.out.println("Netflix cookieFile exists");
            cookieHandler.loadCookies(browser);
            System.out.println("Loading cookies");
        }
        else {
            System.out.println("Netflix cookieFile not found");
        }
        browser.get("https://www.netflix.com/search"); //rootUrl
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
            try{
                WebElement userButton = new WebDriverWait(browser, 10).
                        until(ExpectedConditions.presenceOfElementLocated(By.className("profile-icon")));
                userButton.click();
            }catch (Exception e){

            }


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

            String title = movieNode.getAttribute("aria-label");
            String url = movieNode.getAttribute("href");
//            System.out.println("--------------------------");
//            System.out.println(movieTitle + " was found on Netflix");
//            System.out.println("Url: " + url);
//            System.out.println("--------------------------");
            //browser.close();
            movieNode = movieNode.findElement(By.xpath("//img[@class='boxart-image boxart-image-in-padded-container']"));
            String imgUrl = movieNode.getAttribute("src");
            return new MovieInfo(title, url,imgUrl, "Netflix.png");

        }catch(TimeoutException e){
            //System.out.println(movieTitle + " was not found on Netflix");
            //e.printStackTrace();

        }catch(Exception e){
            //System.out.println("netflix waiting for login fields failed");
            //System.out.println(e.getMessage());
            e.printStackTrace();
        }finally {
            //browser.close();
        }
        return  null;
    }




//    if(service.getAccount().getUserName() != null && service.getAccount().getPassword() != null) {
//                //System.out.println("Username and password found");
//
//                if (service.getCookieHandler().hasExpired()) {
//                    System.out.println("Logging in");
//                    Thread thread = new Thread(service::login);
//                    thread.start();
//                }
//            } else {
//                System.out.println("No username found");
//            }




    @Override
    public void login() {
        if(account.hasLogin()){
            System.out.println("login for netflix found");
            if (cookieHandler.hasExpired()){
                System.out.println("expired cookies");
            }else{
                System.out.println("cookies available, cancelling login");
                return;
            }
        }else{
            System.out.println("cant log in without account");
            return;
        }

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
