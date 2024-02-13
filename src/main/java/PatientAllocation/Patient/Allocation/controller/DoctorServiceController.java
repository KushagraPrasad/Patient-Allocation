package PatientAllocation.Patient.Allocation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import PatientAllocation.Patient.Allocation.entity.Doctors;
import PatientAllocation.Patient.Allocation.service.DoctorsService;

@RestController
@RequestMapping("/doctor")
public class DoctorServiceController {

	@Autowired
	private DoctorsService doctorsService;

	@PostMapping("")
	public ResponseEntity<String> addDoctor(@RequestBody Doctors doctor) {
		String newDoctor = doctorsService.addDoctor(doctor);
		return new ResponseEntity<>(newDoctor, HttpStatus.CREATED);
	}

	@RequestMapping(value = "/{doctorId}", method = RequestMethod.DELETE)
	public ResponseEntity<String> removeDoctor(@PathVariable Long doctorId) {
		if (doctorsService.removeDoctor(doctorId)) {
			return new ResponseEntity<>("Doctor removed successfully", HttpStatus.OK);
		}
		return new ResponseEntity<>("Doctor not found", HttpStatus.NOT_FOUND);
	}

}
