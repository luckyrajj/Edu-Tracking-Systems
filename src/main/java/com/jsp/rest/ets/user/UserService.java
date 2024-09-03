package com.jsp.rest.ets.user;

import org.springframework.stereotype.Service;

import com.jsp.rest.ets.exception.TrainerNotFoundByIdException;
import com.jsp.rest.ets.security.RegistrationRequest;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserService {

	private UserRepository userRepository;
	private UserMapper userMapper;
	
	public UserResponse saveUser(RegistrationRequest registrationRequest,UserRole role) {
		User user = null;
		switch (role) {
		case ADMIN -> user = new Admin();
		case HR -> user = new HR();
		case STUDENT -> user = new Student();
		case TRAINER -> user = new Trainer();
		default -> throw new IllegalArgumentException("Unexpected value: " + role);
		}

		if(user != null) {
			user = userMapper.mapToUserEntity(registrationRequest, user);
			user.setRole(role);
			user = userRepository.save(user);
		}

		return userMapper.mapToUserResponse(user);
	}
	
	public UserResponse updateTrainer(TrainerRequest trainerRequest,String userId) {
		return userRepository.findById(userId).map(user->{
			userMapper.mapToTrainerEntity(trainerRequest, (Trainer)user);
			user=userRepository.save(user);
			return userMapper.mapToTrainerResponse((Trainer)user);
		}).orElseThrow(()-> new TrainerNotFoundByIdException("failed to update the trainer"));


	}
}
