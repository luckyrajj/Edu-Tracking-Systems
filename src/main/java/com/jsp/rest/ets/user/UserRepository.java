package com.jsp.rest.ets.user;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository  extends JpaRepository<User, String>{
	
	
	public List<User> findByRole(UserRole role);

	public Optional<User> findByEmail(String email);

}
