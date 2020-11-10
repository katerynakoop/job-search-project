package com.andersen.jobsearch.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.andersen.jobsearch.demo.entity.Role;
import com.andersen.jobsearch.demo.entity.User;
import com.andersen.jobsearch.demo.entity.UserRole;


/**
 * Repository Interface for {@link Role} class.
 */

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer>
{
	Role findByRole(UserRole role);
}
