package cn.com.egova.webservice2.bean;

public class User {
    private String username;
    private String password;
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public User(String username, String password) {
        super();
        this.username = username;
        this.password = password;
    }
    public User() {
        super();
    }
}