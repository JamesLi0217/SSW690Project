package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.dao.impl.UserDaoImpl;
import com.example.demo.model.User;

@RestController
//@RequestMapping(path = "/user")
public class UserController {
	
	 @Autowired
		private UserDaoImpl userdao;
	 
//	 @GetMapping
	 @RequestMapping(path = "/home")
	 public String toTest() {
	  return "Welcome to Java Inspires...";
	 }
	 
	 @RequestMapping(path = "/")
	 public String defaultPage() {
	  return "Welcome to default page ...";
	 }
	 
//	 @RequestMapping(path = "/login")
	 @RequestMapping(path = "/login", method = RequestMethod.GET)
	 public String toTestLogin() {
	  return "Sucess login...";
	 }
	 
	 @RequestMapping(path = "/loginerror")
	 public String toTestLoginError() {
	  return "fail to login...";
	 }
	 
	 @RequestMapping(path = "/register", method = RequestMethod.GET)
	 private void register() 
	 {
		User user = new User();
		user.setUserEmail("124qq.edu");
		user.setPassword("123");
		user.setUserName("lpz12");
		userdao.createUser(user);
	 }
	 
	 @RequestMapping(path = "/getUser")
	 private String getUserName() {
		return userdao.getUser(19).user_name;
		 
	 }
		 
}
