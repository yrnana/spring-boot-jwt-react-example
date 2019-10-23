package com.example.lgcns;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.example.lgcns.model.Employee;
import com.example.lgcns.repository.EmployeeRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class AppRunner implements CommandLineRunner {

	@Autowired
	private EmployeeRepository employeeRepository;

	@Override
	public void run(String... args) throws Exception {
		List<Object[]> obj = employeeRepository.getObject();
		System.out.println(obj.get(0)[0]);

		List<?> map = employeeRepository.getMap();
		System.out.println(map.get(0));

		List<Object[]> list = employeeRepository.getGroup();
		Map<String, Object> result = list.stream().collect(Collectors.toMap(x -> x[0].toString(), x -> x[1]));
		System.out.println(result);

		List<Object[]> list2 = employeeRepository.getRank();
		list2.forEach(x -> {
			System.out.printf("%d, %d, %f, %s\n", x[0], x[1], x[2], x[3]);
		});

		List<Employee> employees = employeeRepository.getEmployees();
		employees.forEach(System.out::println);

		List<Employee> employees2 = employeeRepository.getLike("AD_");
		employees2.forEach(System.out::println);
	}

}

// https://www.baeldung.com/jackson-object-mapper-tutorial
// https://www.baeldung.com/foreach-java
// https://www.baeldung.com/java-maps-streams
