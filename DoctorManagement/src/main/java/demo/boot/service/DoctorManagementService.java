package demo.boot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import demo.boot.model.Doctor;

@Service
public class DoctorManagementService implements IDoctorManagementService {

	@Autowired
	RestTemplate restTemplate;

	private String getBaseUrl() {

		return "http://DoctorService";
	}

	@Override
	public ResponseEntity<List<Doctor>> getAllDoctors() {
		ParameterizedTypeReference<List<Doctor>> responseType = new ParameterizedTypeReference<List<Doctor>>() {
		};
		ResponseEntity<List<Doctor>> responseEntity = restTemplate.exchange(getBaseUrl(), HttpMethod.GET, null,
				responseType);
		return responseEntity;

	}

	@Override
	public ResponseEntity<Doctor> getByDoctorId(Long id) {
		return restTemplate.getForEntity(getBaseUrl() + "/" + id, Doctor.class);
	}

	@Override
	public ResponseEntity<Doctor> createDoctor(Doctor doctor) {
		return restTemplate.postForEntity(getBaseUrl(), doctor, Doctor.class);
	}

	@Override
	public ResponseEntity<Doctor> updateDoctor(Long id, Doctor doctor) {
		return restTemplate.exchange(getBaseUrl() + "/" + id, HttpMethod.PUT, new HttpEntity<>(doctor), Doctor.class);
	}

	@Override
	public ResponseEntity<Doctor> deleteDoctor(Long id) {
		restTemplate.delete(getBaseUrl() + "/" + id);
		return ResponseEntity.ok().build();
	}

}
