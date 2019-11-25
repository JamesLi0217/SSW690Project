package com.developer.user.ws.ui.controller;


import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.developer.user.ws.io.entity.FriendsEntity;
import com.developer.user.ws.service.FriendsService;
import com.developer.user.ws.service.GroupService;
import com.developer.user.ws.service.UserService;
import com.developer.user.ws.shared.dto.FriendDto;
import com.developer.user.ws.shared.dto.GroupDto;
import com.developer.user.ws.shared.dto.UserDto;
import com.developer.user.ws.ui.model.request.UserDetailsRequestModel;
import com.developer.user.ws.ui.model.response.FriendRest;
import com.developer.user.ws.ui.model.response.GroupRest;
import com.developer.user.ws.ui.model.response.UserRest;


@RestController
@RequestMapping("users") // http://localhost:8083/users
public class UserController {
	
	@Autowired
	UserService userService;
	
	@Autowired
	GroupService groupService;
	
	@Autowired
	GroupService groupsService;
	
	@Autowired
	FriendsService friendsService;
	
	@GetMapping (path = "/{id}",produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public UserRest getUser(@PathVariable String id) {
		UserRest returnValue = new UserRest();
		UserDto findDto = userService.getUserByUserId(Integer.parseInt(id));
		ModelMapper modelMapper = new ModelMapper();
		returnValue = modelMapper.map(findDto, UserRest.class);
//		BeanUtils.copyProperties(findDto, returnValue);
		return returnValue;
	}
	
//	@GetMapping (path = "/AllGroups/{id}",produces = {MediaType.APPLICATION_JSON_VALUE })
//	public UserRest getUserAllGroups(@PathVariable String id) {
//		UserRest returnValue = new UserRest();
//		UserDto findDto = userService.getUserByUserId(Integer.parseInt(id));
//		BeanUtils.copyProperties(findDto, returnValue);
//		return returnValue;
//		GroupRest returnValue = new GroupRest();
//		GroupDto findDto = userService.getUserGroupsByUserId(Integer.parseInt(id));
//		BeanUtils.copyProperties(findDto, returnValue);
//		return returnValue;
//	}
	
	

	@PostMapping(consumes = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE }, produces = {
			MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
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
	
	@PutMapping(path = "/{id}",
			consumes = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE }, 
			produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public UserRest updateUser(@PathVariable String id, @RequestBody UserDetailsRequestModel userDetails) {
		
		UserRest returnValue = new UserRest(); 
		UserDto userDto = new UserDto(); // user data transfer object between layers
								// source     target
		BeanUtils.copyProperties(userDetails, userDto); // copy data from userdetails to dto
		
		UserDto updateDto = userService.updateUser(Integer.parseInt(id), userDto); //pass detail from ui level to service layer
															// bussiness logical control to create user 
		BeanUtils.copyProperties(updateDto, returnValue);//  no password in returnValue
		return returnValue;
	}
	
	@DeleteMapping(path = "/{id}")
	public String deleteUser() {
		return "delete user is called";
	}
	
	
	@GetMapping(path = "/{id}/AllGroups", produces = { 
			MediaType.APPLICATION_JSON_VALUE})
	public List<GroupRest> getUserGroups(@PathVariable String id) {
		List<GroupRest> returnValue = new ArrayList<>();

		List<GroupDto> groupDto = groupService.getGroups(Integer.parseInt(id));

		if (groupDto != null && !groupDto.isEmpty()) {
			Type listType = new TypeToken<List<GroupRest>>() {
			}.getType();
			returnValue = new ModelMapper().map(groupDto, listType);

		}

		return returnValue;
	}
	
	@GetMapping(path = "/{id}/Friends", produces = { 
			MediaType.APPLICATION_JSON_VALUE})
	public List<FriendRest> getUserFriends(@PathVariable String id) {
		List<FriendRest> returnValue = new ArrayList<>();

		List<FriendDto> friendsDto = friendsService.getFriends(Integer.parseInt(id));

		if (friendsDto != null && !friendsDto.isEmpty()) {
			Type listType = new TypeToken<List<FriendsEntity>>() {
			}.getType();
			returnValue = new ModelMapper().map(friendsDto, listType);

		}

		return returnValue;
	}
	
	// ADD FRIEND WAITING FOR APPROVE 
	@PostMapping(path = "/{id}/addFriends/{friend_id}",
			consumes = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE }, produces = {
			MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public boolean addFriends(@PathVariable String id,@PathVariable String friend_id) {
		
		return friendsService.addFriends(Integer.parseInt(id),Integer.parseInt(friend_id)); 
	}
	
	// APPROVE FRIEND  
	@PutMapping(path = "/{id}/approveFriends/{friend_id}",
			consumes = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE }, produces = {
			MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public boolean approveFriends(@PathVariable String id,@PathVariable String friend_id) {
		
		return friendsService.approveFriends(Integer.parseInt(id),Integer.parseInt(friend_id)); 
	}

	// DENY FRIEND 
	@PutMapping(path = "/{id}/denyFriends/{friend_id}",
			consumes = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE }, produces = {
			MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public boolean denyFriends(@PathVariable String id,@PathVariable String friend_id) {
		
		return friendsService.denyFriends(Integer.parseInt(id),Integer.parseInt(friend_id)); 
	}
}
