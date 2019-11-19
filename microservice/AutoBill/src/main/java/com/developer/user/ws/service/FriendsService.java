package com.developer.user.ws.service;

import java.util.List;

import com.developer.user.ws.shared.dto.FriendDto;

public interface FriendsService {
	List<FriendDto> getFriends(int userId);
}
