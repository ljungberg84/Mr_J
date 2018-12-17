package Test;

import org.junit.jupiter.api.Test;
import parsers.HboService;
import parsers.MovieInfo;

import static org.junit.Assert.*;

public class HboServiceTest {

    private Object MovieInfo;

    @Test
    public void searchReturnsResult() {
        HboService hbo = new HboService();
        MovieInfo object = hbo.search("vikings");

        assertEquals(MovieInfo, object);
    }

    @Test
    public void login() {
    }
}