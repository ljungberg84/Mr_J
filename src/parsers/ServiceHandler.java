package parsers;

import model.MyCookieHandler;
import model.Program;
import model.UserAccount;
import model.WebDriverHandler;
import org.openqa.selenium.Platform;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;


public abstract class ServiceHandler extends WebDriverHandler {

    protected MyCookieHandler cookieHandler;
    protected UserAccount account;

    public ServiceHandler(String cookieFileName) {
        this.cookieHandler = new MyCookieHandler(cookieFileName);
        this.account = new UserAccount();
    }

    public abstract MovieInfo search(String title);

    public abstract void login();

    public void  searchHandler(String title, List<MovieInfo> hits){
        browser = new ChromeDriver(options);
        //hits.add(search(title));
        Program.addHits(search(title));
    }

    public UserAccount getAccount() {
        return account;
    }

    public MyCookieHandler getCookieHandler() {
        return cookieHandler;
    }


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
