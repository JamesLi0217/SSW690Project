/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.controller;

import com.example.demo.dao.impl.GroupDaoImpl;
import com.example.demo.model.Group;
import com.example.demo.model.User;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author sean
 */
@RestController
@RequestMapping(path = "/group")
public class GroupController {
    
    @Autowired
    private GroupDaoImpl groupdao;
    
    @GetMapping()
    public String toTest() {
        return "Welcome to Group Server.....";
    }
    
    @RequestMapping("/createGroup/{groupName}_{userId}")
    public boolean createGroup(@PathVariable("userId") int userId, @PathVariable("groupName") String groupName) {
        List<Integer> users_list = new ArrayList<>();
        users_list.add(userId);
        Group group = new Group(users_list, groupName);
        System.out.println(group.getGroup_name());
        return groupdao.createGroup(group);
//        return Collections.singletonList(
//                new User(userId, userName, "123456")
//        );
    }
    
    @RequestMapping("/getGroupName/{groupId}")
    public String getGroupName(@PathVariable("groupId") String groupId) {
        return groupdao.getGroupName(Integer.parseInt(groupId));
    }
    
//    @RequestMapping("/addMemberToGroup/{groupId}_{userList}")
//    public boolean addMemberToGroup() {
//        
//        
//        
//    }
    
    @RequestMapping("/getGroup/{groupId}")
    public Group getGroup(@PathVariable("groupId") int groupId) {
        return groupdao.getGroup(groupId);
    }
    
//    @RequestMapping("/getIndivitualTotalBalance/{groupId}_{userId}")
//    public float getIndivitualTotalBalance();
//    
//    @RequestMapping("/checkout/{groupId}_{userId}")
//    public List<String> checkout();
//    
    @RequestMapping("/checkoutComfirm/{groupId}_{userId}_{state}")
    public int checkoutComfirm(@PathVariable("groupId") int groupId, @PathVariable("userId") int userId, @PathVariable("state") int state){
        return groupdao.checkoutComfirm(groupId, userId, state);
    }
//    
//    @RequestMapping("/getTotalCheckoutState/{groupId}")
//    public int getTotalCheckoutState();
//    
//    @RequestMapping("/getUserCheckoutComfirm/{groupId}_{userId}")
//    public int getUserCheckoutComfirm();
}
