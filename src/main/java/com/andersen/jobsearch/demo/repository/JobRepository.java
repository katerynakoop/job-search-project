package com.andersen.jobsearch.demo.repository;

import java.util.List;

import javax.persistence.NamedNativeQuery;
import javax.persistence.NamedQuery;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.andersen.jobsearch.demo.entity.Company;
import com.andersen.jobsearch.demo.entity.Employer;
import com.andersen.jobsearch.demo.entity.Job;
import com.andersen.jobsearch.demo.entity.JobStatus;


/**
 * Repository Interface for {@link Job} class.
 */

@Repository
public interface JobRepository extends JpaRepository<Job, Long>
{
	List<Job> findByEmployer(Employer employer);
	
	List<Job> findByJobTitleContainingIgnoreCase(String jobTitle);
	
	List<Job> findByLocationIgnoreCase(String location);
	
	List<Job> findByCompany(Company company);
	
	List<Job> findByJobTitleContainingIgnoreCaseAndLocationIgnoreCase(String jobTitle, String location);
}