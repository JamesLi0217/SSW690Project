/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.bill.controller;

import com.example.bill.model.Group;
import com.example.bill.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author sean
 */
@RequestMapping("group")
@RestController
public class GroupController {
    
    private final GroupService groupService;
    
    @Autowired
    public GroupController(GroupService groupService) {
        this.groupService = groupService;
    }
    
    @PostMapping
    public void createGroup(@RequestBody Group group) {
        groupService.createGroup(group);
    }
    
    @GetMapping("{groupId}")
    public Group getGroup(@PathVariable("groupId") int groupId) {
        return groupService.getGroup(groupId);
    }
    
    @GetMapping("/name/{groupId}")
    public String getGroupName(@PathVariable("groupId") int groupId) {
        return groupService.getGroupName(groupId);
    }
    
    @PostMapping("/user")
    public void addGroupMember(@RequestBody Group group) {
        groupService.addGroupMember(group.getGroupId(), group.getUsersList());
    }
    
    @GetMapping("balance/{groupId}/{userId}")
    public float getIndivitualTotalBalance(@PathVariable("groupId") int groupId,
                                            @PathVariable("userId") int userId) {
        return groupService.getIndivitualTotalBalance(groupId, userId);
    }
    
    @PostMapping("/checkout/{groupId}")
    public String[] checkout(@PathVariable("groupId") int groupId) {
        return groupService.checkout(groupId);
    }
    
    @PostMapping("/checkout/{groupId}/{userId}")
    public int checkoutComfirm(@PathVariable("groupId") int groupId,
                                @PathVariable("userId") int userId) {
        return groupService.checkoutComfirm(groupId, userId);
    }
    
    @PostMapping("/checkout/cancel/{groupId}/{userId}")
    public int cancelCheckoutComfirm(@PathVariable("groupId") int groupId,
                                @PathVariable("userId") int userId) {
        return groupService.cancelCheckoutComfirm(groupId, userId);
    }
    
    @GetMapping("/checkout/cancel/{groupId}")
    public int getUserCancelCheckout(@PathVariable("groupId") int groupId) {
        return groupService.getUserCancelCheckout(groupId);
    }
    
    @GetMapping("checkout/{groupId}")
    public int getTotalCheckoutState(@PathVariable("groupId") int groupId) {
        return groupService.getTotalCheckoutState(groupId);
    }
    
    @GetMapping("checkout/{groupId}/{userId}")
    public int getUserCheckoutComfirm(@PathVariable("groupId") int groupId,
                                        @PathVariable("userId") int userId) {
        return groupService.getUserCheckoutComfirm(groupId, userId);
    }
}
