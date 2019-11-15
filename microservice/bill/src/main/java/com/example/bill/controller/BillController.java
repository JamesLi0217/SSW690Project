/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.bill.controller;

import com.example.bill.model.Bill;
import com.example.bill.service.BillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author sean
 */
@RequestMapping("bill")
@RestController
public class BillController {
    
    private final BillService billService;

    @Autowired
    public BillController(BillService billService) {
        this.billService = billService;
    }
    
    @GetMapping("general/{billId}")
    public Bill getBillGeneral(@PathVariable("billId") int billId) {
        return billService.getBillGeneral(billId);
    }
    
    @GetMapping("{billId}")
    public Bill getBill(@PathVariable("billId") int billId) {
        return billService.getBill(billId);
    }
    
    @PostMapping()
    public void createBill(@RequestBody Bill bill) {
        billService.createBill(bill);
    }
    
    @GetMapping("add/{billId}")
    public int getTotalAddState(@PathVariable("billId") int billId) {
        return billService.getTotalAddState(billId);
    }
    
    @PostMapping("add/{billId}/{userId}")
    public int billAddComfirm(@PathVariable("billId") int billId,
                            @PathVariable("userId") int userId) {
        return billService.billAddComfirm(billId, userId);
    }
    
    @GetMapping("add/{billId}/{userId}")
    public int getUserAddComfirm(@PathVariable("billId") int billId,
                            @PathVariable("userId") int userId) {
        return billService.getUserAddComfirm(billId, userId);
    }
    
    @PostMapping("cancel/{billId}/{userId}") 
    public void cancelAddComfirm(@PathVariable("billId") int billId,
                            @PathVariable("userId") int userId) {
        billService.cancelAddComfirm(billId, userId);
    }
    
    @GetMapping("cancel/{billId}")
    public int getUserCancelAdd(@PathVariable("billId") int billId) {
        return billService.getUserCancelAdd(billId);
    }
}
