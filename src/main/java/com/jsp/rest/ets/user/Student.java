package com.jsp.rest.ets.user;

import java.time.Year;
import java.util.List;

import  com.jsp.rest.ets.batch.Batch;

import com.jsp.rest.ets.rating.Rating;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "students")
@Getter
@Setter
public class Student extends User {

	@Column(name = "degree")
	private String degree;
	
	@Column(name = "stream")
	private String stream;
	
	@Column(name = "yop")
	private Year   yop;
	
	@Column(name = "degree_percentage")
	private double degreePercentage;
	
	@Column(name = "tenth_percentage")
	private double tenthPercentage;
	
	@Column(name = "twelveth_percentage")
	private double twelvethPercentage;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "stacks")
	private Stack stack;
	
	@Column(name = "ratings")
	@OneToMany(mappedBy = "student",cascade = CascadeType.REMOVE)
	private List<Rating> ratings;
	
	@Column(name = "batches")
	@ManyToMany(mappedBy = "students")
	private List<Batch> batches;
	
}
