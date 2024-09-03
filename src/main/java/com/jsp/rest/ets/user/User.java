package com.jsp.rest.ets.user;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "users")
public class User {

	private String userId;
	
	private String userName;
	private String email;
	private String password;
	//private UserRole role;
	
	private LocalDateTime createdAt;
	private LocalDateTime modifiedAt;
}
