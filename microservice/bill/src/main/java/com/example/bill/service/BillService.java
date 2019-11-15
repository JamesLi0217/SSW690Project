/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.bill.service;

import com.example.bill.dao.BillDao;
import com.example.bill.model.Bill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 *
 * @author sean
 */
@Service
public class BillService {
    
    private final BillDao billDao;

    @Autowired
    public BillService(@Qualifier("AWSBill") BillDao billDao) {
        this.billDao = billDao;
    }

    public Bill getBillGeneral(int billId) {
        return billDao.getBillGeneral(billId);
    }

    public int createBill(Bill bill) {
        return billDao.createBill(bill);
    }

    public Bill getBill(int billId) {
        return billDao.getBill(billId);
    }

    public int getTotalAddState(int billId) {
        return billDao.getTotalAddState(billId);
    }

    public int billAddComfirm(int billId, int userId) {
        billDao.billAddComfirm(billId, userId);
        return billDao.getTotalAddState(billId);
    }

    public int getUserAddComfirm(int billId, int userId) {
        return billDao.getUserAddComfirm(billId, userId);
    }

    public int cancelAddComfirm(int billId, int userId) {
        return billDao.cancelAddComfirm(billId, userId);
    }

    public int getUserCancelAdd(int billId) {
        return billDao.getUserCancelAdd(billId);
    }
}
