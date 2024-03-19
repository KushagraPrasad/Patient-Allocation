package patientallocation.patient.allocation.controller;

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

import patientallocation.patient.allocation.entity.Doctor;
import patientallocation.patient.allocation.entity.Patient;
import patientallocation.patient.allocation.service.DoctorServiceImpl;
import patientallocation.patient.allocation.service.PatientServiceImpl;

@RestController
@RequestMapping("/patient")
public class PatientServiceController {

	@Autowired
	private DoctorServiceImpl doctorsService;

	@Autowired
	private PatientServiceImpl patientsService;

	@PostMapping(value = "")
	public ResponseEntity<String> addPatient(@RequestBody Patient patient) {
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
	public ResponseEntity<List<Doctor>> suggestDoctors(@PathVariable Long patientId) {
		List<Doctor> suggestedDoctors = doctorsService.getSuggestedDoctors(patientId);
		if (suggestedDoctors.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(suggestedDoctors, HttpStatus.OK);
	}
}
