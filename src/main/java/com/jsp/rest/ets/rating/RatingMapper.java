package com.jsp.rest.ets.rating;

import org.springframework.stereotype.Component;

@Component
public class RatingMapper {

	public Rating mapToRating(RatingRequest ratingRequest, Rating rating) {
		rating.setRating(ratingRequest.getRating());
		rating.setFeedBack(ratingRequest.getFeedBack());
		return rating;
	}
	
	public RatingResponse mapToRatingResponse(Rating rating) {
		RatingResponse ratingResponse=new RatingResponse();
		ratingResponse.setRating(rating.getRating());
		ratingResponse.setFeedBack(rating.getFeedBack());
		ratingResponse.setSubject(rating.getSubject());
		return ratingResponse;
	}
}
