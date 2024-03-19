package patientallocation.patient.allocation.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import patientallocation.patient.allocation.entity.Doctor;
import patientallocation.patient.allocation.entity.Patient;
import patientallocation.patient.allocation.repository.DoctorsRepository;
import patientallocation.patient.allocation.repository.PatientsRepository;

@Service
public class DoctorServiceImpl {

	@Autowired
	private DoctorsRepository doctorsRepository;

	@Autowired
	private PatientsRepository patientsRepository;

	public String addDoctor(Doctor doctor) {
		if (isValidDoctor(doctor)) {
			doctorsRepository.save(doctor);
			return "Doctor added successfully";
		} else {
			return "Invalid doctor details";
		}
	}

	public boolean removeDoctor(Long doctorId) {
		if (doctorsRepository.existsById(doctorId)) {
			doctorsRepository.deleteById(doctorId);
			return true;
		}
		return false;
	}

	@SuppressWarnings("unused")
	public List<Doctor> getSuggestedDoctors(Long patientId) {

		Patient patient = patientsRepository.findById(patientId).orElse(null);
		String city = patient.getCity();
		String symptom = patient.getSymptom();
		String speciality = getSpeciality(symptom);
		List<Doctor> doctorsInCity = doctorsRepository.findByCity(city);
		List<Doctor> suggestedDoctors = doctorsRepository.findByCityAndSpeciality(city, speciality);

		if (patient == null) {
			throw new IllegalArgumentException("Patient not found with the provided ID");
		}

		if (!isValidCity(city)) {
			return createListWithMessage("We are still waiting to expand to your location");
		}

		if (doctorsInCity.isEmpty()) {
			return createListWithMessage("There isn't any doctor present at your location");
		}

		if (suggestedDoctors.isEmpty()) {
			return createListWithMessage("There isn't any doctor present at your location for your symptom");
		}

		return suggestedDoctors;

	}

	private String getSpeciality(String symptom) {
		switch (symptom) {
		case "Arthritis":
		case "Backpain":
		case "Tissue injuries":
			return "Orthopedic";
		case "Dysmenorrhea":
			return "Gynecology";
		case "Skin infection":
		case "Skin burn":
			return "Dermatology";
		case "Ear pain":
			return "ENT specialist";
		default:
			return "";
		}
	}

	private boolean isValidDoctor(Doctor doctor) {
		return doctor.getName().length() >= 3 && isValidCity(doctor.getCity()) && doctor.getCity().length() <= 20
				&& isValidEmail(doctor.getEmail()) && doctor.getPhoneNumber().length() >= 10;
	}

	private boolean isValidEmail(String email) {
		return email.matches("^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,}$");
	}

	private boolean isValidCity(String city) {
		return city != null && (city.equals("Delhi") || city.equals("Noida") || city.equals("Faridabad"));
	}

	private List<Doctor> createListWithMessage(String message) {
		List<Doctor> list = new ArrayList<>();
		Doctor dummyDoctor = new Doctor();
		dummyDoctor.setName(message);
		list.add(dummyDoctor);
		return list;
	}

}
