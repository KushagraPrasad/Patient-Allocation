package patientallocation.patient.allocation.service;

import java.util.List;

import org.springframework.stereotype.Service;

import patientallocation.patient.allocation.entity.Doctor;

@Service
public interface DoctorService {

	public String addDoctor(Doctor doctor);

	public boolean removeDoctor(Long doctorId);

	public List<Doctor> getSuggestedDoctors(Long patientId);
}
