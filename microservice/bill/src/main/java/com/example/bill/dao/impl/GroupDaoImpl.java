/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.bill.dao.impl;

import com.example.bill.dao.GroupDao;
import com.example.bill.model.Group;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
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
        // if everyone comfirm, change the checkout state and everyone's comfirm state, all the bills have been checked out state change to 3
        // !!!!!!!!!!!!!!!
        // !!!!!! change all the bills' state which have been checked out
        if(finish == 1) {
            String CHECKOUT_COMFIRM_SQL = "UPDATE autobill_db.group_user_list SET check_state_id = 2 WHERE group_id = ?";
            int update = jdbcTemplate.update(CHECKOUT_COMFIRM_SQL, groupId);
            String TOTAL_CHECKOUT_COMFIRM_SQL = "UPDATE autobill_db.groups SET check_state_id = 2 WHERE group_id = ?";
            update = jdbcTemplate.update(TOTAL_CHECKOUT_COMFIRM_SQL, groupId);
            String BILL_STATE_CHANGE_SQL = "UPDATE autobill_db.group_bill_list SET total_add_state_id = 3 WHERE group_id = ? AND total_add_state_id = 2";
            update = jdbcTemplate.update(BILL_STATE_CHANGE_SQL, groupId);
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
        String BILL_STATE_CHANGE_SQL = "UPDATE autobill_db.group_bill_list SET total_add_state_id = 2 WHERE group_id = ? AND total_add_state_id = 1";
        update = jdbcTemplate.update(BILL_STATE_CHANGE_SQL, groupId);
    }
    
    

    @Override
    public void cancelCheckoutComfirm(int groupId, int userId) {
        String CHECKOUT_COMFIRM_SQL = "UPDATE autobill_db.group_user_list SET check_state_id = 2 WHERE group_id = ?";
        int update = jdbcTemplate.update(CHECKOUT_COMFIRM_SQL, groupId);
        String CHECKOUT_CANCEL_SQL = "UPDATE autobill_db.group_user_list SET check_state_id = -1 WHERE group_id = ? and user_id = ?";
        update = jdbcTemplate.update(CHECKOUT_CANCEL_SQL, groupId, userId);
        String TOTAL_CHECKOUT_COMFIRM_SQL = "UPDATE autobill_db.groups SET check_state_id = 2 WHERE group_id = ?";
        update = jdbcTemplate.update(TOTAL_CHECKOUT_COMFIRM_SQL, groupId);
        String BILL_STATE_CHANGE_SQL = "UPDATE autobill_db.group_bill_list SET total_add_state_id = 1 WHERE group_id = ? AND total_add_state_id = 2";
        update = jdbcTemplate.update(BILL_STATE_CHANGE_SQL, groupId);
    }

    @Override
    public int getUserCancelCheckout(int groupId) {
        String GET_CANCEL_USER_SQL = "SELECT user_id FROM autobill_db.group_user_list "
                + "WHERE group_id = ? "
                + "AND check_state_id = -1";
        return jdbcTemplate.queryForObject(GET_CANCEL_USER_SQL, Integer.class, groupId);
        
    }

    @Override
    public float getIndivitualTotalBalance(int groupId, int userId) {
        String GET_BILL_LIST_SQL = "SELECT gb.bill_id "
                + "FROM autobill_db.group_bill_list AS gb "
                + "JOIN bill_user_list AS ul "
                + "ON gb.bill_id = ul.bill_id "
                + "WHERE group_id = ? AND user_id = ? "
                + "AND (total_add_state_id = 1 OR total_add_state_id = 2)";
        List<Map<String, Object>> billList = jdbcTemplate.queryForList(GET_BILL_LIST_SQL, groupId, userId);
        float balance = 0;
        for(Map<String, Object> row : billList) {
            int billId = Integer.parseInt(row.get("bill_id").toString());
            String GET_BILL_AMOUnT = "";
            String GET_USER_LIST_SQL = "SELECT bill_amount, bill_participant FROM autobill_db.bills WHERE bill_id = ?";
            List<Map<String, Object>> list = jdbcTemplate.queryForList(GET_USER_LIST_SQL, billId);
            Map<String, Object> result = list.get(0);
            float billAmount = Float.parseFloat(result.get("bill_amount").toString());
            int size = Integer.parseInt(result.get("bill_participant").toString());
            balance += billAmount / size;
        }
        return balance;
    }

    @Override
    public String[] getTranList(Group group) {
        Map<Integer, Float> billList = new HashMap();
        for(int userId : group.getUsersList()) 
            billList.put(userId, Float.parseFloat("0"));
        for(int billId : group.getBillsList()) {
            String GET_TOTAL_BILL_ADD_SQL = "SELECT total_add_state_id FROM autobill_db.group_bill_list WHERE bill_id = ? AND group_Id = ?";
            int billState = jdbcTemplate.queryForObject(GET_TOTAL_BILL_ADD_SQL, Integer.class, billId, group.getGroupId());
            System.out.println(billId);
            if(billState == 2) {
                String GET_PAYER_ID_SQL = "SELECT bill_payer FROM autobill_db.bills WHERE bill_id = ?";
                int payerId = jdbcTemplate.queryForObject(GET_PAYER_ID_SQL, Integer.class, billId);
                String GET_AMOUNT_SQL = "SELECT bill_amount FROM autobill_db.bills WHERE bill_id = ?";
                float amount = jdbcTemplate.queryForObject(GET_AMOUNT_SQL, Float.class, billId);
                billList.replace(payerId, billList.get(payerId) + amount);
                String GET_USER_SQL = "SELECT user_id FROM autobill_db.bill_user_list WHERE bill_id = ?;";
                List<Map<String, Object>> users = jdbcTemplate.queryForList(GET_USER_SQL, billId);
                int num = users.size();
                for(int i = 0; i < users.size(); i++) {
                    int id = Integer.parseInt(users.get(i).get("user_id").toString());
                    billList.replace(id, billList.get(id) - amount / num);
                }
            }
        }
        PriorityQueue<Float> pos = new PriorityQueue<Float>();
        PriorityQueue<Float> neg = new PriorityQueue<Float>();
        int count = 0;
        for(Integer key : billList.keySet()) {
            if(billList.get(key) >= 0) {
                pos.add(key);
            }
            count ++;
        }
        return tranList;
    }
    
    
}
