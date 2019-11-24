package com.developer.user.ws.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.developer.user.ws.io.entity.FriendsEntity;
import com.developer.user.ws.io.entity.UserEntity;
import com.developer.user.ws.io.repository.FriendsRepository;
import com.developer.user.ws.io.repository.UserRepository;
import com.developer.user.ws.service.FriendsService;
import com.developer.user.ws.shared.dto.FriendDto;

@Service
public class FriendsServiceImpl implements FriendsService {

	@Autowired
	UserRepository userRepository;
	
	@Autowired
	FriendsRepository friendsRepository;
	
	@Override
	public List<FriendDto> getFriends(int userId) {
        List<FriendDto> returnValue = new ArrayList<>();
        ModelMapper modelMapper = new ModelMapper();
        
        UserEntity userEntity = userRepository.findByUserId(userId);
        if(userEntity==null) return returnValue;
 
        List<FriendsEntity> friends = friendsRepository.findAllByUserId(userId);
//        userEntity.setFriendList(friends);
        for(FriendsEntity friendEntity:friends)
        {
            returnValue.add(modelMapper.map(friendEntity, FriendDto.class) );
        }
        
        return returnValue;
	}

	@Override
	public boolean addFriends(int user_id, int friend_id) {
		// TODO Auto-generated method stub
		UserEntity userEntity = userRepository.findByUserId(user_id);
		UserEntity friendEntity = userRepository.findByUserId(friend_id);
		if (userEntity == null || friendEntity == null)
			throw new UsernameNotFoundException("This id is not in db");
		
		FriendsEntity friendsEntity = new FriendsEntity();
		friendsEntity.setFriendId(friend_id);
		friendsEntity.setUserId(user_id);
		friendsEntity.setConfirmStateId(0);
		friendsRepository.save(friendsEntity);
		
		return true;
	}

	@Override
	public boolean approveFriends(int user_id, int friend_id) {
		// TODO Auto-generated method stub
		UserEntity userEntity = userRepository.findByUserId(user_id);
		UserEntity friendEntity = userRepository.findByUserId(friend_id);
		if (userEntity == null || friendEntity == null)
			throw new UsernameNotFoundException("This id is not in db");
		
		FriendsEntity friendsEntity = friendsRepository.findByUserId(user_id);
		friendsEntity.setConfirmStateId(1);
		friendsRepository.save(friendsEntity);
		
		return true;
	}

	@Override
	public boolean denyFriends(int user_id, int friend_id) {
		// TODO Auto-generated method stub
		UserEntity userEntity = userRepository.findByUserId(user_id);
		UserEntity friendEntity = userRepository.findByUserId(friend_id);
		if (userEntity == null || friendEntity == null)
			throw new UsernameNotFoundException("This id is not in db");
		FriendsEntity friendsEntity = friendsRepository.findByUserId(user_id);
		friendsEntity.setConfirmStateId(-1);
		friendsRepository.save(friendsEntity);
		
		return true;
	}
}
