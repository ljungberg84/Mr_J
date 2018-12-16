package model;

public class UserAccount {

    private String userName = "";
    private String password = "";

    public boolean hasLogin(){
        System.out.println("has login");
        return (!userName.isEmpty() && !password.isEmpty());
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
