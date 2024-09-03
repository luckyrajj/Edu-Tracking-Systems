package com.jsp.rest.ets.user;

import org.springframework.stereotype.Component;

@Component
public class UserMapper {

	public User mapToUserEntity( userRequest, User user) {
		user.setUserName(userRequest.getUsername());
		user.setEmail(userRequest.getEmail());
		user.setPassword(userRequest.getPassword());
		user.setRole(userRequest.getRole());
		return 
	}
	
}
