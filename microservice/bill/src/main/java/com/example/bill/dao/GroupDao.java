/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.bill.dao;

import com.example.bill.model.Group;
import java.util.Map;


/**
 *
 * @author sean
 */
public interface GroupDao {
    public abstract int createGroup(Group group);
    
    public abstract String getGroupName(int groupId);
    public abstract Group getGroup(int groupId);
    
    /* checkout function */
    public abstract int checkoutComfirm(int groupId, int userId);
    public abstract int getTotalCheckoutState(int groupId);
    
    /* Group delete function */

    public int addGroupMember(int groupId, int[] usersList);

    public int getUserCheckoutComfirm(int groupId, int userId);

    public void checkout(int groupId);

    public void cancelCheckoutComfirm(int groupId, int userId);

    public int getUserCancelCheckout(int groupId);

    public float getIndivitualTotalBalance(int groupId, int userId);

    public Map<Integer, Float> getTranList(Group group);

    public int addTransfer(int groupId, int posId, int negId, float tranAmount);

    public int changeCheckoutCalculateState(int groupId, int i);

    public Map<Integer, Float> getTrasfer(int groupId, int userId);
    
    public int deleteTransfer(int groupId);
}
