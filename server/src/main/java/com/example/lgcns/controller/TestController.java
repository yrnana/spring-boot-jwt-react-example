package com.example.lgcns.controller;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.lgcns.model.Department;
import com.example.lgcns.model.Employee;
import com.example.lgcns.service.TestService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * TestController
 */
@RestController
@RequestMapping("/api/test")
public class TestController {

	@GetMapping("/list")
	public List<Employee> employee() {
		List<Employee> employees = Collections.singletonList(new Employee());
		return employees;
	}

	@GetMapping("/map")
	public Map<String, Object> map() {
		Map<String, Object> map = new HashMap<>();
		map.put("test1", "hello");
		map.put("test2", new Employee());
		return map;
	}

	// http://wonwoo.ml/index.php/post/1834
	@PostMapping("/param")
	public Map<String, Object> param(@RequestBody String map) throws JsonMappingException, JsonProcessingException {
		ObjectMapper objectMapper = new ObjectMapper();
		Map<String, Object> json = objectMapper.readValue(map, new TypeReference<Map<String, Object>>() {
		});
		System.out.println(map);
		System.out.println(json.get("tct"));
		System.out.println(json.get("te"));
		return json;
	}

	@PostMapping("/param2")
	public Map<String, Object> param2(@RequestBody Map<String, Object> map)
			throws JsonMappingException, JsonProcessingException {
		System.out.println(map);
		System.out.println(map.get("tct"));
		System.out.println(map.get("te"));
		return map;
	}

	@Autowired
	private TestService testService;

	@GetMapping("/service")
	public List<Department> service() {
		return testService.retrieveDepartment();
	}

	// https://www.baeldung.com/jackson-object-mapper-tutorial
}