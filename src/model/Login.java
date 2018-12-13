package model;

import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import parsers.MovieInfo;
import parsers.ServiceParser;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.List;

public class Login extends ServiceParser {


    public Login(String rootUrl) {
        super(rootUrl);
    }

    @Override
    protected MovieInfo runScript(String movieTitle) {
        String userName = "gbellak11@gmail.com";
        String password = "gab11bel";
        browser.get("https://www.netflix.com/se/search");
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

            //File file = new File("cookies.data");
            file.delete();
            file.createNewFile();
            FileWriter fileWriter = new FileWriter(file);
            BufferedWriter bWrite = new BufferedWriter(fileWriter);

            for (Cookie ck : browser.manage().getCookies()) {
                bWrite.write((ck.getName()+";"+ck.getValue()+";"+ck.getDomain()+
                        ";"+ck.getPath()+";"+ck.getExpiry()+";"+ck.isSecure()));

                bWrite.newLine();
            }
            bWrite.close();
            fileWriter.close();

        } catch(Exception e){
            e.printStackTrace();
        }finally {
            //browser.close();
        }
        return null;
    }
}
