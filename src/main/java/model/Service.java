package model;

import parsers.MovieInfo;

public interface Service {

    MovieInfo search(String title);

    void login();

     Boolean hasLogin();

     Boolean hasCookies();

     void playMovie(MovieInfo movie);

     UserAccount getAccount();

}
