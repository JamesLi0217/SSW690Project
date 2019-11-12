package com.developer.user.ws.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.developer.user.ws.shared.dto.UserDto;

public interface UserService extends UserDetailsService{

	UserDto createUser(UserDto user);
	UserDto getUser(String email);
	UserDto getUserByUserId(int id);
	UserDto updateUser(UserDto user);
}
