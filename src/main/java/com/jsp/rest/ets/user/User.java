package com.jsp.rest.ets.user;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "users")
public class User {

	@Column(name = "user_id")
	private String userId;
	@Column(name = "user_name")
	private String userName;
	@Column(name = "user_email")
	private String email;
	@Column(name = "user_password")
	private String password;
	@Column(name = "user_role")
	private String role;
	@Column(name = "created_at")
	private LocalDateTime createdAt;
	@Column(name = "modified_at")
	private LocalDateTime modifiedAt;
}
