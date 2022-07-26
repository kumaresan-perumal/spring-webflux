package com.kum.webflux;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

	private final EmployeeRepository employeeRepository;

	public EmployeeController(EmployeeRepository employeeRepository) {
		this.employeeRepository = employeeRepository;
	}

	@GetMapping("/{id}")
	private Mono<Employee> getEmployeeById(@PathVariable String id) {
		return employeeRepository.findEmployeeById(id);
	}

	// NOTE: flux non-blocking code
	@GetMapping(produces = MediaType.TEXT_EVENT_STREAM_VALUE)
	private Flux<Employee> getAllEmployees() throws InterruptedException {
		Thread.sleep(100);
		return employeeRepository.findAllEmployees();
	}

	@PostMapping("/update")
	private Mono<Employee> updateEmployee(@RequestBody Employee employee) {
		return employeeRepository.updateEmployee(employee);
	}

}