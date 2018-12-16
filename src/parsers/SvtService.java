//package parsers;
//
//import org.openqa.selenium.By;
//import org.openqa.selenium.TimeoutException;
//import org.openqa.selenium.WebElement;
//import org.openqa.selenium.support.ui.ExpectedConditions;
//import org.openqa.selenium.support.ui.WebDriverWait;
//
//
//public class SvtService extends ServiceHandler {
//
//    public SvtService() {
//        super("https://www.svtplay.se", "svtCookies");
//    }
//
//    @Override
//    public void login() {
//        System.out.println("SVT has no login");
//    }
//
//    @Override
//    public MovieInfo search(String movieTitle) {
//        System.out.println("Starting SVT");
//        browser.get("https://www.svtplay.se");
//        try{
//            WebElement searchField = new WebDriverWait(browser, 5).
//                    until(ExpectedConditions.presenceOfElementLocated(By.id("q")));
//            searchField.sendKeys(movieTitle);
//
//            WebElement searchTab = new WebDriverWait(browser, 5).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[@class='play_search__submit lp_button']")));
//            searchTab.click();
//            WebElement movieElement = new WebDriverWait(browser, 5).until(ExpectedConditions.presenceOfElementLocated
//                    (By.xpath("//a//span[@class='play_search-suggestions__link-text play_js-search-suggestion-title'][contains(translate(text(), "+
//                            "'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'),'" + movieTitle.toLowerCase() + "')]")));
//
//
//
//            WebElement urlElement = new WebDriverWait(browser, 5).until(ExpectedConditions.presenceOfElementLocated
//                    (By.xpath("//a[@class='play_search-result__link']")));
//
//            String url = urlElement.getAttribute("href");
//            System.out.println("Returning movieInfo object");
//
//            return new MovieInfo(movieTitle, url, "SVT Play");
//
//        }catch(TimeoutException e){
//            //System.out.println(movieTitle + " was not found on SVTPlay");
//            //e.printStackTrace();
//
//        }finally {
//            browser.close();
//        }
//        return  null;
//    }
//}
