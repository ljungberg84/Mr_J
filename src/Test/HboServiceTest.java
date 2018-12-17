package Test;

import org.junit.jupiter.api.Test;
import parsers.HboService;
import parsers.MovieInfo;

import static org.junit.Assert.*;

public class HboServiceTest {

    private MovieInfo vikings = new MovieInfo("Vikings",
            "https://se.hbonordic.com/series/vikings/99bee710-f406-4a98-a319-f929eedbdabf",
            "https://static.hbonordic.com/categoryThumbnails/b6aa58b9-3189-44c0-813a-5f4c2c60a562.jpg", "Hbo.jpg");

    @Test
    public void searchReturnsCorrectResults() {
        HboService hbo = new HboService();
        MovieInfo object = hbo.search("vikings");

        assertEquals("Returns wrong source", object.getSource(), vikings.getSource());
        assertEquals("Returns wrong title", object.getTitle(), vikings.getTitle());
        assertEquals("Wrong url", object.getUrl(), vikings.getUrl());
        assertEquals("Wrong image path", object.getImagePath(), vikings.getImagePath());
    }

    @Test
    public void login() {
    }
}