//package test.java;
import model.Service;
import model.UserAccount;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import parsers.MovieInfo;
import parsers.ServiceHandler;
import parsers.ViaplayService;

import java.util.ArrayList;
import java.util.Scanner;

import static org.hamcrest.Matchers.equalToIgnoringCase;
import static org.hamcrest.Matchers.hasProperty;
import static org.junit.Assert.*;


public class ViaplayServiceTest {

    public static void main(String[] args) {

        //This testing test methods from ServiceHandler class witch is abstract therefore
        //testing is made from ViaplayServiceTest and ViaplayService is tested from here as well.
        //Methods from ServiceHandler that are tested here include playMovie, hasLogin, hasCookies, getCookies,
        //and setCookies.



        Scanner sc = new Scanner(System.in);

        String userName;
        String password;

        System.out.println("Enter username for viaplay!");
  //      userName = sc.nextLine();
        System.out.println("Enter password for viaplay");
    //    password = sc.nextLine();

   //     UserAccount myaccount = new UserAccount(userName, password);

        ViaplayService viaplayService = new ViaplayService();
   //     viaplayService.setAccount(myaccount);


      //  startLogin(viaplayService);

        // Creating testdata
        ArrayList<MovieInfo> movies = new ArrayList<>();


        MovieInfo m1 = new MovieInfo("Girl", "https://viaplay.se/film/girls-trip-2017",
                "https://i-viaplay-com.akamaized.net/viaplay-prod/a4fa339/1515769757-bcde18aeae8c622419fda9eb58a87cb4a21bae3f.jpg", "Viaplay.jpg");
        MovieInfo m2 = new MovieInfo("girl", "https://viaplay.se/film/girls-trip-2017",
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
//        movies.add(m2);
//        movies.add(m3);
//        movies.add(m4);
//        movies.add(m5);
//        movies.add(m6);
        viaplayService.playMovie(m1);


        for (MovieInfo movie : movies) {
            System.out.println("Searching for: " + movie.getTitle());
            MovieInfo result = viaplayService.search(movie.getTitle());
            if (result != null) {
                assertThat(result, hasProperty("title", equalToIgnoringCase(movie.getTitle())));
                assertThat(result, Matchers.hasProperty("url", equalToIgnoringCase(movie.getUrl())));
                assertThat(result, hasProperty("imagePath", equalToIgnoringCase(movie.getImagePath())));
                assertThat(result, hasProperty("source", equalToIgnoringCase(movie.getSource())));
            } else {
                System.out.println(movie.getTitle() + "Return null");
            }
        }
    }

    @Test
    public static void startLogin(ServiceHandler service){
        System.out.println("startLogin()");
        System.out.println(service.getCookieHandler());

            if(service.hasLogin()){
                System.out.println("login found");
                if(false){//service.hasCookies()){
                    System.out.println("cookies found ");
                }
                else{
                    System.out.println("no cookies but login");
                    Thread thread = new Thread(service::login);
                    thread.start();
                }
            }else{
                System.out.println("no login or cookies, cant log in");
            }
        }
    }


