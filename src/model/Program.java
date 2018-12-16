package model;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.EventHandler;
import parsers.MovieInfo;
import parsers.ServiceHandler;

import java.util.*;

public class Program {

    Scanner sc = new Scanner(System.in);

    //List<Parsable> services;
    private static Map<String, ServiceHandler> services;
    private static  ObservableList<MovieInfo> hits;

    public static ObservableList<MovieInfo> getHits() {
        return hits;
    }

    public synchronized static void addHits(MovieInfo hit){
        Platform.runLater(()->hits.add(hit));

    }



    public Program() {
        //this.services = new ArrayList<Parsable>();
        this.services = new HashMap<>();
        hits = FXCollections.observableArrayList();
    }

    public void startLogin(){
        System.out.println("Starting login");
        for (ServiceHandler service : services.values()) {
            Thread thread = new Thread(service::login);
            thread.start();

        }

//            if(service.getAccount().getUserName() != null && service.getAccount().getPassword() != null) {
//                //System.out.println("Username and password found");
//
//                if (service.getCookieHandler().hasExpired()) {
//                    System.out.println("Logging in");
//                    Thread thread = new Thread(service::login);
//                    thread.start();
//                }
//            } else {
//                System.out.println("No username found");
//            }
//        }
    }
    public void startSearch(String searchFrase){

        System.out.println("Starting search");
        Platform.runLater(()->hits.clear());
        //hits.clear();

        for (ServiceHandler service : services.values()) {
            System.out.println("Starting thread");
            //Thread thread = new Thread(() -> service.searchHandler(searchFrase, hits));
            //thread.start();  // thread instantiation maybe here
            Task task = new Task<MovieInfo>() {
                @Override
                protected MovieInfo call() throws Exception {
                    return service.searchHandler(searchFrase);
                }
            };
            task.setOnSucceeded(new EventHandler<WorkerStateEvent>() {
                @Override
                public void handle(WorkerStateEvent event) {
                   hits.add((MovieInfo) task.getValue());
                }
            });
            Thread thread = new Thread(task);
            thread.setDaemon(true);
            thread.start();

            //hits.add();
        }
    }

    public void addService(String name, ServiceHandler service){
        if(service != null && name != null){
            //services.add(service);
            services.put(name, service);
        }
    }



//    public static void addHit(MovieInfo movieInfo){
//        synchronized (hits){
//            Platform.runLater(()->hits.add(movieInfo));
//            //hits.add(movieInfo);
//        }
//    }


}
