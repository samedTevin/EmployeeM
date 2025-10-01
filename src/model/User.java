package model;



public class User {

    private int id;
    private String username;
    private String password;
    private String dailyNotes;
    private String createdAt;
    private String updatedAt;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public User(){

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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

    public String getDailyNotes() {
        return dailyNotes;
    }

    public void setDailyNotes(String dailyNotes) {
        this.dailyNotes = dailyNotes;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String  getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }


}
