package com.jsp.rest.ets.user;

import com.jsp.rest.ets.util.SimpleResponseStructure;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.jsp.rest.ets.exception.StudentNotFoundByIdException;
import com.jsp.rest.ets.security.RegistrationRequest;
import com.jsp.rest.ets.util.AppResponseBuilder;
import com.jsp.rest.ets.util.ResponseStructure;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;



@RestController
@AllArgsConstructor
public class UserController {

	private UserService userService;
	private AppResponseBuilder responseBuilder;
	
	@Operation(description = "This API endpoint is used to trigger the request to register the admin and send the otp to their mail to verify. In order to register the "
			+ " admin , the object of RegistrationRequest is attached along with it  ",responses = {
					@ApiResponse(responseCode = "202",description = "Admin Object Created"),
					@ApiResponse(responseCode = "500",description = "Internal Server Error",content = {
							@Content(schema = @Schema(anyOf = RuntimeException.class))
					})
			})
	
	@PostMapping("register/admins")
	public ResponseEntity<SimpleResponseStructure> saveAdmin(@RequestBody @Valid  RegistrationRequest registrationRequest) {
		 userService.registerUser(registrationRequest,UserRole.ADMIN);
		return responseBuilder.verifyMail(HttpStatus.ACCEPTED,"Accepted the request,please verify your email to register");
	}
	
	
	@Operation(description = "This API endpoint is used to trigger the request to register the HR and send the otp to their mail to verify. In order to register the "
			+ "hr, the object of RegistrationRequest is attached along with it ",responses = {
					@ApiResponse(responseCode = "202",description = "HR object Created"),
					@ApiResponse(responseCode ="500",description = "Internal Server Error" ,content = {
							@Content(schema = @Schema(anyOf = RuntimeException.class))
					})
			})
	@PostMapping("register/hrs")
	public ResponseEntity<SimpleResponseStructure> saveHR(@RequestBody @Valid  RegistrationRequest registrationRequest) {
		userService.registerUser(registrationRequest,UserRole.HR);
		return responseBuilder.verifyMail(HttpStatus.ACCEPTED,"Accepted the request,please verify your email to register");
	}
	
	
	@Operation(description = "This API endpoint is used to trigger the request to register the Trainer and send the otp to their mail to verify. In order to register the "
			+ "Trainer, the object of RegistrationRequest is attached along with it ",responses = {
					@ApiResponse(responseCode = "202",description = "Trainer object Created"),
					@ApiResponse(responseCode ="500",description = "Internal Server Error" ,content = {
							@Content(schema = @Schema(anyOf = RuntimeException.class))
					})
			})
	@PostMapping("/register/trainers")
	public ResponseEntity<SimpleResponseStructure> saveTrainer(@RequestBody @Valid  RegistrationRequest registrationRequest) {
		userService.registerUser(registrationRequest,UserRole.TRAINER);
		return responseBuilder.verifyMail(HttpStatus.ACCEPTED,"Accepted the request,please verify your email to register");
	}
	@Operation(description = "This API endpoint is used to trigger the request to register the Student and send the otp to their mail to verify. In order to register the "
			+ "Student, the object of RegistrationRequest is attached along with it ",responses = {
			@ApiResponse(responseCode = "202",description = "Student object Created"),
			@ApiResponse(responseCode ="500",description = "Internal Server Error" ,content = {
					@Content(schema = @Schema(anyOf = RuntimeException.class))
			})
	})
	@PostMapping("register/students")
	public ResponseEntity<SimpleResponseStructure> saveStudent(@RequestBody @Valid  RegistrationRequest registrationRequest) {
		userService.registerUser(registrationRequest,UserRole.STUDENT);
		return responseBuilder.verifyMail(HttpStatus.ACCEPTED,"Accepted the request,please verify your email to register");
	}

	@Operation(description = "This API endpoint is used to verify the otp which is sent to the mail of the User those who want to register for any type of role " +
			" once the otp will get verify after that they are able to register and their details will be saved in the database. " +
			" In order to verify the Object of OtpDtoRequest has to be attached with the url",responses = {
			@ApiResponse(responseCode = "200",description = "User registered successfully"),
			@ApiResponse(responseCode = "500",description = "Internal Server Error",content = {
					@Content(schema = @Schema(anyOf = RuntimeException.class))
			})
	})
	@PostMapping("/verify/users/register")
	public ResponseEntity<ResponseStructure<UserResponse>> verifyOtpToRegisterUser(@RequestBody OtpDtoRequest otpDtoRequest){
		UserResponse userResponse=userService.verifyOtpToRegisterUser(otpDtoRequest);
		return responseBuilder.success(HttpStatus.CREATED,"Otp verified, registered successfully",userResponse);
	}

	
	@Operation(description = "This API endpoint is meant for updating the Student details and store it in the specific database. In order to "
			+ "update the Student, the unique identifier as path variable and the object of StudentRequest is attached along "
			+ " with the url",responses = {
					@ApiResponse(responseCode = "302",description = "updated student records"),
					@ApiResponse(responseCode = "404",description = "failed to update the student",content = {
							@Content(schema = @Schema(anyOf = StudentNotFoundByIdException.class))
					})
			})
	@PutMapping("/students/{userId}")
	public ResponseEntity<ResponseStructure<StudentResponse>> updateStudent(@RequestBody StudentRequest studentRequest,@PathVariable String userId){
		StudentResponse studentResponse=userService.updateStudent(studentRequest,userId);
		return responseBuilder.success(HttpStatus.OK, "Student Updated", studentResponse);
	}
	
	
	@Operation(description = "This API endpoint is meant for updating the Student technology stack and store it in the specific database. In order to "
			+ "update the Student stack, the unique identifier as path variable and the stack  name is  attached as a ReuqestParam along "
			+ " with the url",responses = {
					@ApiResponse(responseCode = "302",description = "updated student stack"),
					@ApiResponse(responseCode = "404",description = "failed to update the student stack ",content = {
							@Content(schema = @Schema(anyOf = StudentNotFoundByIdException.class))
					})
			})
	@PatchMapping("/students/{userId}")
	public ResponseEntity<ResponseStructure<StudentResponse>>updateStudentStack(@PathVariable String userId,@RequestParam Stack stack){
		StudentResponse response=userService.updateStudentStack(userId,stack);
		return responseBuilder.success(HttpStatus.OK, "stack updated for the student", response);
	}
	
	
	@Operation(description = "This API endpoint is meant for updating the Trainer details and store it in the specific database. In order to "
			+ "update the Trainer, the unique identifier as path variable and the object of TrainerRequest is attached along "
			+ " with the url",responses = {
					@ApiResponse(responseCode = "302",description = "updated trainer records"),
					@ApiResponse(responseCode = "404",description = "failed to update the trainer",content = {
							@Content(schema = @Schema(anyOf = StudentNotFoundByIdException.class))
					})
			})
	@PutMapping("/trainers/{userId}")
	public ResponseEntity<ResponseStructure<UserResponse>> updateTrainer(@RequestBody TrainerRequest trainerRequest,@PathVariable String userId){
		UserResponse response=userService.updateTrainer(trainerRequest,userId);
		return responseBuilder.success(HttpStatus.OK, "Trainer updated", response);
	}
	


}
