/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.bill.service;


import com.example.bill.dao.GroupDao;
import com.example.bill.model.Group;
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

    public String[] checkout(int groupId) {
        Group group = groupDao.getGroup(groupId);
        groupDao.checkout(groupId);
        return null;
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
    
}
