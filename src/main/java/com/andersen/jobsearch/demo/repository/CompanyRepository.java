package com.andersen.jobsearch.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.andersen.jobsearch.demo.entity.Company;

/**
 * Repository Interface for {@link Company} class.
 */
@Repository
public interface CompanyRepository extends JpaRepository<Company, Long>
{
	Company findByCodeEDRPOU(Long codeEDRPOU);
	Company findByName(String name);
	//Company findCompanyByNameAndCodeEDRPOU(String name, Long codeEDRPOU);
}