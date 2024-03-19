package patientallocation.patient.allocation.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import patientallocation.patient.allocation.entity.Patient;
import patientallocation.patient.allocation.repository.PatientsRepository;

@Service
public class PatientServiceImpl {

	@Autowired
	private PatientsRepository patientsRepository;

	public String addPatient(Patient patient) {
		 if (isValidPatient(patient)) {
		patientsRepository.save(patient);
		return "Patient added successfully";
	}
		 else {
			 return "Invalid patient details";
		 }
	}
	public boolean removePatient(Long patientId) {
		if (patientsRepository.existsById(patientId)) {
			patientsRepository.deleteById(patientId);
			return true;
		}
		return false;
	}

	private boolean isValidPatient(Patient patient) {
		return patient.getName().length() >= 3 && patient.getCity().length() <= 20 && isValidEmail(patient.getEmail())
				&& patient.getPhoneNumber().length() >= 10;
	}

	private boolean isValidEmail(String email) {
		// You can use more sophisticated email validation if needed
		return email.matches("^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,}$");
	}
}
