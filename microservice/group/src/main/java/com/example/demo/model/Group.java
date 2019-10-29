/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.model;

import java.util.List;

/**
 *
 * @author sean
 */
public class Group {
    private final int group_id;
    private String group_name = "New Group";
    private float total_amount = 0;
    private int check_state_id = -1;
    private List<Integer> bills_list;
    private List<Integer> users_list;

    public Group(int group_id, String group_name) {
        this.group_id = group_id;
        this.group_name = group_name;
    }

    public int getGroup_id() {
        return group_id;
    }

    public String getGroup_name() {
        return group_name;
    }

    public void setGroup_name(String group_name) {
        this.group_name = group_name;
    }

    public float getTotal_amount() {
        return total_amount;
    }

    public void setTotal_amount(float total_amount) {
        this.total_amount = total_amount;
    }

    public int getCheckState_id() {
        return check_state_id;
    }

    public void setCheckState_id(int state_id) {
        this.check_state_id = state_id;
    }

    public List<Integer> getBills_list() {
        return bills_list;
    }

    public void addBillToBills_list(int bill_id) {
        this.bills_list.add(bill_id);
    }

    public void removeBillFromBills_list(int bill_id) {
        this.bills_list.remove(bill_id);
    }
    
    public List<Integer> getUsers_list() {
        return users_list;
    }

    public void addUserToUsers_list(int user_id) {
        this.users_list.add(user_id);
    }
    
    public void removeUserToUsers_list(int user_id) {
        this.users_list.remove(user_id);
    }
}
