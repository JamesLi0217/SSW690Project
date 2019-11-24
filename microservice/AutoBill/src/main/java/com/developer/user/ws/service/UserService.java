package com.developer.user.ws.service;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.developer.user.ws.shared.dto.GroupDto;
import com.developer.user.ws.shared.dto.UserDto;

public interface UserService extends UserDetailsService{

	UserDto createUser(UserDto user);
	UserDto getUser(String email);
	UserDto getUserByUserId(int id);
	UserDto updateUser(int id, UserDto user);
//	GroupDto getUserGroupsByUserId(int id);
//	List<UserDto> getFriends(int parseInt);
	
}
