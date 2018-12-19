package parsers;

import model.MyCookieHandler;
import model.UserAccount;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ViaplayService extends ServiceHandler{

    public ViaplayService() {
        super(new MyCookieHandler("viaplay_cookies"), new UserAccount("",""));
    }

    @Override
    public MovieInfo search(String movieTitle) {

        browser = new ChromeDriver(options);
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
                            "//a[contains(@href, '" + movieTitle.toLowerCase().replace(' ', '-') + "')]")));

            String url = movieNode.getAttribute("href");
            if (url.substring(7, 18).equalsIgnoreCase("kundservice"))
                throw new TimeoutException();

            movieNode = movieNode.findElement(By.xpath("//div[contains(@class, \"front-image Item-image\")]"));

            String imgUrl = movieNode.getAttribute("style");
            imgUrl = imgUrl.substring(imgUrl.indexOf("(") + 2, imgUrl.indexOf("?"));

            //System.out.println("imgUrl: " + imgUrl);

            System.out.println(url);
//            System.out.println(url.length());
//            System.out.println("--------------------------");
//            System.out.println(movieTitle + " was found on Viaplay");
//            System.out.println("Url: " + url);
////            System.out.println("--------------------------");
//
            return new MovieInfo(movieTitle, url, imgUrl, "Viaplay.jpg");

        } catch (TimeoutException e) {
            System.out.println(movieTitle + " was not found on Viaplay");
            //e.printStackTrace();

        } catch (Exception e) {
            System.out.println("something went wrong");
            System.out.println(e.getMessage());
            e.printStackTrace();
        } finally {
            //browser.close();
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
//                    until(ExpectedConditions.presenceOfElementLocated(By.className("LoginHeader-menu-3DV_r")));
                        until(ExpectedConditions.elementToBeClickable(By.className("LoginHeader-menu-3DV_r")));
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
            //browser.close();
        }
    }

    @Override
    public String toString() {
        return "Viaplay";
    }
}
