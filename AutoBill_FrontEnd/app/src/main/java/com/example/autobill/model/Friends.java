package com.example.autobill.model;

public class Friends {
    private String groupId;
    private int[] userList;

    public Friends(String groupId,int[] userList){
        this.groupId = groupId;
        this.userList = userList;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public int[] getUserList() {
        return userList;
    }

    public void setUserList(int[] userList) {
        this.userList = userList;
    }
}
