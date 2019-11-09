/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.bill.dao.impl;

import com.example.bill.dao.GroupDao;
import com.example.bill.model.Group;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.*;
import org.springframework.stereotype.Repository;

/**
 *
 * @author sean
 */
@Repository("AWSGroup")
public class GroupDaoImpl implements GroupDao {
    
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public int createGroup(Group group) {
        String CREATE_GROUP_SQL = "INSERT INTO autobill_db.groups(group_name) VALUES (?)";
	int update = jdbcTemplate.update(CREATE_GROUP_SQL, group.getGroupName());
	if(update != 1) 
            return 0;
        String GET_LAST_AUTO_INCREMENT_ID = "SELECT group_id AS LastID FROM autobill_db.groups WHERE group_id = @@Identity;";
        int groupId = jdbcTemplate.queryForObject(GET_LAST_AUTO_INCREMENT_ID, Integer.class);
        group.setGroupId(groupId);
        int[] userIdList = group.getUsersList();
        String ADD_USER_TO_GROUP_SQL = "INSERT INTO group_user_list(user_id, group_id) VALUES(?,?)";
        for (Integer userId : userIdList) {
            update = jdbcTemplate.update(ADD_USER_TO_GROUP_SQL, userId, groupId);
            if(update !=1 ) 
                return 0;
        }
        return 1;
    }

    @Override
    public String getGroupName(int groupId) {
        String GET_GROUP_NAME = "SELECT group_name FROM autobill_db.groups WHERE group_id = ?";
        return  jdbcTemplate.queryForObject(GET_GROUP_NAME, String.class, groupId);
    }

    @Override
    public Group getGroup(int groupId) {
        // get name, amount, checkStateId
        String GET_GROUP_PROFILE = "SELECT * FROM autobill_db.groups WHERE group_id = ?";
        List<Map<String, Object>> tmp = jdbcTemplate.queryForList(GET_GROUP_PROFILE, groupId);
        Map<String, Object> result = tmp.get(0);
        String groupName = result.get("group_name").toString();
        float totalAmount = Float.parseFloat(result.get("total_amount").toString());
        int checkStateId = Integer.parseInt(result.get("check_state_id").toString());
        // get bills
        String GET_BILLS_LIST = "SELECT bill_id FROM autobill_db.group_bill_list WHERE group_id = ?";
        tmp = jdbcTemplate.queryForList(GET_BILLS_LIST, groupId);
        int[] billsList = new int[tmp.size()];
        int i = 0;
        for (Map<String, Object> row : tmp) {
            billsList[i] = Integer.parseInt(row.get("bill_id").toString());
            i ++;
        }
        // get users
        String GET_USERS_LIST = "SELECT user_id FROM autobill_db.group_user_list WHERE group_id = ?";
        tmp = jdbcTemplate.queryForList(GET_USERS_LIST, groupId);
        int[] usersList = new int[tmp.size()];
        i = 0;
        for (Map<String, Object> row : tmp) {
            usersList[i] = Integer.parseInt(row.get("user_id").toString());
            i ++;
        }
        return new Group(groupId, groupName, totalAmount, checkStateId, billsList, usersList);
    }
    
    @Override
    public int addGroupMember(int groupId, int[] usersList) {
        String ADD_USER_TO_GROUP_SQL = "INSERT INTO group_user_list(user_id, group_id) VALUES(?,?)";
        for (Integer userId : usersList) {
            int update = jdbcTemplate.update(ADD_USER_TO_GROUP_SQL, userId, groupId);
            if(update !=1 ) 
                return 0;
        }
        return 1;
    }

    @Override
    public int checkoutComfirm(int groupId, int userId) {
        String CHECKOUT_COMFIRM_SQL = "UPDATE autobill_db.group_user_list SET check_state_id = 1 WHERE user_id = ? and group_id = ?";
	int update = jdbcTemplate.update(CHECKOUT_COMFIRM_SQL, userId, groupId);
        return update;
    }

