package PatientAllocation.Patient.Allocation.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import PatientAllocation.Patient.Allocation.entity.Doctors;
import PatientAllocation.Patient.Allocation.entity.Patients;
import PatientAllocation.Patient.Allocation.service.DoctorsService;
import PatientAllocation.Patient.Allocation.service.PatientsService;

@RestController
@RequestMapping("/patient")
public class PatientServiceController {

	@Autowired
	private DoctorsService doctorsService;

	@Autowired
	private PatientsService patientsService;

	@PostMapping(value = "")
	public ResponseEntity<String> addPatient(@RequestBody Patients patient) {
		String newPatient = patientsService.addPatient(patient);
		return new ResponseEntity<>(newPatient, HttpStatus.CREATED);
	}

	@RequestMapping(value = "/{patientId}", method = RequestMethod.DELETE)
	public ResponseEntity<String> removePatient(@PathVariable Long patientId) {
		if (patientsService.removePatient(patientId)) {
			return new ResponseEntity<>("Patient removed successfully", HttpStatus.OK);
		}
		return new ResponseEntity<>("Patient not found", HttpStatus.NOT_FOUND);
	}

	@GetMapping("/suggestion/{patientId}")
	public ResponseEntity<List<Doctors>> suggestDoctors(@PathVariable Long patientId) {
		List<Doctors> suggestedDoctors = doctorsService.getSuggestedDoctors(patientId);
		if (suggestedDoctors.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(suggestedDoctors, HttpStatus.OK);
	}
}
