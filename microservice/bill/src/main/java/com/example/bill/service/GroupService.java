/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.bill.service;


import com.example.bill.dao.GroupDao;
import com.example.bill.model.Group;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 *
 * @author sean
 */
@Service
public class GroupService {
    
    private final GroupDao groupDao; 

    @Autowired
    public GroupService(@Qualifier("AWSGroup")GroupDao groupDao) {
        this.groupDao = groupDao;
    }
    
    public int createGroup(Group group) {
        return groupDao.createGroup(group); 
    }

    public String getGroupName(int groupId) {
        return groupDao.getGroupName(groupId);
    }

    public Group getGroup(int groupId) {
        return groupDao.getGroup(groupId);
    }

    public int addGroupMember(int groupId, int[] usersList) {
        return groupDao.addGroupMember(groupId, usersList);
    }

    public int checkoutComfirm(int groupId, int userId) {
        groupDao.checkoutComfirm(groupId, userId);
        return groupDao.getTotalCheckoutState(groupId);
    }

    public int getTotalCheckoutState(int groupId) {
        return groupDao.getTotalCheckoutState(groupId);
    }

    public int getUserCheckoutComfirm(int groupId, int userId) {
        return groupDao.getUserCheckoutComfirm(groupId, userId);
    }

    public Map<Integer, Map<Integer, Float>> checkout(int groupId) {
        Group group = groupDao.getGroup(groupId);
        groupDao.checkout(groupId);
        Map<Integer, Float> billList = groupDao.getTranList(group);
        return CalculateTransfer(billList, groupId);
    }

    public int cancelCheckoutComfirm(int groupId, int userId) {
        groupDao.cancelCheckoutComfirm(groupId, userId);
        return groupDao.getTotalCheckoutState(groupId);
    }

    public int getUserCancelCheckout(int groupId) {
        return groupDao.getUserCancelCheckout(groupId);
    }

    public float getIndivitualTotalBalance(int groupId, int userId) {
        return groupDao.getIndivitualTotalBalance(groupId, userId);
    }

    private Map<Integer, Map<Integer, Float>> CalculateTransfer(Map<Integer, Float> billList, int groupId) {
        Map<Integer, Float> posBill = new HashMap();
        Map<Integer, Float> negBill = new HashMap();
        Map<Integer, Map<Integer, Float>> res = new HashMap();
        for(int userId : billList.keySet()) {
            float amount = billList.get(userId);
            if(amount > 0)
                posBill.put(userId, amount);
            else if(amount < 0)
                negBill.put(userId, amount);
        }
        while(!posBill.isEmpty() && !negBill.isEmpty()) {
            int posId = Integer.parseInt(posBill.keySet().toArray()[0].toString());
            int negId = Integer.parseInt(negBill.keySet().toArray()[0].toString());
            float posVal = posBill.get(posId);
            float negVal = negBill.get(negId);
            float tranAmount;
            Map<Integer, Float> posTran = new HashMap();
            Map<Integer, Float> negTran = new HashMap();
            if(res.get(posId) != null)
                posTran = res.get(posId);
            if(res.get(negId) != null)
                negTran = res.get(negId);
            if(posVal == -negVal) {
                posTran.put(negId, posVal);
                negTran.put(posId, negVal);
                posBill.remove(posId);
                negBill.remove(negId);
                tranAmount = posVal;
            } else if(posVal > -negVal) {
                posTran.put(negId, -negVal);
                negTran.put(posId, negVal);
                posBill.replace(posId, posVal + negVal);
                negBill.remove(negId);
                tranAmount = -negVal;
            } else {
                posTran.put(negId, posVal);
                negTran.put(posId, -posVal);
                negBill.replace(negId, posVal + negVal);
                posBill.remove(posId);
                tranAmount = posVal;
            }
            groupDao.addTransfer(groupId, posId, negId, tranAmount);
            res.put(posId, posTran);
            res.put(negId, negTran);
        }
        groupDao.changeCheckoutCalculateState(groupId, 1);
        return res;
    }

    public Map<Integer, Float> getTrasfer(int groupId, int userId) {
        return groupDao.getTrasfer(groupId, userId);
    }
    
}