    @Override
    public int getTotalCheckoutState(int groupId) {
        String GET_GROUP_TOTAL_CHECKOUT_COMFIRM_SQL = "SELECT check_state_id FROM autobill_db.groups WHERE group_id = ?";
        if(jdbcTemplate.queryForObject(GET_GROUP_TOTAL_CHECKOUT_COMFIRM_SQL, Integer.class, groupId) == 2) {
            return 2;
        }
        // check have everyone has comfirm
        String GET_USERS_LIST = "SELECT check_state_id FROM autobill_db.group_user_list WHERE group_id = ?";
        List<Map<String, Object>> result = jdbcTemplate.queryForList(GET_USERS_LIST, groupId);
        int finish = 1;
        for (Map<String, Object> row : result) {
            if(Integer.parseInt(row.get("check_state_id").toString()) == 0) {
                finish = 0;
                break;
            }
        }
        // if everyone comfirm, change the checkout state and everyone's comfirm state
        // !!!!!!!!!!!!!!!
        // !!!!!! change all the bills' state which have been checked out
        if(finish == 1) {
            String CHECKOUT_COMFIRM_SQL = "UPDATE autobill_db.group_user_list SET check_state_id = 2 WHERE group_id = ?";
            int update = jdbcTemplate.update(CHECKOUT_COMFIRM_SQL, groupId);
            String TOTAL_CHECKOUT_COMFIRM_SQL = "UPDATE autobill_db.groups SET check_state_id = 2 WHERE group_id = ?";
            update = jdbcTemplate.update(TOTAL_CHECKOUT_COMFIRM_SQL, groupId);
        }
        return  jdbcTemplate.queryForObject(GET_GROUP_TOTAL_CHECKOUT_COMFIRM_SQL, Integer.class, groupId);
    }

    @Override
    public int getUserCheckoutComfirm(int groupId, int userId) {
        String GET_CHECKOUT_COMFIRM_SQL = "SELECT check_state_id FROM autobill_db.group_user_list WHERE group_id = ? and user_id = ?";
        return jdbcTemplate.queryForObject(GET_CHECKOUT_COMFIRM_SQL, Integer.class, groupId, userId);
    }

    @Override
    public void checkout(int groupId) {
        String CHECKOUT_COMFIRM_SQL = "UPDATE autobill_db.group_user_list SET check_state_id = 0 WHERE group_id = ?";
        int update = jdbcTemplate.update(CHECKOUT_COMFIRM_SQL, groupId);
        String TOTAL_CHECKOUT_COMFIRM_SQL = "UPDATE autobill_db.groups SET check_state_id = 0 WHERE group_id = ?";
        update = jdbcTemplate.update(TOTAL_CHECKOUT_COMFIRM_SQL, groupId);
    }

    @Override
    public void cancelCheckoutComfirm(int groupId, int userId) {
        String CHECKOUT_COMFIRM_SQL = "UPDATE autobill_db.group_user_list SET check_state_id = 2 WHERE group_id = ?";
        int update = jdbcTemplate.update(CHECKOUT_COMFIRM_SQL, groupId);
        String CHECKOUT_CANCEL_SQL = "UPDATE autobill_db.group_user_list SET check_state_id = -1 WHERE group_id = ? and user_id = ?";
        update = jdbcTemplate.update(CHECKOUT_CANCEL_SQL, groupId, userId);
        String TOTAL_CHECKOUT_COMFIRM_SQL = "UPDATE autobill_db.groups SET check_state_id = 2 WHERE group_id = ?";
        update = jdbcTemplate.update(TOTAL_CHECKOUT_COMFIRM_SQL, groupId);
    }

    @Override
    public int getUserCancelCheckout(int groupId) {
        String GET_CANCEL_USER_SQL = "SELECT user_id FROM autobill_db.group_user_list WHERE group_id = ? and check_state_id = -1";
        return jdbcTemplate.queryForObject(GET_CANCEL_USER_SQL, Integer.class, groupId);
        
    }
    
    
}
