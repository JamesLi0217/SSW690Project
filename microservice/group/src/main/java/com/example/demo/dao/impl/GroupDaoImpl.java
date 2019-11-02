/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.dao.impl;

import com.example.demo.dao.GroupDao;
import com.example.demo.model.Group;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 *
 * @author sean
 */
@Repository
public class GroupDaoImpl implements GroupDao{

    @Autowired 
	private JdbcTemplate jdbcTemplate;
    
    @Override
    public void createGroup(Group group) {
        String CREATE_GROUP_SQL = "INSERT INTO groups(group_name) VALUES (?)";
	int update = jdbcTemplate.update(CREATE_GROUP_SQL, group.getGroup_name());
	if(update ==1) {
            System.out.println("Group is created..");
	}
        List<Integer> userIdList = group.getUsers_list();
        for (Integer userId : userIdList) {
            String ADD_USER_TO_GROUP_SQL = "INSERT INTO group_user_list(user_id, group_id) VALUES(?,?,?,?)";
            int tmp = jdbcTemplate.update(ADD_USER_TO_GROUP_SQL, userId, group.getGroup_id());
            if(update ==1) {
                System.out.println(userId.toString() + " is added to group");
            }
        }
                
    }

    @Override
    public Group getGroup(int group_id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void startToCheckout(int group_id, int user_id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void comfirmCheckout(int group_id, int user_id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void cancelCheckout(int group_id, int user_id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void checkout(int group_id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void startToDeleteGroup(int group_id, int user_id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void comfirmDeleteGroup(int group_id, int user_id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void cancelDeleteGroup(int group_id, int user_id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void deleteGroup(int group_id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
