/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.bill.dao;

import com.example.bill.model.Bill;

/**
 *
 * @author sean
 */
public interface BillDao {

    public Bill getBillGeneral(int billId);

    public int createBill(Bill bill);

    public Bill getBill(int billId);

    public int getTotalAddState(int billId);

    public int billAddComfirm(int billId, int userId);

    public int getUserAddComfirm(int billId, int userId);

    public int cancelAddComfirm(int billId, int userId);

    public int getUserCancelAdd(int billId);
    
}
