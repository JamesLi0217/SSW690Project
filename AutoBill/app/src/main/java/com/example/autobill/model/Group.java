package com.example.autobill.model;


public class Group {
    private int groupId;
    private String groupName = "New Group";
    private float totalAmount = 0;
    private int checkStateId = -1;
    private int[] billsList;
    private int[] usersList;

    public Group(int[] usersList, String groupName, int groupId) {
        this.groupId = groupId;
        this.groupName = groupName;
        this.usersList = usersList;

    }

    public Group(String groupName,int [] usersList){
        this.groupName = groupName;
        this.usersList = usersList;
    }

    public Group(int groupId, String groupName) {
        this.groupId = groupId;
        this.groupName = groupName;
    }

    public Group(int groupId, String groupName, float totalAmount, int checkStateId, int[] billsList, int[] usersList) {
        this.groupId = groupId;
        this.groupName = groupName;
        this.totalAmount = totalAmount;
        this.checkStateId = checkStateId;
        this.billsList = billsList;
        this.usersList = usersList;
    }

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public float getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(float totalAmount) {
        this.totalAmount = totalAmount;
    }

    public int getCheckStateId() {
        return checkStateId;
    }

    public void setCheckStateId(int checkStateId) {
        this.checkStateId = checkStateId;
    }

    public int[] getBillsList() {
        return billsList;
    }

    public void setBillsList(int[] billsList) {
        this.billsList = billsList;
    }

    public int[] getUsersList() {
        return usersList;
    }

    public void setUsersList(int[] usersList) {
        this.usersList = usersList;
    }

}
