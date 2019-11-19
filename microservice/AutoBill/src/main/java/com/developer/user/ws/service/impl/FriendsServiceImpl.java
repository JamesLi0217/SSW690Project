package com.developer.user.ws.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
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
}
