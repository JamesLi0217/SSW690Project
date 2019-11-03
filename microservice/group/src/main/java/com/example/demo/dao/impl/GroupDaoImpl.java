/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.dao.impl;

import com.example.demo.dao.GroupDao;
import com.example.demo.model.Group;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
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
    public boolean createGroup(Group group) {
        String CREATE_GROUP_SQL = "INSERT INTO autobill_db.groups(group_name) VALUES (?)";
	int update = jdbcTemplate.update(CREATE_GROUP_SQL, group.getGroup_name());
	if(update != 1) 
            return false;
        String GET_LAST_AUTO_INCREMENT_ID = "SELECT group_id AS LastID FROM autobill_db.groups WHERE group_id = @@Identity;";
        int group_id = jdbcTemplate.queryForObject(GET_LAST_AUTO_INCREMENT_ID, Integer.class);
        group.setGroup_id(group_id);
        List<Integer> userIdList = group.getUsers_list();
        for (Integer userId : userIdList) {
            String ADD_USER_TO_GROUP_SQL = "INSERT INTO group_user_list(user_id, group_id) VALUES(?,?)";
            update = jdbcTemplate.update(ADD_USER_TO_GROUP_SQL, userId, group_id);
            if(update !=1 ) 
                return false;
        }
        return true;       
    }
    
    @Override
    public String getGroupName(int group_id){
        String GET_GROUP_NAME = "SELECT group_name FROM autobill_db.groups WHERE group_id = ?";
        return  jdbcTemplate.queryForObject(GET_GROUP_NAME, String.class, group_id);
    }

    @Override
    public Group getGroup(int group_id) {
        String GET_GROUP_PROFILE = "SELECT * FROM autobill_db.groups WHERE group_id = ?";
        List<Map<String, Object>> tmp = jdbcTemplate.queryForList(GET_GROUP_PROFILE, group_id);
        Map<String, Object> result = tmp.get(0);
        String group_name = result.get("group_name").toString();
        float total_amount = Float.parseFloat(result.get("total_amount").toString());
        int check_state_id = Integer.parseInt(result.get("check_state_id").toString());
        List<Integer> bills_list = new ArrayList();
        String GET_BILLS_LIST = "SELECT bill_id FROM autobill_db.group_bill_list WHERE group_id = ?";
        tmp = jdbcTemplate.queryForList(GET_BILLS_LIST, group_id);
        List<Integer> users_list = new ArrayList();
        String GET_USERS_LIST = "SELECT user_id FROM autobill_db.group_user_list WHERE group_id = ?";
        tmp = jdbcTemplate.queryForList(GET_USERS_LIST, group_id);
        for (Map<String, Object> row : tmp) {
            users_list.add(Integer.parseInt(row.get("user_id").toString()));
        }
        return new Group(group_id, group_name, total_amount, check_state_id, bills_list, users_list);
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

    @Override
    public int checkoutComfirm(int groupId, int userId, int state) {
        String CHECKOUT_COMFIRM_SQL = "UPDATE autobill_db.group_user_list SET check_state_id = ? WHERE user_id = ? and group_id = ?";
	int update = jdbcTemplate.update(CHECKOUT_COMFIRM_SQL, state, userId, groupId);
        return update;
    }
    
}
