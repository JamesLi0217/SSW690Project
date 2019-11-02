package com.example.demo.controller;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.dao.impl.UserDaoImpl;
import com.example.demo.model.User;

@RestController
public class UserController {
	
 	@Autowired
	private UserDaoImpl userdao;
	 @Autowired private DataSource dataSource;
	 

	 @RequestMapping(path = "/home")
	 @GetMapping()
  public String toTest() {
	  return "Welcome to Java Inspires...";
	 }
	 
	 @RequestMapping(path = "/")
	 public String defaultPage() {
	  return "Welcome to default page ...";
	 }
	 
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

		user.setUserEmail("tc1@gmai.com");
		user.setPassword("55553");
		user.setUserName("tc");
		userdao.createUser(user);
	 }
	 
//	 @RequestMapping(path = "/getUser/{id}")
//	 private User getUserName(Integer id) {
//		System.out.println(userdao);
//		return userdao.getUser(id);
//		 
//	 }
	 
	 
	 // for search name by id
	 @RequestMapping(path = "/getUserName/{id}")
	 private String getUserName(@PathVariable("id") String id) {
		 int id_num = Integer.parseInt(id);
		 return userdao.getUserName(id_num);
	 }
	 
	 // for search email by id
	 @RequestMapping(path = "/getUserEmail/{id}")
	 private String getUserEmail(@PathVariable("id") String id) {
		 int id_num = Integer.parseInt(id);
		 return userdao.getUserEmail(id_num);
	 }
	 
	// for search id by name
	 @RequestMapping(path = "/getUserID/{name}")
	 private Integer getUserID(@PathVariable("name") String name) {
//		 int id_num = Integer.parseInt(id);
		 return userdao.getUserID(name);
	 }
		 
}
