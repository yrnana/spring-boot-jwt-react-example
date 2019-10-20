package com.example.lgcns.controller;

import java.util.List;

import com.example.lgcns.model.Department;
import com.example.lgcns.model.Employee;
import com.example.lgcns.repository.DepartmentRepository;
import com.example.lgcns.repository.EmployeeRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * EmployeeController
 */
@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

	@Autowired
	private EmployeeRepository employeeRepository;

	@Autowired
	private DepartmentRepository departmentRepository;

	@GetMapping("")
	List<Employee> findAll() {
		return employeeRepository.findAll();
	}

	@GetMapping("/{id}")
	Employee findById(@PathVariable Integer id) {
		return employeeRepository.findById(id).orElse(null);
	}

	@GetMapping("/{id}/department")
	Department findDeparmentById(@PathVariable Integer id) {
		/**
		 * 지연 로딩을 이용한 쿼리
		 * Employee emp = employeeRepository.findById(id).orElse(null);
		 * if (emp == null) return null;
		 * return emp.getDepartment();
		 */
		return departmentRepository.findByEmployeeId(id).orElse(null);
	}

	@PostMapping("")
	Employee save(@RequestBody Employee input) {
		input.setEmployeeId(employeeRepository.getNextId());
		return employeeRepository.save(input);
	}
}