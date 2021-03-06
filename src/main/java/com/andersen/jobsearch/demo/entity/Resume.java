package com.andersen.jobsearch.demo.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * Simple JavaBean domain object that represents Resume.
 */

@Entity
@Table(name="resumes")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Resume
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected Long id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="employee_id")
	private Employee employee;
	
	@Column(name="desired_position")
	private String desiredPosition;
	
	@Column(name="city")
	private String city;
	
	@Column(name="work_experience")
	private String workExperience;
	
	@Column(name="skills")
	private String skills;
	
	@ManyToMany(mappedBy = "resumes")
	private List<Job> jobs;
}
