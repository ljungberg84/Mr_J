package Test.java;

import model.Program;
import model.Service;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import parsers.HboService;
import parsers.ServiceHandler;


import static org.junit.Assert.*;

public class ProgramTest {

    Program p = new Program();

    @Test
    void servicesShouldContainObject(){

        ServiceHandler hboParser = new HboService();
        p.addService("hbo", hboParser);

        assertThat(p.getServices(), Matchers.hasEntry("hbo", hboParser));
    }

    @Test
    void doesNotLoginWithoutCookies(){
        quickInitialize();

        p.startLogin();
        //should produce output: "startLogin" and "no login or cookies, cant log in"
    }


    @Test
    void doesNotSearchWhenInputIsNull(){

        p.startSearch(null);
        p.startSearch("");
        //should produce output: Error Invalid input

    }

    @Test
    void startsSearchingAndNewThread(){
        quickInitialize();

        p.startSearch("sgdfgs");
        //should produce output: "Starting search" and "Starting thread"
    }

    public void quickInitialize() //helper method to initialize a service to use in tests
    {
        ServiceHandler hboParser = new HboService();
        p.addService("hbo", hboParser);
    }

}