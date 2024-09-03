package com.jsp.rest.ets.batch;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.jsp.rest.ets.config.GenerateSequenceId;
import com.jsp.rest.ets.user.Student;
import com.jsp.rest.ets.user.Subject;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "batches")
@EntityListeners(AuditingEntityListener.class)
public class Batch {
	
	@Id
	@GenerateSequenceId
	@Column(name = "batch_id")
	private String batchId;
	
	@Column(name = "title")
	private String title;
	
	@Column(name = "subjects")
	private List<Subject> subjects;
	
	@Column(name = "starting_date")
	private LocalDate startingDate;
	
	
	@Column(name = "closed_date")
	private LocalDate closedDate;
	
	@Column(name="begins_at")
	private LocalTime beginsAt;
	
	@Column(name = "ends_at")
	private LocalTime endsAt;
	
	@Column(name = "batch_status")
	@Enumerated(EnumType.STRING)
	private BatchStatus batchStatus;
	
	
	@ManyToMany
	private List<Student> students;
	
	
}
