/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.bill.dao.impl;

import com.example.bill.dao.BillDao;
import com.example.bill.model.Bill;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 *
 * @author sean
 */
@Repository("AWSBill")
public class BillDaoImpl implements BillDao {
    
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public Bill getBillGeneral(int billId) {
        String GET_Bill_PROFILE = "SELECT bill_name, bill_amount, bill_date FROM autobill_db.bills WHERE bill_id = ?";
        List<Map<String, Object>> tmp = jdbcTemplate.queryForList(GET_Bill_PROFILE, billId);
        Map<String, Object> result = tmp.get(0);
        String billName = result.get("bill_name").toString();
        float billAmount = Float.parseFloat(result.get("bill_amount").toString());
        int billDate = Integer.parseInt(result.get("bill_date").toString());
        String Get_Bill_STATE_SQL = "SELECT total_add_state_id FROM autobill_db.group_bill_list WHERE bill_id = ?";
        int state = jdbcTemplate.queryForObject(Get_Bill_STATE_SQL, Integer.class, billId);
        return new Bill(billId, billName, billAmount, billDate, state);
    }

    @Override
    public int createBill(Bill bill) {
        String CREATE_BILL_SQL = "INSERT INTO autobill_db.bills(bill_name, bill_amount, bill_date, bill_receipt, bill_description) VALUES (?, ?, ?, ?, ?)";
        int update = jdbcTemplate.update(CREATE_BILL_SQL, bill.getBillName(), bill.getAmount(), bill.getDate(), bill.getReceiptImg(), bill.getDescription());
        if(update != 1) {
            return 0;
        }
        String GET_LAST_AUTO_INCREMENT_ID = "SELECT bill_id AS LastID FROM autobill_db.bills WHERE bill_id = @@Identity;";
        int billId = jdbcTemplate.queryForObject(GET_LAST_AUTO_INCREMENT_ID, Integer.class);
        bill.setBillId(billId);
        String CREATE_BILL_GROUP_SQL = "INSERT INTO autobill_db.group_bill_list(group_id, bill_id) VALUES (?, ?)";
        update = jdbcTemplate.update(CREATE_BILL_GROUP_SQL, bill.getGroupId(), bill.getBillId());
        if(update != 1) {
                return 0;
        }
        for(int i = 0; i < bill.getUsersList().length; i ++) {
            String CREATE_BILL_USER_SQL = "INSERT INTO autobill_db.bill_user_list(user_id, bill_id) VALUES (?, ?)";
            update = jdbcTemplate.update(CREATE_BILL_USER_SQL, bill.getUsersList()[i], bill.getBillId());
            if(update != 1) {
                return 0;
            }
        }
        return 1;
    }

    @Override
    public Bill getBill(int billId) {
        String GET_Bill_PROFILE = "SELECT * FROM autobill_db.bills WHERE bill_id = ?";
        List<Map<String, Object>> tmp = jdbcTemplate.queryForList(GET_Bill_PROFILE, billId);
        Map<String, Object> result = tmp.get(0);
        String billName = result.get("bill_name").toString();
        float billAmount = Float.parseFloat(result.get("bill_amount").toString());
        int billDate = Integer.parseInt(result.get("bill_date").toString());
        // bill_description
        String billReceipt = result.get("bill_receipt").toString();
        String billDesc = result.get("bill_description").toString();
        String Get_Bill_STATE_SQL = "SELECT total_add_state_id FROM autobill_db.group_bill_list WHERE bill_id = ?";
        int state = jdbcTemplate.queryForObject(Get_Bill_STATE_SQL, Integer.class, billId);
        String GET_USER_SQL = "SELECT user_id FROM autobill_db.bill_user_list WHERE bill_id = ?;";
        List<Map<String, Object>> users = jdbcTemplate.queryForList(GET_USER_SQL, billId);
        int[] userList = new int[users.size()];
        for(int i = 0; i < users.size(); i++) 
            userList[i] = Integer.parseInt(users.get(i).get("user_id").toString());
        return new Bill(billId, billName, billAmount, billDate, state, billReceipt, billDesc, userList);
    }

