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

public class HboParser extends ServiceParser implements Searcher, Loginable {

    public HboParser() {
        super("https://se.hbonordic.com/search", "HBOCookies");
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
            System.out.println("HBO file exists");
            cookieHandler.loadCookies(browser);
            System.out.println("Loading cookies");
        } else {
            System.out.println("HBO file not found");
        }
        browser.get("https://se.hbonordic.com/search");
        try {
            WebElement searchField = new WebDriverWait(browser, 3).
                    until(ExpectedConditions.presenceOfElementLocated(By.id("search-field")));
            searchField.sendKeys(movieTitle);
            WebElement movieNode = new WebDriverWait(browser, 3).until(ExpectedConditions.
                    presenceOfElementLocated(By.xpath("//a[translate(@aria-label," +
                            " 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz')='" + movieTitle.toLowerCase() + "']")));

            String url = movieNode.getAttribute("href");

            return new MovieInfo(movieNode.getAttribute("aria-label"), url, "HBO");

        } catch (TimeoutException e) {
            //System.out.println(movieTitle + " was not found on Netflix");
            //e.printStackTrace();

        } catch (Exception e) {
            //System.out.println("netflix waiting for login fields failed");
            //System.out.println(e.getMessage());
            e.printStackTrace();
        } finally {
            browser.close();
        }
        return null;
    }

    @Override
    public void login() {
        System.out.println("Starting HBO login");
        browser = new ChromeDriver(options);
        browser.get("https://se.hbonordic.com/sign-in");
        try{

            WebElement emailField = new WebDriverWait(browser, 2).
                    until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"email\"]")));

            WebElement passwordField = new WebDriverWait(browser, 2).
                    until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"app\"]/div/div[1]/div/main/div/div[2]/div/div[2]/form/label[2]/div[2]/input")));

            emailField.sendKeys(account.getUserName());
            passwordField.sendKeys(account.getPassword());

            WebElement logInButtonPassword = new WebDriverWait(browser, 2).
                    until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"app\"]/div/div[1]/div/main/div/div[2]/div/div[2]/form/div[1]/div/button")));
            logInButtonPassword.click();

            WebDriverWait wait = new WebDriverWait(browser, 2);
            WebElement afterLogInButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"app\"]/div/div[1]/div/ol/li[1]/button/div")));
            Thread.sleep(2000);
            afterLogInButton.click();

            WebDriverWait wait1 = new WebDriverWait(browser, 2);
            WebElement searchFieldButton = wait1.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"app\"]/div/div[1]/header/div/button")));
            //Thread.sleep(2000);
            searchFieldButton.click();
            cookieHandler.saveCookies(browser);
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            browser.close();
        }





        }

}



//
//    public HboParser() {
//        super("https://se.hbonordic.com/search");
//    }
//
//    @Override
//    protected MovieInfo search(String movieTitle) {
//        browser.get(searchUrl);
//        try{


////            System.out.println("--------------------------");
////            System.out.println(movieTitle + " was found on HBO nordic");
////            System.out.println("Url: " + url);
////            System.out.println("--------------------------");
//            //browser.close();
//            return new MovieInfo(movieNode.getAttribute("aria-label"), url, "HBO Nordic");
//
//        }catch(TimeoutException e){
//            //System.out.println(movieTitle + " was not found on HBO nordic");
//            //e.printStackTrace();
//
//        }catch(Exception e){
//            System.out.println("hbo something went wrong");
//            System.out.println(e.getMessage());
//            e.printStackTrace();
//        }finally {
//            browser.close();
//        }
//        return null;
//    }
//}
