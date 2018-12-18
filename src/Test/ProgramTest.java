package Test;

import model.Program;
import model.Service;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import parsers.HboService;
import parsers.ServiceHandler;

import java.util.Map;

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
        ServiceHandler hboParser = new HboService();
        p.addService("hbo", hboParser);

        p.startLogin();

    }

}