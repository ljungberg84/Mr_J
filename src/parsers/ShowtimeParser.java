//package parsers;
//
//import org.openqa.selenium.WebElement;
//
//public class ShowtimeParser extends ServiceHandler {
//
//    public ShowtimeParser() {
//        super("https://www.sho.com/");
//    }
//
//    @Override
//    protected MovieInfo search(String movieTitle) {
//        browser.get(searchUrl);
//        try{
//            WebElement searchField = browser.findElementByClassName("global-navigation__search-field");
//            searchField.sendKeys("hello world");
//
//        //}catch (TimeoutException e){
//
//        }catch (Exception e){
//
//        }
//        return null;
//    }
//
//}
