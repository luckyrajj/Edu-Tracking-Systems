package com.jsp.rest.ets.user;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
	
	@Operation(description = "This API endpoint is used to register the admin and store their information in the database. In order to register the "
			+ " admin , the object of RegistraionRequest is attached along with it  ",responses = {
					@ApiResponse(responseCode = "200",description = "Admin Object Created"),
					@ApiResponse(responseCode = "500",description = "Internal Server Error",content = {
							@Content(schema = @Schema(anyOf = RuntimeException.class))
					})
			})
	
	@PostMapping("/admins/register")
	public ResponseEntity<ResponseStructure<UserResponse>> saveAdmin(@RequestBody @Valid  RegistrationRequest registrationRequest) {
		UserResponse adminResponse=userService.saveUser(registrationRequest,UserRole.ADMIN);
		return responseBuilder.success(HttpStatus.CREATED,"Admin created Successfully", adminResponse);
	}
	
	
	@Operation(description = "This API endpoint is used to register the HR and store their information in the database. In order to register the "
			+ "hr, the object of RegistratonRequest is attached along with it ",responses = {
					@ApiResponse(responseCode = "200",description = "HR object Created"),
					@ApiResponse(responseCode ="500",description = "Internal Server Error" ,content = {
							@Content(schema = @Schema(anyOf = RuntimeException.class))
					})
			})
	@PostMapping("/hrs/register")
	public ResponseEntity<ResponseStructure<UserResponse>>saveHr(@RequestBody @Valid RegistrationRequest registrationRequest){
		UserResponse hrResponse=userService.saveUser(registrationRequest,UserRole.HR);
		return responseBuilder.success(HttpStatus.CREATED, "Hr created Successfully", hrResponse);
	}
	
	
	@Operation(description = "This API endpoint is used to register the Trainer and store their information in the database. In order to register the "
			+ "Trainer, the object of RegistratonRequest is attached along with it ",responses = {
					@ApiResponse(responseCode = "200",description = "Trainer object Created"),
					@ApiResponse(responseCode ="500",description = "Internal Server Error" ,content = {
							@Content(schema = @Schema(anyOf = RuntimeException.class))
					})
			})
	@PostMapping("/trainers/register")
	public ResponseEntity<ResponseStructure<UserResponse>> saveTrainer(@RequestBody RegistrationRequest registrationRequest){
		UserResponse response=userService.saveUser(registrationRequest,UserRole.TRAINER);
		return responseBuilder.success(HttpStatus.CREATED, "Trainer created successfully", response);
	}
	
	
	@Operation(description = "This API endpoint is used to register the Student and store their information in the database. In order to register the "
			+ "Student, the object of RegistrationRequest is attached along with it ",responses = {
					@ApiResponse(responseCode = "200",description = "Student object Created"),
					@ApiResponse(responseCode ="500",description = "Internal Server Error" ,content = {
							@Content(schema = @Schema(anyOf = RuntimeException.class))
					})
			})
	@PostMapping("/students/register")
	public ResponseEntity<ResponseStructure<UserResponse>> saveStudent(@RequestBody RegistrationRequest registrationRequest){
		UserResponse response=userService.saveUser(registrationRequest, UserRole.STUDENT);
		return responseBuilder.success(HttpStatus.CREATED, "Student created successfully", response);
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
