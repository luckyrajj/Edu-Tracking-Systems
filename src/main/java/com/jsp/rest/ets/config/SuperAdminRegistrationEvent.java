package com.jsp.rest.ets.config;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import com.jsp.rest.ets.user.SuperAdmin;
import com.jsp.rest.ets.user.User;
import com.jsp.rest.ets.user.UserRepository;
import com.jsp.rest.ets.user.UserRole;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class SuperAdminRegistrationEvent {


	public SuperAdminRegistrationEvent(UserRepository userRepository) {
		super();
		this.userRepository = userRepository;
		
	}

	private UserRepository userRepository;

	@Value("${super_admin.email}")
	private String superAdminEmail;

	//These method will never have parameter if they have it is of bean type
	
	@EventListener(classes = ApplicationReadyEvent.class)
	public void registerSuperAdmin() {
		log.info("Checking if super_admin present ");
		List<User>users=userRepository.findByRole(UserRole.SUPER_ADMIN);
		if(users.isEmpty()) {
			log.info("super_admin is not present ,creating one");
			SuperAdmin user=new SuperAdmin();
			user.setEmail(superAdminEmail);
			user.setPassword(UUID.randomUUID().toString());
			user.setRole(UserRole.SUPER_ADMIN);
			userRepository.save(user);
		}		
		else {
			log.info("super_admin present with email: "+users.getFirst().getEmail());
		}

	}
}
