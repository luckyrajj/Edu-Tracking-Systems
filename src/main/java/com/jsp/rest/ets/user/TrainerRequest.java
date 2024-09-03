package com.jsp.rest.ets.user;

import java.util.*;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TrainerRequest extends UserRequest {

	private List<Subject> subjects;
}
