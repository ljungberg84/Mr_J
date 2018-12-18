package Test;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import parsers.MovieInfo;
import parsers.ViaplayService;

import java.util.ArrayList;

import static org.hamcrest.Matchers.equalToIgnoringCase;
import static org.hamcrest.Matchers.hasProperty;
import static org.junit.Assert.*;


public class ViaplayServiceTest {

    public static void main(String []args){


        // Creating testdata
        ArrayList<MovieInfo> movies = new ArrayList<>();


        MovieInfo m1 = new MovieInfo("Girl","https://viaplay.se/film/girls-trip-2017",
                "https://i-viaplay-com.akamaized.net/viaplay-prod/a4fa339/1515769757-bcde18aeae8c622419fda9eb58a87cb4a21bae3f.jpg", "Viaplay.jpg" );
        MovieInfo m2 = new MovieInfo("girl","https://viaplay.se/film/girls-trip-2017",
                "https://i-viaplay-com.akamaized.net/viaplay-prod/a4fa339/1515769757-bcde18aeae8c622419fda9eb58a87cb4a21bae3f.jpg", "Viaplay.jpg");
        MovieInfo m3 = new MovieInfo("Startup", "https://viaplay.se/serier/startup",
                "https://i-viaplay-com.akamaized.net/viaplay-prod/614/492/StartUp3Exc_Packshot.jpg", "Viaplay.jpg");
        MovieInfo m4 = new MovieInfo("Gone with", "https://viaplay.se/store/borta-med-vinden-1939",
                "https://i-viaplay-com.akamaized.net/viaplay-prod/431/848/S48593_packshot_GE-1452847226793.jpg", "Viaplay.jpg");
        MovieInfo m5 = new MovieInfo("gone with", "https://viaplay.se/store/borta-med-vinden-1939",
                "https://i-viaplay-com.akamaized.net/viaplay-prod/431/848/S48593_packshot_GE-1452847226793.jpg", "Viaplay.jpg");
        MovieInfo m6 = new MovieInfo("borta med", "https://viaplay.se/store/borta-med-vinden-1939",
                "https://i-viaplay-com.akamaized.net/viaplay-prod/431/848/S48593_packshot_GE-1452847226793.jpg", "Viaplay.jpg");

        movies.add(m1);
        movies.add(m2);
        movies.add(m3);
   //     movies.add(m4);
   //     movies.add(m5);
        movies.add(m6);

        ViaplayService viaplayService = new ViaplayService();

        for ( MovieInfo movie : movies) {
            System.out.println("Searching for: " + movie.getTitle());
            MovieInfo result = viaplayService.search(movie.getTitle());

            assertThat(result, hasProperty("title", equalToIgnoringCase(movie.getTitle())));
            assertThat(result, Matchers.hasProperty("url", equalToIgnoringCase(movie.getUrl())));
            assertThat(result, hasProperty("imagePath", equalToIgnoringCase(movie.getImagePath())));
            assertThat(result, hasProperty("source", equalToIgnoringCase(movie.getSource())));




        }
    }
}
