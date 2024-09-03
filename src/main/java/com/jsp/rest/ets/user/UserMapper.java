package com.jsp.rest.ets.user;

import org.springframework.stereotype.Component;

import com.jsp.rest.ets.security.RegistrationRequest;

@Component
public class UserMapper {

	public User mapToUserEntity( RegistrationRequest registrationRequest, User user) {
		user.setUsername(registrationRequest.getUsername());
		user.setEmail(registrationRequest.getEmail());
		user.setPassword(registrationRequest.getPassword());
		
		return user; 
	}
	
	public UserResponse mapToUserResponse(User user) {
		UserResponse userResponse=new UserResponse();
		userResponse.setUsername(user.getUsername());
		userResponse.setEmail(user.getEmail());
		userResponse.setRole(user.getRole());
		userResponse.setCreatedAt(user.getCreatedAt());
		userResponse.setModifiedAt(user.getModifiedAt());
		return userResponse;
	}
	
	public Trainer mapToTrainerEntity(TrainerRequest trainerRequest,Trainer trainer) {
		trainer.setUsername(trainerRequest.getUsername());
		trainer.setEmail(trainerRequest.getEmail());
		trainer.setSubjects(trainerRequest.getSubjects());
		return trainer;
	}
	public TrainerResponse mapToTrainerResponse(Trainer trainer) {
		TrainerResponse response=new TrainerResponse();
		response.setUsername(trainer.getUsername());
		response.setEmail(trainer.getEmail());
		response.setSubjects(trainer.getSubjects());
		response.setCreatedAt(trainer.getCreatedAt());
		response.setRole(trainer.getRole());
		response.setModifiedAt(trainer.getModifiedAt());
		return response;
	}
	
}
