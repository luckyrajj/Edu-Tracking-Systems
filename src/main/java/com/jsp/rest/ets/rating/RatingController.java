package com.jsp.rest.ets.rating;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.jsp.rest.ets.user.UserService;
import com.jsp.rest.ets.util.AppResponseBuilder;
import com.jsp.rest.ets.util.ResponseStructure;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
public class RatingController {
	
	private AppResponseBuilder responseBuilder;
	private UserService userService;

	
	@PutMapping("/ratings/{ratingId}")
	public ResponseEntity<ResponseStructure<RatingResponse>> updateStudentRating(@RequestBody @Valid RatingRequest ratingRequest ,@PathVariable String ratingId){
		RatingResponse response=userService.updateStudentRating(ratingRequest,ratingId);
		return responseBuilder.success(HttpStatus.OK, "rating updated", response);
	}

	

	
	@GetMapping("/students/{studentId}/ratings")
	public ResponseEntity<ResponseStructure<List<RatingResponse>>> getStudentRatings(@PathVariable String studentId) {
		List<RatingResponse> ratings= userService.getStudentRatings(studentId);
		return responseBuilder.success(HttpStatus.FOUND, "rating for student found", ratings);
	}

}
