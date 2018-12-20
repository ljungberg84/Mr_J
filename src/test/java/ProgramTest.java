import model.Program;
//import org.hamcrest.Matchers;
import org.junit.Before;
//import org.junit.jupiter.api.Test;

import org.junit.Test;
import parsers.HboService;
import parsers.MovieInfo;
import parsers.NetflixService;
import parsers.ServiceHandler;


import static org.junit.Assert.*;

public class ProgramTest {

    Program p = Program.getInstance();

    @Before
    public void clearServices(){
        p.getServices().clear();
    }

    @Test
    public void startSearchTest(){
        p.getServices().clear();
        assertFalse(p.startSearch(""));
        assertFalse(p.startSearch(null));
        assertTrue(p.startSearch("alien"));
    }

    @Test
    public void startLogin(){
        assertFalse("calling login without acount info",p.startLogin());
    }

    @Test
    public void addHitsTest(){

        p.addHits(new MovieInfo("title", "url", "imgUrl", "source"));
        MovieInfo testMovie = new MovieInfo("title", "url", "imgUrl", "source");
        assertEquals(1, p.getHits().size());
        p.addHits(testMovie);
        assertEquals("adding movie element to list",2, p.getHits().size());
        p.addHits(null);
        assertEquals("adding null to list",2, p.getHits().size());
    }

    @Test
    public void addServiceTest(){

        assertEquals(0, p.getServices().size());
        p.addService( "test", new NetflixService());
        assertEquals("adding service element to list",1 , p.getServices().size());
        p.addService(null, new NetflixService());
        assertEquals("adding null key", 1, p.getServices().size());
        p.addService("test2", null);
        assertEquals("adding null value", 1, p.getServices().size());
    }

    @Test
    public void servicesShouldContainObject(){

        ServiceHandler hboParser = new HboService();
        p.addService("hbo", hboParser);

        //assertThat(p.getServices(), Matchers.hasEntry("hbo", hboParser));
    }

    @Test
    public void doesNotLoginWithCookies(){
        quickInitialize();
        p.startLogin();
        //should produce output: "startLogin" and "no login or cookies, cant log in"
    }


    @Test
    public void doesNotSearchWhenInputIsNull(){

        p.startSearch(null);
        p.startSearch("");
        //should produce output: Error Invalid input

    }

    @Test
    public void startsSearchingAndNewThread(){
        quickInitialize();

        p.startSearch("sgdfgs");
        //should produce output: "Starting search" and "Starting thread"
    }

    void quickInitialize() //helper method to initialize a service to use in tests
    {
        ServiceHandler hboParser = new HboService();
        p.addService("hbo", hboParser);
    }

}