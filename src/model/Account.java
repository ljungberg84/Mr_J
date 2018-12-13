package model;

import parsers.MovieInfo;
import parsers.ServiceParser;

import java.util.HashMap;

public abstract class Account extends ServiceParser {

    private String userName;
    private String passWord;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public Account(String rootUrl, String userName, String passWord) {
        super(rootUrl);
        this.userName = userName;
        this.passWord = passWord;

    }

    public abstract void Login();

}
