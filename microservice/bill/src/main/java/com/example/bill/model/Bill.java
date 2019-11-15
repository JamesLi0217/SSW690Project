/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.bill.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 *
 * @author sean
 */
public class Bill {
    private int billId;
    private int groupId;
    private int date;
    private float amount;
    private String billName;
    private String description;
    private String receiptImg;
    private int[] usersList;
    private int addState;
    private int payerId;

    public Bill(int billId, String billName, float billAmount, int billDate, int state) {
        this.billId = billId;
        this.billName = billName;
        this.amount = billAmount;
        this.date = billDate;
        this.addState = state;
        
    }
    
    public Bill(@JsonProperty("groupId") int groupId,
            @JsonProperty("date") int date, 
            @JsonProperty("amount") float amount, 
            @JsonProperty("billName") String billName, 
            @JsonProperty("description") String description, 
            @JsonProperty("receiptImg") String receiptImg, 
            @JsonProperty("usersList") int[] usersList,
            @JsonProperty("payerId") int payerId) {
        this.groupId = groupId;
        this.date = date;
        this.amount = amount;
        this.billName = billName;
        this.description = description;
        this.receiptImg = receiptImg;
        this.usersList = usersList;
        this.payerId = payerId;
    }

    public Bill(int billId, String billName, float billAmount, int billDate, int state, String billReceipt, String billDesc, int[] userList, int payerId) {
        this.billId = billId;
        this.billName = billName;
        this.date = billDate;
        this.amount = billAmount;
        this.description = billDesc;
        this.receiptImg = billReceipt;
        this.usersList = userList;
        this.addState = state;
        this.payerId = payerId;
    }

    public int getBillId() {
        return billId;
    }

    public void setBillId(int billId) {
        this.billId = billId;
    }

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public String getBillName() {
        return billName;
    }

    public void setBillName(String billName) {
        this.billName = billName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getReceiptImg() {
        return receiptImg;
    }

    public void setReceiptImg(String receiptImg) {
        this.receiptImg = receiptImg;
    }

    public int[] getUsersList() {
        return usersList;
    }

    public void setUsersList(int[] usersList) {
        this.usersList = usersList;
    }

    public int getDate() {
        return date;
    }

    public void setDate(int date) {
        this.date = date;
    }

    public int getAddState() {
        return addState;
    }

    public void setAddState(int addState) {
        this.addState = addState;
    }

    public int getPayerId() {
        return payerId;
    }

    public void setPayerId(int payerId) {
        this.payerId = payerId;
    }
    
    
}
