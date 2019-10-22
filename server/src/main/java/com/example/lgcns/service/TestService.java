package com.example.lgcns.service;

import java.util.List;

import javax.transaction.Transactional;

import com.example.lgcns.model.Department;
import com.example.lgcns.repository.DepartmentRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * TestService
 */
@Service
public class TestService {

	@Autowired
	private DepartmentRepository deparmentRepository; // DAO

	@Transactional
	public List<Department> retrieveDepartment() {
		return deparmentRepository.findAll();
	}
}