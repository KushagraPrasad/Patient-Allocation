package PatientAllocation.Patient.Allocation.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import PatientAllocation.Patient.Allocation.entity.Doctors;
import PatientAllocation.Patient.Allocation.entity.Patients;
import PatientAllocation.Patient.Allocation.repository.DoctorsRepository;
import PatientAllocation.Patient.Allocation.repository.PatientsRepository;

@Service
public class DoctorsService {

	@Autowired
	private DoctorsRepository doctorsRepository;

	@Autowired
	private PatientsRepository patientsRepository;

	public String addDoctor(Doctors doctor) {
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
	public List<Doctors> getSuggestedDoctors(Long patientId) {

		Patients patient = patientsRepository.findById(patientId).orElse(null);
		String city = patient.getCity();
		String symptom = patient.getSymptom();
		String speciality = getSpeciality(symptom);
		List<Doctors> doctorsInCity = doctorsRepository.findByCity(city);
		List<Doctors> suggestedDoctors = doctorsRepository.findByCityAndSpeciality(city, speciality);

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

	private boolean isValidDoctor(Doctors doctor) {
		return doctor.getName().length() >= 3 && isValidCity(doctor.getCity()) && doctor.getCity().length() <= 20
				&& isValidEmail(doctor.getEmail()) && doctor.getPhoneNumber().length() >= 10;
	}

	private boolean isValidEmail(String email) {
		return email.matches("^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,}$");
	}

	private boolean isValidCity(String city) {
		return city != null && (city.equals("Delhi") || city.equals("Noida") || city.equals("Faridabad"));
	}

	private List<Doctors> createListWithMessage(String message) {
		List<Doctors> list = new ArrayList<>();
		Doctors dummyDoctor = new Doctors();
		dummyDoctor.setName(message);
		list.add(dummyDoctor);
		return list;
	}

}
