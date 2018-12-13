package parsers;

import javafx.application.Platform;
import model.Account;
import model.MyCookieHandler;
import model.Program;
import model.Searcher;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.File;
import java.util.HashMap;


public abstract class ServiceParser implements Searcher {

    protected String searchUrl; //Maybe remove or change
    protected ChromeDriver browser;

    public ChromeOptions getOptions() {
        return options;
    }

    protected ChromeOptions options;
    protected MovieInfo hit;
    protected Account account;
    protected MyCookieHandler cookieHandler;
   // protected File file = new File("cookies.data"); //should be in account class

    public ServiceParser(String searchUrl, String fileName) {
        System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
        this.searchUrl = searchUrl;
        this.options = new ChromeOptions();
        this.account = new Account();
        options.setHeadless(false);
        //this.browser = new ChromeDriver(options);
        this.cookieHandler = new MyCookieHandler(options, fileName);

        //set preferences to not load images and to use disk cache
        //--------------------------------------------------
        HashMap<String, Object> prefs = new HashMap<String, Object>();
        prefs.put("profile.managed_default_content_settings.images", 2);
        prefs.put("disk-cache-size", 4096);
        //options.setExperimentalOption("prefs", prefs);
        //--------------------------------------------------
    }
//
//    @Override
//    public MovieInfo search(String movieTitle) {
//        return null;
//    }



    public Account getAccount() {
        return account;
    }

    public MyCookieHandler getCookieHandler() {
        return cookieHandler;
    }

    public void parse(String movieTitle){
        browser = new ChromeDriver(options);
        Platform.runLater(()->Program.addHit(search(movieTitle)) );

//        this.browser = new ChromeDriver(options);
//        Thread thread = new Thread(()-> Program.addHit(search(movieTitle)));
//        thread.start();
    }
//
//    public void loadCookies(){
//
//    }

    public abstract void login();

    public abstract MovieInfo search(String movieTitle);

    //TODO login on startSearch, if cookies out of date: login and gather new ones, else: use cookies to login before search
    //TODO when clicking button for hits on streamingservice; use cookies to login
    //TODO move login part of scripts to proper place and let parser scripts only handle search
    //TODO for efficiency replace element retrieval methods with By.id where possible(fastest way to access elements)
    //TODO replace By.xpath where possible(slowest way to access elements)
    //TODO check if element contains 'movie title' search phrase, not equals
    //TODO how replace '&' with 'and' for retrieved object title target string?
    //TODO replace sleep with listener (in Program) once javafx is connected to Program class
    //TODO research other way to get object returned from thread
}
