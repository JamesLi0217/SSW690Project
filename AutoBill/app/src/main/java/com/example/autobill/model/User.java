package com.example.autobill.model;

public class User{
    public int user_id;
    public String user_name;
    public int user_start_date;
    public String user_password;
    public String user_email;
    public int[] groupList;

    public User() {
        this.user_id = 3;
        this.user_name= "jack";
        groupList = new int[2];
        groupList[0] = 3;
        groupList[1] = 6;

    }

    public int[] getGroupList() {
        return this.groupList;
    }

    public int getID() {
        return user_id;
    }

    public void setId(int user_id) {
        this.user_id = user_id;
    }

    public String getUserName() {
        return user_name;
    }

    public void setUserName(String user_name) {
        this.user_name = user_name;
    }

    public String getPassword() {
        return user_password;
    }

    public void setPassword(String user_password) {
        this.user_password = user_password;
    }

    public String getUserEmail() {
        return user_password;
    }

    public void setUserEmail(String user_email) {
        this.user_email = user_email;
    }


}