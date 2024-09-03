package com.jsp.rest.ets.user;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.jsp.rest.ets.security.RegistrationRequest;
import com.jsp.rest.ets.util.AppResponseBuilder;
import com.jsp.rest.ets.util.ResponseStructure;

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
}
