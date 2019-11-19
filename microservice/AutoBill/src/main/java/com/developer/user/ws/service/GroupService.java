package com.developer.user.ws.service;

import java.util.List;

import com.developer.user.ws.io.entity.GroupEntity;
import com.developer.user.ws.io.entity.UserEntity;
import com.developer.user.ws.shared.dto.GroupDto;

public interface GroupService {
	List<GroupDto> getGroups(int userId);
//    GroupDto getGroup(int groupId);
}