    @Override
    public int getTotalAddState(int billId) {
        String Get_Bill_STATE_SQL = "SELECT total_add_state_id FROM autobill_db.group_bill_list WHERE bill_id = ?";
        int state = jdbcTemplate.queryForObject(Get_Bill_STATE_SQL, Integer.class, billId);
        if(state == 1 || state == 3) 
            return state;
        // check has everyone comfirm the biil addition
        String GET_USER_SQL = "SELECT add_state_id FROM autobill_db.bill_user_list WHERE bill_id = ?;";
        List<Map<String, Object>> users = jdbcTemplate.queryForList(GET_USER_SQL, billId);
        int finish = 1;
        for (Map<String, Object> row : users) {
            if(Integer.parseInt(row.get("add_state_id").toString()) == 0) {
                finish = 0;
                break;
            }
        }
        // if everyone comfirm the bill addition, change bill state and add bill amount into group total amount
        if(finish == 1){
            String TOTAL_ADD_COMFIRM_SQL = "UPDATE autobill_db.group_bill_list SET total_add_state_id = 1 WHERE bill_id = ?";
            int update = jdbcTemplate.update(TOTAL_ADD_COMFIRM_SQL, billId);
            String GET_GROUP_ID_SQL = "SELECT group_id FROM autobill_db.group_bill_list WHERE bill_id = ?";
            int groupId = jdbcTemplate.queryForObject(GET_GROUP_ID_SQL, Integer.class, billId);
            String GET_BILL_AMOUNT = "SELECT bill_amount FROM autobill_db.bills WHERE bill_id = ?";
            float amount = jdbcTemplate.queryForObject(GET_BILL_AMOUNT, Float.class, billId);
            String CHANGE_GROUP_AMOUNT_SQL = "UPDATE autobill_db.groups SET total_amount = total_amount + ? WHERE group_id = ?";
            update = jdbcTemplate.update(CHANGE_GROUP_AMOUNT_SQL,amount, groupId);
        }
        return jdbcTemplate.queryForObject(Get_Bill_STATE_SQL, Integer.class, billId);
    }

    @Override
    public int billAddComfirm(int billId, int userId) {
        String ADD_COMFIRM_SQL = "UPDATE autobill_db.bill_user_list SET add_state_id = 1 WHERE bill_id = ? and user_id = ?";
        return jdbcTemplate.update(ADD_COMFIRM_SQL, billId, userId);
    }

    @Override
    public int getUserAddComfirm(int billId, int userId) {
        String GET_ADD_COMFIRM_SQL = "SELECT add_state_id FROM autobill_db.bill_user_list WHERE bill_id = ? and user_id = ?";
        return jdbcTemplate.queryForObject(GET_ADD_COMFIRM_SQL, Integer.class, billId, userId);
    }

    @Override
    public int cancelAddComfirm(int billId, int userId) {
        String ADD_CANCEL_SQL = "UPDATE autobill_db.bill_user_list SET add_state_id = -1 WHERE bill_id = ? and user_id = ?";
        int update = jdbcTemplate.update(ADD_CANCEL_SQL, billId, userId);
        if(update != 1)
            return 0;
        String TOTAL_ADD_COMFIRM_SQL = "UPDATE autobill_db.group_bill_list SET total_add_state_id = -1 WHERE bill_id = ?";
        update = jdbcTemplate.update(TOTAL_ADD_COMFIRM_SQL, billId);
        if(update != 1)
            return 0;
        return 1;
    }

    @Override
    public int getUserCancelAdd(int billId) {
        String GET_CANCEL_USER_SQL = "SELECT user_id FROM autobill_db.bill_user_list WHERE bill_id = ? and add_state_id = -1";
        return jdbcTemplate.queryForObject(GET_CANCEL_USER_SQL, Integer.class, billId);
    }


    
    
    
}
