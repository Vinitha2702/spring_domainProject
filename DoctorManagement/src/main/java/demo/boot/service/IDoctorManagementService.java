package demo.boot.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import demo.boot.model.Doctor;

public interface IDoctorManagementService {
	ResponseEntity<List<Doctor>> getAllDoctors();
	ResponseEntity<Doctor> getByDoctorId(Long id);
	ResponseEntity<Doctor> createDoctor(Doctor doctor);
	ResponseEntity<Doctor> updateDoctor(Long id,Doctor doctor);
	ResponseEntity<Doctor> deleteDoctor(Long id);
}
