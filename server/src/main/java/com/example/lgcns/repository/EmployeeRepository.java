package com.example.lgcns.repository;

import java.util.List;

import com.example.lgcns.model.Employee;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * EmployeeRepository
 */
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

	@Query("SELECT MAX(e.employeeId) + 1 FROM Employee e")
	public Integer getNextId();

	@Query("SELECT e.employeeId, e.managerId FROM Employee e")
	public List<Object[]> getObject();

	@Query("SELECT new map(e.employeeId, e.managerId) FROM Employee e")
	public List<?> getMap();

	@Query("SELECT e.jobId, AVG(e.salary) FROM Employee e GROUP BY e.jobId")
	public List<Object[]> getGroup();

	@Query(value = "SELECT RANK() OVER (PARTITION BY department_id ORDER BY salary DESC), department_id, salary, first_name FROM employees ORDER BY department_id ASC, salary DESC", nativeQuery = true)
	public List<Object[]> getRank();

	@Query("SELECT e FROM Employee e WHERE e.hireDate BETWEEN '1987-01-01' AND '1987-12-31'")
	public List<Employee> getEmployees();

	@Query("SELECT e FROM Employee e WHERE e.jobId LIKE :jobId%")
	public List<Employee> getLike(String jobId);
}