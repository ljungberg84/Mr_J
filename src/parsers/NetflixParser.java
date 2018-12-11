package parsers;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class NetflixParser extends ServiceParser {

    private String userName = "";
    private String password = "";

    public NetflixParser() {
        super("https://www.netflix.com/se/login");
    }

    @Override
    protected MovieInfo runScript(String movieTitle) {
        browser.get(rootUrl);
        try{
            WebElement emailField = new WebDriverWait(browser, 10).
                    until(ExpectedConditions.presenceOfElementLocated(By.id("id_userLoginId")));
            WebElement passwordField = new WebDriverWait(browser, 10).
                    until(ExpectedConditions.presenceOfElementLocated(By.id("id_password")));

            emailField.sendKeys(userName);
            passwordField.sendKeys(password);

            List<WebElement> buttonList = browser.findElementsByTagName("Button");
            WebElement loginButton = buttonList.get(1);
            loginButton.click();

            WebElement userButton = new WebDriverWait(browser, 10).
                    until(ExpectedConditions.presenceOfElementLocated(By.className("profile-icon")));
            userButton.click();

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
            System.out.println("netflix waiting for login fields failed");
            System.out.println(e.getMessage());
            e.printStackTrace();
        }finally {
            browser.close();
        }
        return  null;
    }
}
