package parsers;

import model.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;


public abstract class ServiceHandler extends WebDriverHandler implements Service {

    protected MyCookieHandler cookieHandler;
    protected UserAccount account;

    public ServiceHandler() {
        this(null, null);
    }

    public ServiceHandler(MyCookieHandler cookieHandler, UserAccount account) {
        this.cookieHandler = cookieHandler;
        this.account = account;
    }

//    @Override
//    public MovieInfo search(String title){
//        browser = new ChromeDriver(options);
//        if(cookieHandler != null){
//            cookieHandler.loadCookies(browser);
//        }
//        //return search(title);
//    }


    public void playMovie(MovieInfo movie){
        ChromeOptions playOptions = new ChromeOptions().setHeadless(false).addArguments("start-maximized");
        browser = new ChromeDriver(playOptions);
        if (hasCookies()){
            cookieHandler.loadCookies(browser);
        }
        browser.get(movie.getUrl());

    }

    @Override
    public Boolean hasLogin(){
        return (account != null && account.hasLogin());
    }

    @Override
    public Boolean hasCookies() {
        return (cookieHandler != null && cookieHandler.isValid());
    }

//    @Override
//    public void login(){
//        if(account != null && account.hasLogin()){
//            if (cookieHandler.isValid()) {
//                System.out.println("expired cookies");
//                return;
//            }
//            else {
//
//            }
//        }
//            if (cookieHandler.isValid()){
//                System.out.println("expired cookies");
//            }else{
//                System.out.println("cookies available, cancelling login");
//                return;
//            }
//        }else{
//            System.out.println("cant log in without account");
//            return;
//        }
//    }

    //public void  searchHandler(String title, List<MovieInfo> hits){
        //browser = new ChromeDriver(options);
        //hits.add(search(title));
        //Program.addHits(search(title));
    //}

//    public MovieInfo  searchHandler(String title){
//        browser = new ChromeDriver(options);
//        return search(title);
//        //hits.add(search(title));
//        //Program.addHits(search(title));
//    }



    public UserAccount getAccount() {
        return this.account;
    }

    public MyCookieHandler getCookieHandler() {
        return cookieHandler;
    }


    //TODO continue with viaplay, return image and stuff
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


