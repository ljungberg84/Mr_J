package model;

import parsers.MovieInfo;

public interface Searcher {

    public MovieInfo search(String movieTitle);
}
