
import model.Program;
import org.hamcrest.Matchers;
import org.junit.Test;
import parsers.MovieInfo;
import parsers.NetflixService;

import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.text.IsEqualIgnoringCase.equalToIgnoringCase;
import static org.junit.Assert.*;

public class NetflixServiceTest {

    NetflixService netflix = new NetflixService();
    MovieInfo ozark = new MovieInfo("Ozark", "https://www.netflix.com/se/title/80117552", "Netflix.png");

    @Test
    public void searchReturnsCorrectResults() {

        MovieInfo object = netflix.search("ozark");

        assertThat(object, hasProperty("title", equalToIgnoringCase(ozark.getTitle())));
        assertThat(object, hasProperty("url", equalToIgnoringCase(ozark.getUrl())));
        assertThat(object, hasProperty("imagePath", equalToIgnoringCase(ozark.getImagePath())));
        assertThat(object, hasProperty("source", equalToIgnoringCase(ozark.getSource())));

    }

    @org.junit.jupiter.api.Test
    void searchReturnsNullWhenItHasNoArguments(){
        MovieInfo object = netflix.search("");

        assertThat(object.getTitle(), Matchers.nullValue());
    }

    @org.junit.jupiter.api.Test
    public void loggingInWithValidInfoCreatesCookie() {
        netflix.login();

        assertTrue(netflix.getCookieHandler().getCookieFile().exists());
    }


//    @org.junit.jupiter.api.Test
//    public void addHitsTest(){
//        Program p = new Program();
//        MovieInfo testMovie = new MovieInfo("title", "url", "imgUrl", "source");
//        assertEquals(0, p.getHits().size());
//        p.addHits(testMovie);
//        assertEquals("adding movie element to list",1, p.getHits().size());
//        p.addHits(null);
//        assertEquals("adding null to list",1, p.getHits().size());
//    }

}