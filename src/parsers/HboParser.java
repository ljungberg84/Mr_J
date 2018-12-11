package parsers;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HboParser extends ServiceParser {

    public HboParser() {
        super("https://se.hbonordic.com/search");
    }

    @Override
    protected MovieInfo runScript(String movieTitle) {
        browser.get(rootUrl);
        try{
            WebElement searchField = new WebDriverWait(browser, 3).
                    until(ExpectedConditions.presenceOfElementLocated(By.id("search-field")));
            searchField.sendKeys(movieTitle);
            WebElement movieNode = new WebDriverWait(browser, 3).until(ExpectedConditions.
                    presenceOfElementLocated(By.xpath("//a[translate(@aria-label,"+
                            " 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz')='" + movieTitle.toLowerCase() + "']")));

            String url = movieNode.getAttribute("href");
//            System.out.println("--------------------------");
//            System.out.println(movieTitle + " was found on HBO nordic");
//            System.out.println("Url: " + url);
//            System.out.println("--------------------------");
            //browser.close();
            return new MovieInfo(movieNode.getAttribute("aria-label"), url, "HBO Nordic");

        }catch(TimeoutException e){
            System.out.println(movieTitle + " was not found on HBO nordic");
            //e.printStackTrace();

        }catch(Exception e){
            System.out.println("hbo something went wrong");
            System.out.println(e.getMessage());
            e.printStackTrace();
        }finally {
            browser.close();
        }
        return null;
    }
}
