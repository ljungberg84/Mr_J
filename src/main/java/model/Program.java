package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import parsers.MovieInfo;

import java.util.*;

public class Program {

    private static Program instance = new Program();
    private Map<String, Service> services;
    private ObservableList<MovieInfo> hits;

    private Program() {
        services = new HashMap<>();
        hits = FXCollections.observableArrayList();
        //instance = new Program();
    }

    public boolean startLogin(){
        System.out.println("startLogin()");
        boolean login = false;
        for (Service service : services.values()) {
            if(service.hasLogin()){
                if(service.hasCookies()){
                    System.out.println("login found");
                    System.out.println("cookies found ");
                }
                else{
                    login = true;
                    System.out.println("no cookies but login");
                    Thread thread = new Thread(service::login);
                    thread.start();
                }
            }else{
                System.out.println("no login or cookies, cant log in");
            }
        }
        return login;
    }


    public void startSearch(String searchFrase){

        if (searchFrase==null || searchFrase.equals("")) {
            System.out.println("Error: invalid input");
            } else {
        System.out.println("test");

        for (Service service : services.values()) {
            System.out.println("Starting thread");
            Task task = new Task<MovieInfo>() {
                @Override
                protected MovieInfo call() throws Exception {
                    return service.search(searchFrase);
                }
            };

            task.setOnSucceeded((event)->{
                if(task.getValue() != null){
                    addHits(((Task<MovieInfo>) task).getValue());
                }
            });

            Thread thread = new Thread(task);
            thread.setDaemon(true);
            thread.start();
            }
        }
    }

    public void addService(String name, Service service){
        if(service != null && name != null){
            services.put(name, service);
        }
    }

    public Map<String, Service> getServices() {
        return services;
    }

    public ObservableList<MovieInfo> getHits() {
        return hits;
    }

    public synchronized void addHits(MovieInfo hit){
        if(hit != null){
            this.hits.add(hit);
        }
    }

    public static Program getInstance(){
        return instance;
    }
}
