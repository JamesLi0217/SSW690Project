package com.example.autobill.model;

public class User {

    public int user_id;
    public String userName;
    public int userStartDate;
    public String userPassword;
    public String userEmail;
    public int[] groupList;


    public User(){}
    public User(String userName, int userStartDate, String userPassword, String userEmail) {
        this.userName = userName;
        this.userStartDate = userStartDate;
        this.userPassword = userPassword;
        this.userEmail = userEmail;
    }


    public User(String userEmail, String userPassword) {
        this.userPassword = userPassword;
        this.userEmail = userEmail;
    }


    public void setUserName(String user_name) {
        this.userName = user_name;
    }


    public String getPassword() {
        return userPassword;
    }

    public void setPassword(String user_password) {
        this.userPassword = user_password;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String user_email) {
        this.userEmail = user_email;
    }


}