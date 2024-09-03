package com.jsp.rest.ets.user;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserResponse {
	
	
	private String username;
	private String email;
	private UserRole role;
	private LocalDateTime createdAt;
	private LocalDateTime modifiedAt;
	
}
