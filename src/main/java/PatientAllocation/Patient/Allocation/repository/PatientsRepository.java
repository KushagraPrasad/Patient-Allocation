package PatientAllocation.Patient.Allocation.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import PatientAllocation.Patient.Allocation.entity.Patients;

@Repository
public interface PatientsRepository extends JpaRepository<Patients, Long> {

}
