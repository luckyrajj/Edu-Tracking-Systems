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
	
	public Student mapToStudentEntity(StudentRequest studentRequest, Student student) {
		student.setUsername(studentRequest.getUsername());
		student.setEmail(studentRequest.getEmail());
		student.setDegree(studentRequest.getDegree());
		student.setStream(studentRequest.getStream());
		student.setYop(studentRequest.getYop());
		student.setDegreePercentage(studentRequest.getDegreePercentage());
		student.setTwelvethPercentage(studentRequest.getTwelvethPercentage());
		student.setTenthPercentage(studentRequest.getTenthPercentage());
		return student;
	}
	public StudentResponse mapToStudentResponse(Student student) {
		StudentResponse studentResponse=new StudentResponse();
		studentResponse.setUsername(student.getUsername());
		studentResponse.setEmail(student.getEmail());
		studentResponse.setDegree(student.getDegree());
		studentResponse.setStream(student.getStream());
		studentResponse.setDegree(student.getDegree());
		studentResponse.setRole(student.getRole());
		studentResponse.setCreatedAt(student.getCreatedAt());
		studentResponse.setModifiedAt(student.getModifiedAt());
		studentResponse.setYop(student.getYop());
		studentResponse.setStack(student.getStack());
	
		studentResponse.setDegreePercentage(student.getDegreePercentage());
		studentResponse.setTwelvethPercentage(student.getTwelvethPercentage());
		studentResponse.setTenthPercentage(student.getTenthPercentage());
		return studentResponse;
	}


	
}
