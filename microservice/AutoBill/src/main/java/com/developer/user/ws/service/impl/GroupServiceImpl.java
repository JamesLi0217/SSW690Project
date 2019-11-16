package com.developer.user.ws.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.developer.user.ws.io.entity.GroupEntity;
import com.developer.user.ws.io.entity.UserEntity;
import com.developer.user.ws.io.repository.GroupRepository;
import com.developer.user.ws.io.repository.UserRepository;
import com.developer.user.ws.service.GroupService;
import com.developer.user.ws.shared.dto.GroupDto;

@Service
public class GroupServiceImpl implements GroupService {

	@Autowired
	UserRepository userRepository;
	
	@Autowired
	GroupRepository groupRepository;
	
	@Override
	public List<GroupDto> getGroups(int userId) {
        List<GroupDto> returnValue = new ArrayList<>();
        ModelMapper modelMapper = new ModelMapper();
        
        UserEntity userEntity = userRepository.findByUserId(userId);
        if(userEntity==null) return returnValue;
 
        List<GroupEntity> groups = groupRepository.findAllByUserId(userId);
        userEntity.setAllGroups(groups);
        for(GroupEntity groupEntity:groups)
        {
            returnValue.add(modelMapper.map(groupEntity, GroupDto.class) );
        }
        
        return returnValue;
	}

	@Override
	public GroupDto getGroup(int groupId) {
		GroupDto returnValue = null;

		GroupEntity groupEntity = groupRepository.findByGroupId(groupId);
        
        if(groupEntity!=null)
        {
            returnValue = new ModelMapper().map(groupEntity, GroupDto.class);
        }
 
        return returnValue;
	}

}
