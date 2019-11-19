package com.developer.user.ws.service;

import java.util.List;

import com.developer.user.ws.shared.dto.FriendDto;
import com.developer.user.ws.shared.dto.UserDto;

public interface FriendsService {
	List<FriendDto> getFriends(int userId);

	boolean addFriends(int user_id, int friend_id); // true is for invite friends successfully
	
	boolean approveFriends(int user_id, int friend_id); // true is for being friends each other successfully
	
	boolean denyFriends(int user_id, int friend_id); // true is for denying friends each other successfully
}
