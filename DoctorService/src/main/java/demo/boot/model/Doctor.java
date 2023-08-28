	package demo.boot.model;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.springframework.hateoas.RepresentationModel;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Size;

@Entity
public class Doctor extends RepresentationModel<Doctor>{
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@Size(min=3,message="name should have  atleast 3 characters")
	private String doctorName;
	private int experience;
	private String hospital;
	private String consultancy;
	
	@ManyToOne
	@JoinColumn(name="specialist_id")
	@Cascade(CascadeType.ALL)
	private Specialist specialist;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDoctorName() {
		return doctorName;
	}

	public void setDoctorName(String doctorName) {
		this.doctorName = doctorName;
	}

	public int getExperience() {
		return experience;
	}

	public void setExperience(int experience) {
		this.experience = experience;
	}

	public String getHospital() {
		return hospital;
	}

	public void setHospital(String hospital) {
		this.hospital = hospital;
	}

	public String getConsultancy() {
		return consultancy;
	}

	public void setConsultancy(String consultancy) {
		this.consultancy = consultancy;
	}

	public Specialist getSpecialist() {
		return specialist;
	}

	public void setSpecialist(Specialist specialist) {
		this.specialist = specialist;
	}
	
	
}
