package demo.boot.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import demo.boot.dao.DoctorDao;
import demo.boot.exceptions.DoctorNotFoundException;
import demo.boot.model.Doctor;
import demo.boot.service.Producer;
import jakarta.validation.Valid;

@RestController
public class DoctorController {
	private final DoctorDao doctorDao;

	@Autowired
	public DoctorController(DoctorDao doctorDao) {
		this.doctorDao = doctorDao;
	}

	@Autowired
	Producer producer;

	@GetMapping("/")
	public ResponseEntity<List<Doctor>> getAllDoctors() {
		List<Doctor> getalldoctors = doctorDao.getAllDoctors();
		producer.sendMsgToTopic(getalldoctors);
		for (Doctor doctor : getalldoctors) {
			doctor.add(linkTo(methodOn(DoctorController.class).getAllDoctors()).withSelfRel());
			doctor.add(linkTo(methodOn(DoctorController.class).getAllDoctors()).slash("id").withRel("Doctor"));

		}
		return ResponseEntity.ok(getalldoctors);

	}

	@GetMapping("/{id}")
	public EntityModel<Optional<Doctor>> getDoctorById(@PathVariable Long id) {
		Optional<Doctor> doctorbyid = doctorDao.getByDoctorId(id);
		Link getlinks = linkTo(DoctorController.class).slash(id).withSelfRel();
		if (doctorbyid.isEmpty()) {
			throw new DoctorNotFoundException("Doctor with id" + id + "not found");
		}
		return EntityModel.of(doctorbyid, getlinks);
	}

	@PostMapping
	public ResponseEntity<Doctor> createDoctor(@Valid @RequestBody Doctor doctor) {
		Doctor doctor1 = doctorDao.createDoctor(doctor);
		return ResponseEntity.status(HttpStatus.CREATED).body(doctor1);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Doctor> updateDoctor(@PathVariable Long id, @Valid @RequestBody Doctor updoctor) {
		Optional<Doctor> existingdoctorbyid = doctorDao.getByDoctorId(id);
		if (existingdoctorbyid.isPresent()) {
			Doctor doctor = existingdoctorbyid.get();
			doctor.setDoctorName(updoctor.getDoctorName());
			doctor.setHospital(updoctor.getHospital());
			Doctor updoctor1 = doctorDao.updateDoctor(doctor);
			return ResponseEntity.ok(updoctor1);

		} else {
			return ResponseEntity.notFound().build();
		}

	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Doctor> deleteDoctor(@PathVariable Long id) {
		Optional<Doctor> doctorbyid = doctorDao.getByDoctorId(id);
		if (doctorbyid.isPresent()) {
			doctorDao.deleteDoctor(doctorbyid.get());
			return ResponseEntity.noContent().build();
		} else {
			return ResponseEntity.notFound().build();
		}
	}

}
