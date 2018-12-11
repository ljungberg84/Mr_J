package parsers;

import model.Program;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.HashMap;


public abstract class ServiceParser implements Parsable {

    protected String rootUrl;
    protected ChromeDriver browser;
    protected ChromeOptions options;
    protected String driverPath = ""; // driver path needs to be specified
    protected MovieInfo hit;


    public ServiceParser(String rootUrl) {
        System.setProperty("webdriver.chrome.driver", driverPath);
        this.rootUrl = rootUrl;
        this.options = new ChromeOptions();
        options.setHeadless(true);

        //set preferences to not load images
        //--------------------------------------------------
        HashMap<String, Object> prefs = new HashMap<String, Object>();
        prefs.put("profile.managed_default_content_settings.images", 2);
        options.setExperimentalOption("prefs", prefs);
        //--------------------------------------------------
    }

    @Override
    public void parse(String movieTitle){
        this.browser = new ChromeDriver(options);
        Thread thread = new Thread(()-> Program.addHit(runScript(movieTitle)));
        thread.start();
    }

    protected abstract MovieInfo runScript(String movieTitle);

    //TODO for efficiency replace element retrieval methods with By.id where possible(fastest way to access elements)
    //TODO replace By.xpath where possible(slowest way to access elements)
    //TODO check if element contains 'movie title' search phrase, not equals
    //TODO how replace '&' with 'and' for retrieved object title target string
    //TODO replace sleep with listener (in Program) once javafx is connected to Program class
    //TODO research other way to get object returned from thread
}
