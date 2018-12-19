package model;

import fx.Controller;
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

    private Map<String, Service> services;
    private ObservableList<MovieInfo> hits;

    public ObservableList<MovieInfo> getHits() {
        return hits;
    }

    public synchronized void addHits(MovieInfo hit){
        if(hit != null){
            this.hits.add(hit);
        }
    }

    public Program() {
        services = new HashMap<>();
        hits = FXCollections.observableArrayList();
    }

    public void startLogin(){
        System.out.println("startLogin()");
        for (Service service : services.values()) {
            if(service.hasLogin()){
                System.out.println("login found");
                if(true){//service.hasCookies()){
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
    public void startSearch(String searchFrase){

        System.out.println("Starting search");

        for (Service service : services.values()) {
            System.out.println("Starting thread");
            Task task = new Task<MovieInfo>() {
                @Override
                protected MovieInfo call() throws Exception {
                    return service.search(searchFrase);
                }
            };
            task.setOnSucceeded(new EventHandler<WorkerStateEvent>() {
                @Override
                public void handle(WorkerStateEvent event) {
                    addHits(((Task<MovieInfo>) task).getValue());
                    System.out.println(hits.size());
                }
            });
            Thread thread = new Thread(task);
            thread.setDaemon(true);
            thread.start();
            //Platform.runLater(()-> Controller.getSearchButton().setDisable(false));
        }
    }

    public void addService(String name, ServiceHandler service){
        if(service != null && name != null){
            services.put(name, service);
        }
    }

    public Map<String, Service> getServices() {
        return services;
    }
}
