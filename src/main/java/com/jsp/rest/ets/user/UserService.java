package com.jsp.rest.ets.user;



import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Random;

import com.jsp.rest.ets.config.RandomGenerator;
import com.jsp.rest.ets.exception.*;
import com.jsp.rest.ets.security.JWTService;
import com.jsp.rest.ets.util.CacheHelper;
import com.jsp.rest.ets.util.MailMessageSender;
import com.jsp.rest.ets.util.MessageModel;
import com.jsp.rest.ets.util.SimpleResponseStructure;
import jakarta.mail.MessagingException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CachePut;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.jsp.rest.ets.rating.RatingMapper;
import com.jsp.rest.ets.rating.RatingRepository;
import com.jsp.rest.ets.rating.RatingRequest;
import com.jsp.rest.ets.rating.RatingResponse;
import com.jsp.rest.ets.security.RegistrationRequest;

import lombok.AllArgsConstructor;

@Slf4j
@Service
@AllArgsConstructor
public class UserService {

	private UserRepository userRepository;
	private UserMapper userMapper;
	private RatingMapper ratingMapper;
	private RatingRepository ratingRepository;
	private MailMessageSender javaMailSender;
	private Random randomGenerator;
	private CacheHelper cacheHelper;
	private AuthenticationManager authenticationManager;
	private JWTService jwtService;


	public void registerUser(RegistrationRequest registrationRequest, UserRole role) {
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
			int otp=randomGenerator.nextInt(100000,999999);
			cacheHelper.userCache(user);
			cacheHelper.otpCache(otp,user.getEmail());
            try {
                sendVerificationOtpToUser(user.getEmail(),otp);
            } catch (MessagingException e) {
              log.info("Messaging Exception Occurred");
            }





        }
	}
	
	public UserResponse updateTrainer(TrainerRequest trainerRequest,String userId) {
		return userRepository.findById(userId).map(user->{
			userMapper.mapToTrainerEntity(trainerRequest, (Trainer)user);
			user=userRepository.save(user);
			return userMapper.mapToTrainerResponse((Trainer)user);
		}).orElseThrow(()-> new TrainerNotFoundByIdException("failed to update the trainer"));


	}
	public StudentResponse updateStudent(StudentRequest studentRequest, String userId) {
		
		return userRepository.findById(userId).map(user->{
			userMapper.mapToStudentEntity(studentRequest, (Student)user);
			user=userRepository.save(user);
			return userMapper.mapToStudentResponse((Student)user);
		}).orElseThrow(()->new StudentNotFoundByIdException("failed to update the student"));
	}
	public StudentResponse updateStudentStack(String userId, Stack stack) {

		return userRepository.findById(userId).map(user->{
			Student student=(Student)user;

			student.setStack(stack);
			user=userRepository.save(student);
			return userMapper.mapToStudentResponse(student);
		}).orElseThrow(()->new StudentNotFoundByIdException("failed to update the teck stack"));

	}
	public RatingResponse updateStudentRating(RatingRequest ratingRequest,String ratingId) {

		return ratingRepository.findById(ratingId).map(rating->{
			ratingMapper.mapToRating(ratingRequest, rating);
			rating=ratingRepository.save(rating);
			return ratingMapper.mapToRatingResponse(rating);
		}).orElseThrow(()-> new RatingNotFoundByIdException("failed to update the rating"));
	}

	public List<RatingResponse> getStudentRatings(String studentId) {

		return userRepository.findById(studentId).map(user->{
			return ((Student)user).getRatings().stream()
					.map(rating -> ratingMapper.mapToRatingResponse(rating))
					.toList();
		}).orElseThrow(()-> new StudentNotFoundByIdException("failed to find the student"));

	}

	private void sendVerificationOtpToUser(String email,int otp) throws MessagingException {
		String message="<!DOCTYPE html>\n" +
				"<html lang=\"en\">\n" +
				"<head>\n" +
				"    <meta charset=\"UTF-8\">\n" +
				"    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
				"    <title>Document</title>\n" +
				"</head>\n" +
				"<body>\n" +
				"    <h2>Edu-Tracking-Systems</h2>\n" +
				"    <h3>Please Verify the email by using the otp below:</h3>\n" +
				"    <h4>The otp to verify the email:</h4>\n" +
				"	<h4>"+otp+"</h4>" +
				"</body>\n" +
				"</html>";
		MessageModel messageModel=new MessageModel();
		messageModel.setTo(email);
		messageModel.setSendDate(new Date());
		messageModel.setSubject("Verify your email for register to Edu-Tracking-Systems");
		messageModel.setText(message);
		javaMailSender.sendMail(messageModel);

	}

	public UserResponse verifyOtpToRegisterUser(OtpDtoRequest otpRequest) {
		Integer otp=cacheHelper.getOtpToVerify(otpRequest.getEmail());
		if(!otp.equals(otpRequest.getOtp())){
		throw new InvalidOtpException("otp is incorrect or otp is expire, please try again");
		}
		User user=cacheHelper.getRegisteringUser(otpRequest.getEmail());
		if(!user.getEmail().equals(otpRequest.getEmail()))
		throw new RegistrationSessionExpired("Registartion Session Expired, Please try again");


		user = userRepository.save(user);
		return userMapper.mapToUserResponse(user);
	}


    public String loginUser(LoginRequest loginRequest) {

		 UsernamePasswordAuthenticationToken authenticationToken=new UsernamePasswordAuthenticationToken(loginRequest.getEmail(),loginRequest.getPassword());
		 Authentication authentication = authenticationManager.authenticate(authenticationToken);

		 if(authentication.isAuthenticated()){

			return userRepository.findByEmail(loginRequest.getEmail()).map(user ->
					 jwtService.createJwtToken(user.getUserId(), user.getEmail(),user.getRole().name()))
					.orElseThrow(()->new UsernameNotFoundException("Invalid credentials"));



		 }

		 return null;

    }
}
