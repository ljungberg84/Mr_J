import org.junit.Test;
import parsers.MovieInfo;
import parsers.SvtService;

import static org.junit.Assert.*;

public class SvtServiceTest {

    private MovieInfo sunesjul = new MovieInfo("Sunes jul", "https://www.svtplay.se/sunes-jul", "https://www.svtstatic.se/image-cms/svtse/1511946576/oppet-arkiv/article14202712.svt/ALTERNATES/small/sunes-jul-pelle-nordwall-svt-1920-jpg", "Svt play.png");
    private MovieInfo rapport = new MovieInfo("Rapport", "https://www.svtplay.se/rapport", "https://www.svtstatic.se/image-cms/svtse/1512043695/nyhetsprogrammet-rapport/article14251708.svt/ALTERNATES/small/affischbild-rapport-katarina-sandstrom-jpg", "Svt play.png");

    @Test
    public void searchReturnsCorrectResults() throws Exception{
        SvtService svtService = new SvtService();
        MovieInfo searchObject = svtService.search("Sunes jul");
        MovieInfo searchObject2 = svtService.search("Rapport");

        assertEquals(sunesjul.getTitle(), searchObject.getTitle());
        assertEquals(sunesjul.getUrl(), searchObject.getUrl());
        assertEquals(sunesjul.getImagePath(), searchObject.getImagePath());
        assertEquals(sunesjul.getSource(), searchObject.getSource());

        assertEquals(rapport.getTitle(), searchObject2.getTitle());
        assertEquals(rapport.getUrl(), searchObject2.getUrl());
        assertEquals(rapport.getImagePath(), searchObject2.getImagePath());
        assertEquals(rapport.getSource(), searchObject2.getSource());
    }
}