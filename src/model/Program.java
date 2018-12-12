package model;

import parsers.MovieInfo;
import parsers.Parsable;

import java.util.*;

public class Program {

    Scanner sc = new Scanner(System.in);

    //List<Parsable> services;
    private Map<String, Parsable> services;
    private static List<MovieInfo> hits;

    public Program() {
        //this.services = new ArrayList<Parsable>();
        this.services = new HashMap<>();
        hits = new ArrayList<>();
    }

    public void start(String searchText){
        int hitCount;

        hits.clear();
        hitCount = 0;
//            System.out.println("Enter movie title to search for:");
//            String movieTitle = sc.nextLine();
//            if (movieTitle.equalsIgnoreCase("quit"))
//                System.exit(0);
        for (Parsable service : services.values()) {
            service.parse(searchText);
        }
        //main thread sleeps to make sure results populate list before printout
        //here we could use listener on 'List<MovieInfo>hits' to update javafx element without having to use sleep
        try {
            Thread.sleep(10000);
        }catch(InterruptedException e){

        }
        for (MovieInfo movie: hits) {
            if(movie != null){
                System.out.println(movie.getTitle() +" : " + movie.getUrl() + " : " + movie.getSource());
                hitCount ++;
            }
        }
        if (hitCount == 0){
            System.out.println("Sorry, " + searchText + " was not found on any streaming platform");
        }

    }

    public void addService(String name, Parsable service){
        if(service != null && name != null){
            //services.add(service);
            services.put(name, service);
        }
    }

    public static void addHit(MovieInfo movieInfo){
        synchronized (hits){
            hits.add(movieInfo);
        }
    }
}
