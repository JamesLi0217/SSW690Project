/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.bill.model;

/**
 *
 * @author sean
 */
public class Bill {
    private int billId;
    private float amount;
    private String description;
    private String receiptImg;

    public Bill(int billId, float amount, String description, String receiptImg) {
        this.billId = billId;
        this.amount = amount;
        this.description = description;
        this.receiptImg = receiptImg;
    }

    public int getBillId() {
        return billId;
    }

    public void setBillId(int billId) {
        this.billId = billId;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
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
    
    
    
}
