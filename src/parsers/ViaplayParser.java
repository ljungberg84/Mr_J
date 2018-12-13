package parsers;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ViaplayParser extends ServiceParser {

    private String userName = "bkay_gbg@hotmail.com";
    private String password = "Pierdole86";

    public ViaplayParser() {
        super("https://www.viaplay.se");

    }

    @Override
    protected MovieInfo runScript(String movieTitle) {
        browser.get(rootUrl);
        try {
//            WebElement logInButton1 = new WebDriverWait(browser,2).
//                    until(ExpectedConditions.presenceOfElementLocated(By.className("LoginHeader-menu-3DV_r")));
//            logInButton1.click();
//
//            WebElement emailField = new WebDriverWait(browser, 2).
//                    until(ExpectedConditions.presenceOfElementLocated(By.className("username")));
//            WebElement passwordField = new WebDriverWait(browser, 2).
//                    until(ExpectedConditions.presenceOfElementLocated(By.className("password")));
//
//            emailField.sendKeys(userName);
//            passwordField.sendKeys(password);
//
//            WebElement logInButton2 = new WebDriverWait(browser,2).
//                    until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body/figure/section/div[3]/form[1]/div[4]/input")));
//            logInButton2.click();
//
//            WebElement searchField = new WebDriverWait(browser,2).
//                    until(ExpectedConditions.presenceOfElementLocated(By.className("SearchButton-icon-kNXgg")));
//            searchField.click();
//
//            WebElement itemSearchField = new WebDriverWait(browser, 2).
//                    until(ExpectedConditions.presenceOfElementLocated(By.className("Search-input-2AQvs")));
//            itemSearchField.sendKeys(movieTitle);
            WebElement searchField = new WebDriverWait(browser, 5).
                            until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@name='url']")));
            searchField.sendKeys(movieTitle);
            WebElement searchButton = new WebDriverWait(browser, 5).
                            until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@type='submit']")));
            searchButton.click();

//            WebElement movieNode = new WebDriverWait(browser, 2).
//                                  until(ExpectedConditions.presenceOfElementLocated(By.xpath(
//                                  "//a[contains(@href, '" + movieTitle.replace(' ','-') + "')]")));

            WebElement movieNode = new WebDriverWait(browser, 2).
                    until(ExpectedConditions.presenceOfElementLocated(By.xpath(
                            "//a[contains(@href, '" + movieTitle.replace(' ','-') + "')]")));

            String url = movieNode.getAttribute("href");
            if (url.substring(7, 18).equalsIgnoreCase("kundservice"))
                throw new TimeoutException();
//            System.out.println(url);
//            System.out.println(url.length());
//            System.out.println("--------------------------");
//            System.out.println(movieTitle + " was found on Viaplay");
//            System.out.println("Url: " + url);
//            System.out.println("--------------------------");

            return new MovieInfo(movieTitle, url,"Viaplay");

        } catch (TimeoutException e) {
            //System.out.println(movieTitle + " was not found on Viaplay");
            //e.printStackTrace();

        } catch (Exception e) {
            System.out.println("something went wrong");
            System.out.println(e.getMessage());
            e.printStackTrace();
        }finally {
            //browser.close();
        }
        return null;
    }
}