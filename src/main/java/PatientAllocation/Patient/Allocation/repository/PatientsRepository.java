package patientallocation.patient.allocation.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import patientallocation.patient.allocation.entity.Patient;

@Repository
public interface PatientsRepository extends JpaRepository<Patient, Long> {

}
