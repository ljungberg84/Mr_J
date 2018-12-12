package parsers;

import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
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

public class NetflixParser extends ServiceParser {

    private String userName = "";
    private String password = "";
    //"https://www.netflix.com/se/login"

    public NetflixParser() {
        super("https://www.netflix.com/se/search");
    }

    @Override
    public void loadCookies() {
        browser.get("https://www.netflix.com/se/");
        try{
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            String strLine;
            while((strLine=bufferedReader.readLine()) != null){
                StringTokenizer token = new StringTokenizer(strLine, ";");

                while(token.hasMoreTokens()){
                    String name = token.nextToken();
                    System.out.println("name: " + name);
                    String value = token.nextToken();
                    String domain = token.nextToken();
                    String path = token.nextToken();
                    Date expiry = null;

                    System.out.println("efter token gets");

                    String val;
                    if(!(val = token.nextToken()).equals("null")){
                        System.out.println("date: " + val);
                        //Long date = Date.parse(val);
                        //Long date = Date.parse(val);
                        //expiry = new Date(val);

                        //"EEE MMM dd HH:mm:ss zzz yyyy"

                        SimpleDateFormat f = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy", Locale.UK);
                        try {
                            Date d = f.parse(val);
                            long milliseconds = d.getTime();
                            //System.out.println("new date: " + d);
                            expiry = new Date(milliseconds);
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                    }
                    Boolean isSecure = Boolean.parseBoolean(token.nextToken());

                    Cookie ck = new Cookie(name, value, domain, path, expiry, isSecure);
                    System.out.println(ck);
                    System.out.println("ad cookie");
                    browser.manage().addCookie(ck);
                }
            }

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    protected MovieInfo runScript(String movieTitle) {
        loadCookies();
        browser.get("https://www.netflix.com/search");//rootUrl
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
            WebElement userButton = new WebDriverWait(browser, 10).
                    until(ExpectedConditions.presenceOfElementLocated(By.className("profile-icon")));
            userButton.click();

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
}
