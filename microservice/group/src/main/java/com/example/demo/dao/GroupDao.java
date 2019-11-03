/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.dao;

import com.example.demo.model.Group;

/**
 *
 * @author sean
 */
public interface GroupDao {
    public abstract boolean createGroup(Group group);
    
    public abstract String getGroupName(int group_id);
    public abstract Group getGroup(int group_id);
    
    /* checkout function */
    public abstract int checkoutComfirm(int groupId, int userId, int state);
    
    /* Group delete function */
    public abstract void startToDeleteGroup(int group_id, int user_id);
    public abstract void comfirmDeleteGroup(int group_id, int user_id);
    public abstract void cancelDeleteGroup(int group_id, int user_id);
    public abstract void deleteGroup(int group_id);
    
}
