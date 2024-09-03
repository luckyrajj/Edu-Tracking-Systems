package com.jsp.rest.ets.rating;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.jsp.rest.ets.exception.RatingNotFoundByIdException;
import com.jsp.rest.ets.user.UserService;
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
public class RatingController {
	
	private AppResponseBuilder responseBuilder;
	private UserService userService;

	@Operation(description = "This API endpoint is meant for updating the Student ratings and store it in the specific database. In order to "
			+ "update the Student ratings, the unique identifier as path variable and the object of RatingRequest is attached along "
			+ " with the url",responses = {
			@ApiResponse(responseCode = "302",description = "updated student ratings"),
			@ApiResponse(responseCode = "404",description = "failed to update the student ratings",content = {

					@Content(schema = @Schema(anyOf = RatingNotFoundByIdException.class))
			})
	})
	@PutMapping("/ratings/{ratingId}")
	public ResponseEntity<ResponseStructure<RatingResponse>> updateStudentRating(@RequestBody @Valid RatingRequest ratingRequest ,@PathVariable String ratingId){
		RatingResponse response=userService.updateStudentRating(ratingRequest,ratingId);
		return responseBuilder.success(HttpStatus.OK, "rating updated", response);
	}

	@Operation(description = "This API endpoint is meant for fetching the list of ratings of the student . In order to "
			+ "fetch  the list of  Student ratings, the unique identifier as path variable  is attached along "
			+ " with the url",responses = {
			@ApiResponse(responseCode = "302",description = "list of student ratings found"),
			@ApiResponse(responseCode = "404",description = "failed to find the list of ratings",content = {
					@Content(schema = @Schema(anyOf = RatingNotFoundByIdException.class))
			})

	})
	@GetMapping("/students/{studentId}/ratings")
	public ResponseEntity<ResponseStructure<List<RatingResponse>>> getStudentRatings(@PathVariable String studentId) {
		List<RatingResponse> ratings= userService.getStudentRatings(studentId);
		return responseBuilder.success(HttpStatus.FOUND, "rating for student found", ratings);
	}

}
