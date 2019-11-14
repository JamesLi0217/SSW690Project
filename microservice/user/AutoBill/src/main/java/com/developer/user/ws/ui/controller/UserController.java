package com.developer.user.ws.ui.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.developer.user.ws.service.UserService;
import com.developer.user.ws.shared.dto.UserDto;
import com.developer.user.ws.ui.model.request.UserDetailsRequestModel;
import com.developer.user.ws.ui.model.response.UserRest;

@RestController
@RequestMapping("users") // http://localhost:8083/users
public class UserController {
	
	@Autowired
	UserService userService;
	
//	@GetMapping(path = "/{id}")
//	public UserRest getUser(@PathVariable String id) {
//		UserRest returnValue = new UserRest(); 
//		
//		UserDto findDto = userService.getUserByUserId(Integer.parseInt(id));
//		BeanUtils.copyProperties(findDto, returnValue);//  no password in returnValue
//		return returnValue;
//	}
	@GetMapping
	public String getUser() {
		return "ss";
	}

	@PostMapping
	public UserRest createUser(@RequestBody UserDetailsRequestModel userDetails) {
		
		
		
		UserRest returnValue = new UserRest(); 
		UserDto userDto = new UserDto(); // user data transfer object between layers
								// source     target
		BeanUtils.copyProperties(userDetails, userDto); // copy data from userdetails to dto
		
		UserDto createDto = userService.createUser(userDto); //pass detail from ui level to service layer
															// bussiness logical control to create user 
		BeanUtils.copyProperties(createDto, returnValue);//  no password in returnValue
		return returnValue;
	}
	
	@PutMapping(path = "/{id}")
	public UserRest updateUser(@RequestBody UserDetailsRequestModel userDetails) {
		
		UserRest returnValue = new UserRest(); 
		UserDto userDto = new UserDto(); // user data transfer object between layers
								// source     target
		BeanUtils.copyProperties(userDetails, userDto); // copy data from userdetails to dto
		
		UserDto updateDto = userService.updateUser(userDto); //pass detail from ui level to service layer
															// bussiness logical control to create user 
		BeanUtils.copyProperties(updateDto, returnValue);//  no password in returnValue
		return returnValue;
	}
	
	@DeleteMapping(path = "/{id}")
	public String deleteUser() {
		return "delete user is called";
	}
}
