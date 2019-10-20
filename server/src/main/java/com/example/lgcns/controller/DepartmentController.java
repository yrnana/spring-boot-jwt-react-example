package com.example.lgcns.controller;

import java.util.List;

import com.example.lgcns.model.Department;
import com.example.lgcns.model.Employee;
import com.example.lgcns.repository.DepartmentRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * DepartmentController
 */
@RestController
@RequestMapping("/api/departments")
public class DepartmentController {

	@Autowired
	private DepartmentRepository departmentRepository;

	@GetMapping("/{id}/employees")
	public List<Employee> findEmployeesById(@PathVariable Integer id) {
		Department dept = departmentRepository.findById(id).orElse(null);
		if (dept == null)
			return null;
		return dept.getEmployees();
	}
}