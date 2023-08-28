package demo.boot.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import demo.boot.model.Doctor;
import demo.boot.service.Consumer;
//import demo.boot.service.Consumer;
import demo.boot.service.IDoctorManagementService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import jakarta.validation.Valid;

@RestController
public class DoctorController {
	
	@Autowired
	IDoctorManagementService doctorManagementService;

	@Autowired	
	Consumer consumer;

	@GetMapping
	@CircuitBreaker(name = "DoctorService", fallbackMethod = "defaultMsg")
	public ResponseEntity<List<Doctor>> getAllDoctors() {
		return doctorManagementService.getAllDoctors();
	}

	@GetMapping("/{id}")
	@CircuitBreaker(name = "DoctorService", fallbackMethod = "defaultMsg")
	public ResponseEntity<?> getDoctorById(@PathVariable Long id) {
		return doctorManagementService.getByDoctorId(id);

	}

	@PostMapping
	@CircuitBreaker(name = "DoctorService", fallbackMethod = "defaultMsg")
	public ResponseEntity<?> createDoctor(@Valid @RequestBody Doctor doctor) {
		return doctorManagementService.createDoctor(doctor);
	}

	@PutMapping("/{id}")
	@CircuitBreaker(name = "DoctorService", fallbackMethod = "defaultMsg")
	public ResponseEntity<?> updateDoctor(@Valid @PathVariable Long id, @RequestBody Doctor doctor) {
		return doctorManagementService.updateDoctor(id, doctor);
	}

	@DeleteMapping("/{id}")
	@CircuitBreaker(name = "DoctorService", fallbackMethod = "defaultMsg")
	public ResponseEntity<?> deleteDoctor(@PathVariable Long id) {
		return doctorManagementService.deleteDoctor(id);
	}

	public ResponseEntity<String> defaultMsg(Exception e) {
		return ResponseEntity.ok("can't reach to the page...");

	}
}
