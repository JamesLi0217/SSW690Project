package com.developer.user.ws.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.developer.user.ws.io.entity.GroupEntity;
import com.developer.user.ws.io.entity.UserEntity;
import com.developer.user.ws.io.repository.GroupRepository;
import com.developer.user.ws.io.repository.UserRepository;
import com.developer.user.ws.service.UserService;
import com.developer.user.ws.shared.Utils;
import com.developer.user.ws.shared.dto.GroupDto;
import com.developer.user.ws.shared.dto.UserDto;


@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository userRepository;
	
	@Autowired
	GroupRepository groupRepository;
	
	@Autowired
	Utils utils;
	
	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Override
	public UserDto createUser(UserDto user) {
		
//		check if the user is existed   something wrong drop the table
		if (userRepository.findByUserEmail(user.getUserEmail()) != null) throw new RuntimeException("already have this user!");
		
		UserEntity userEntity = new UserEntity();
		BeanUtils.copyProperties(user, userEntity); // user and userEntity' fields must be same !!!!!
		
//		UserEntity storedUserDetails = userRepository.save(userEntity);
		
		userEntity.setEncryptedPassword(bCryptPasswordEncoder.encode(user.getUserPassword())); // password will be enCrypted 
																							//before stored in db
		int publicId = utils.generateUserId(5);
		userEntity.setUserId(publicId); // Generate a 5 digit public id
		UserEntity storedUserDetails = userRepository.save(userEntity);
		UserDto returnValue = new UserDto();
		BeanUtils.copyProperties(storedUserDetails, returnValue); 
		return returnValue;
	}

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		UserEntity userEntity = userRepository.findByUserEmail(email);

		if (userEntity == null)
			throw new UsernameNotFoundException(email);
		return new User(userEntity.getUserEmail(), userEntity.getEncryptedPassword(), new ArrayList<>());
//		return new User(userEntity.getUserEmail(), userEntity.getUserPassword(), new ArrayList<>());
	}

	@Override
	public UserDto getUser(String email) {
		
		UserEntity userEntity = userRepository.findByUserEmail(email);
		if (userEntity == null)
			throw new UsernameNotFoundException("This email is not in db");
		
		UserDto returnValue = new UserDto();
		BeanUtils.copyProperties(userEntity, returnValue);
 
		return returnValue;

	}

	@Override
	public UserDto getUserByUserId(int id) {
		
		UserEntity userEntity = userRepository.findByUserId(id);
		if (userEntity == null)
			throw new UsernameNotFoundException("This id is not in db");


		List<GroupEntity> groups = groupRepository.findAllByUserId(id);
		userEntity.setAllGroups(groups);
		UserDto returnValue = new UserDto();
		BeanUtils.copyProperties(userEntity, returnValue);
		return returnValue;
	}
	
	

//	@Override
//	public UserDto updateUser(UserDto user) {
//		// TODO Auto-generated method stub
//		UserEntity userEntity = userRepository.findByUserId(user.getUserId());
//		if (userEntity == null)
//			throw new UsernameNotFoundException("This id is not in db");
//		BeanUtils.copyProperties(user, userEntity); // user and userEntity must be same !!!!!
//		
//		UserEntity storedUserDetails = userRepository.save(userEntity);
//		
//		userEntity.setEncryptedPassword(bCryptPasswordEncoder.encode(user.getUserPassword())); // password will be enCrypted 
//																							//before stored in db
//		UserDto returnValue = new UserDto();
//		BeanUtils.copyProperties(storedUserDetails, returnValue); 
//		return returnValue;
//	}

//	@Override
//	public GroupDto getUserGroupsByUserId(int id) {
//		// TODO Auto-generated method stub
//		GroupEntity groupEntity = groupRepository.findByUserId(id);
//		if (groupEntity == null)
//			throw new UsernameNotFoundException("This id is not in db");
//
//		GroupDto returnValue = new GroupDto();
//		BeanUtils.copyProperties(groupEntity, returnValue);
// 
//		return returnValue;
//	}
	 

}
