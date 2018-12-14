package parsers;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HboService extends ServiceHandler{

    public HboService() {
        super("hbo_cookies");
    }

    @Override
    public MovieInfo search(String movieTitle) {

        if (cookieHandler.hasExpired()) {
            System.out.println("Hbo nordic cookieFile exists");
            cookieHandler.loadCookies(browser);
            System.out.println("Loading cookies");
        }
        else {
            System.out.println("Hbo nordic cookieFile not found");
        }
//        if (cookieHandler.getFile().exists()) {
//            System.out.println("HBO file exists");
//            cookieHandler.loadCookies(browser);
//            System.out.println("Loading cookies");
//        } else {
//            System.out.println("HBO file not found");
//        }
        browser.get("https://se.hbonordic.com/search");
        try {
            WebElement searchField = new WebDriverWait(browser, 3).
                    until(ExpectedConditions.presenceOfElementLocated(By.id("search-field")));
            searchField.sendKeys(movieTitle);

            WebElement movieNode = new WebDriverWait(browser, 3).until(ExpectedConditions.
                    presenceOfElementLocated(By.xpath("//a[translate(@aria-label," +
                            " 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz')='" + movieTitle.toLowerCase() + "']")));

            String url = movieNode.getAttribute("href");

            movieNode = movieNode.findElement(By.xpath("//div[translate(@aria-label," +
                    " 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz')='" + movieTitle.toLowerCase() + "']"));



            String title = movieNode.getAttribute("aria-label");
            String imgUrl = movieNode.getAttribute("style");


            System.out.println("title: " + title);
            System.out.println(imgUrl);
            imgUrl = imgUrl.substring(imgUrl.indexOf('(') + 2, imgUrl.indexOf(')') - 1);
            System.out.println("imgurl: " + imgUrl);
            System.out.println("url: " + url);



            return new MovieInfo(movieNode.getAttribute("aria-label"), url,imgUrl, "HBO Nordic.jpg");

        } catch (TimeoutException e) {
            //System.out.println(movieTitle + " was not found on Netflix");
            //e.printStackTrace();

        } catch (Exception e) {
            //System.out.println("netflix waiting for login fields failed");
            //System.out.println(e.getMessage());
            e.printStackTrace();
        } finally {
            //browser.close();
        }
        return null;
    }

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
