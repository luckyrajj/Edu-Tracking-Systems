package com.jsp.rest.ets.user;

import org.springframework.stereotype.Component;

import com.jsp.rest.ets.security.RegistrationRequest;

@Component
public class UserMapper {

	public User mapToUserEntity( RegistrationRequest registrationRequest, User user) {
		user.setUserName(registrationRequest.getUsername());
		user.setEmail(registrationRequest.getEmail());
		user.setPassword(registrationRequest.getPassword());
		
		return user; 
	}
	
}
