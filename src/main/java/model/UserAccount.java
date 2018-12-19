package model;

public class UserAccount {

    private String userName;
    private String password;

    public UserAccount(){
        this("", "");
    }

    public UserAccount(String userName, String password){
        this.setUserName(userName);
        this.setPassword(password);
    }

    public boolean hasLogin(){
        return (!userName.isEmpty() && !password.isEmpty());
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        if (userName != null){
            this.userName = userName;
        }
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        if(password != null){
            this.password = password;
        }
    }
}
