package model;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.HashMap;

public class WebDriverHandler {

    protected ChromeDriver browser;
    protected ChromeOptions options;

    public WebDriverHandler() {
        System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
        this.options = new ChromeOptions();
        options.setHeadless(true);

        //set preferences to not load images and to use disk cache
        HashMap<String, Object> prefs = new HashMap<String, Object>();
        prefs.put("profile.managed_default_content_settings.images", 2);
        prefs.put("disk-cache-size", 4096);
        //options.setExperimentalOption("prefs", prefs);
    }
}
