package com.jsp.rest.ets.user;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.jsp.ets.user.UserResponse;
import com.jsp.ets.user.UserRole;
import com.jsp.rest.ets.security.RegistrationRequest;
import com.jsp.rest.ets.util.AppResponseBuilder;
import com.jsp.rest.ets.util.ResponseStructure;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
public class UserController {

	private UserService userService;
	private AppResponseBuilder responseBuilder;
	
	@PostMapping("/admins/register")
	public ResponseEntity<ResponseStructure<UserResponse>> saveAdmin(@RequestBody RegistrationRequest registrationRequest){
		UserResponse  userResponse=userService.saveUser(registrationRequest,UserRole.ADMIN);
		return responseBuilder.success(HttpStatus.OK, "admin created", userResponse);
	}
	
	@PostMapping("/hrs/register")
	public ResponseEntity<ResponseStructure<UserResponse>>saveHr(@RequestBody @Valid RegistrationRequest registrationRequest){
		UserResponse hrResponse=userService.saveUser(registrationRequest,UserRole.HR);
		return responseBuilder.success(HttpStatus.CREATED, "Hr created Successfully", hrResponse);
	}
	
	@PostMapping("/trainers/register")
	public ResponseEntity<ResponseStructure<UserResponse>> saveTrainer(@RequestBody RegistrationRequest registrationRequest){
		UserResponse response=userService.saveUser(registrationRequest,UserRole.TRAINER);
		return responseBuilder.success(HttpStatus.CREATED, "Trainer created successfully", response);
	}
	
	@PostMapping("/students/register")
	public ResponseEntity<ResponseStructure<UserResponse>> saveStudent(@RequestBody RegistrationRequest registrationRequest){
		UserResponse userResponse=userService.saveUser(registrationRequest, UserRole.STUDENT);
		return responseBuilder.success(HttpStatus.CREATED, "student created successfully", userResponse);
	}

}
