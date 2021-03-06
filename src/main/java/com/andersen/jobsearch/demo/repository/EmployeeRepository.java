package com.andersen.jobsearch.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.andersen.jobsearch.demo.entity.Employee;
import com.andersen.jobsearch.demo.entity.User;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long>
{
	Employee findByUser(User user);
}
