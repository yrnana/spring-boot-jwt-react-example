package com.example.lgcns.repository;

import java.util.Optional;

import com.example.lgcns.model.Department;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * DepartmentRepository
 */
public interface DepartmentRepository extends JpaRepository<Department, Integer> {

	@Query("SELECT d FROM Employee e LEFT OUTER JOIN e.department d WHERE e.employeeId = :id")
	Optional<Department> findByEmployeeId(Integer id);
}