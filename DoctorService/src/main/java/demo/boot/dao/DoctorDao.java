package demo.boot.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import demo.boot.model.Doctor;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

@Repository
@Transactional

public class DoctorDao {
	@PersistenceContext
	private EntityManager entityManager;
	
	public List<Doctor>  getAllDoctors(){
		return entityManager.createQuery("SELECT d FROM Doctor d",Doctor.class).getResultList();
	}
	public Optional<Doctor> getByDoctorId(Long id){
		return Optional.ofNullable(entityManager.find(Doctor.class, id));
	}
	public Doctor createDoctor(Doctor doctor) {
		 entityManager.persist(doctor);
		 return doctor;
	}
	public Doctor updateDoctor(Doctor doctor) {
		entityManager.merge(doctor);
		return doctor;
	}
	public void deleteDoctor(Doctor doctor) {
		entityManager.remove(doctor);
	}
	
}
