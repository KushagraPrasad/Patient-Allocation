package patientallocation.patient.allocation.service;

import org.springframework.stereotype.Service;

import patientallocation.patient.allocation.entity.Patient;

@Service
public interface PatientService {

	public String addPatient(Patient patient);

	public boolean removePatient(Long patientId);

}
