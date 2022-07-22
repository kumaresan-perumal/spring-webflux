package com.kum.webflux;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public class EmployeeRepository {

	private static final Map<String, Employee> EMPLOYEE_DATA;

	static {
		EMPLOYEE_DATA = new HashMap<>();
		var employee1 = new Employee();
		employee1.setId("1");
		employee1.setName("Employee 1");

		var employee2 = new Employee();
		employee2.setId("2");
		employee2.setName("Employee 2");

		EMPLOYEE_DATA.put("1", employee1);
		EMPLOYEE_DATA.put("2", employee2);
		EMPLOYEE_DATA.put("3", employee1);
		EMPLOYEE_DATA.put("4", employee2);
		EMPLOYEE_DATA.put("5", employee1);
		EMPLOYEE_DATA.put("6", employee2);
		EMPLOYEE_DATA.put("7", employee1);
		EMPLOYEE_DATA.put("8", employee2);
		EMPLOYEE_DATA.put("9", employee1);
		EMPLOYEE_DATA.put("10", employee2);
		EMPLOYEE_DATA.put("11", employee1);
		EMPLOYEE_DATA.put("12", employee2);
		EMPLOYEE_DATA.put("13", employee1);
		EMPLOYEE_DATA.put("14", employee2);
		EMPLOYEE_DATA.put("15", employee2);

	}

	public Mono<Employee> findEmployeeById(String id) {
		return Mono.just(EMPLOYEE_DATA.get(id));
	}

	public Flux<Employee> findAllEmployees() {
		return Flux.fromIterable(EMPLOYEE_DATA.values());
	}

	public Mono<Employee> updateEmployee(Employee employee) {
		Employee existingEmployee = EMPLOYEE_DATA.get(employee.getId());
		if (existingEmployee != null) {
			existingEmployee.setName(employee.getName());
		}
		return Mono.just(existingEmployee);
	}
}