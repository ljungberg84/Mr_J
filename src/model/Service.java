package model;

import parsers.MovieInfo;

import java.util.List;

public interface Service {

    MovieInfo search(String title);

    void login();

    //saves hit from new thread in list
    default void SearchHandler(String title, List<MovieInfo> hits){
        hits.add(search(title));
    }
}
