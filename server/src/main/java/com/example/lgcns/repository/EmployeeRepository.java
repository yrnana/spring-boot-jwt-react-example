package com.example.lgcns.repository;

import com.example.lgcns.model.Employee;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * EmployeeRepository
 */
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

	@Query("SELECT MAX(e.employeeId) + 1 FROM Employee e")
	public Integer getNextId();
}