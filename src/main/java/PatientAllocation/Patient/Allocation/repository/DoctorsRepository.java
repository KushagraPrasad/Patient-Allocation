package PatientAllocation.Patient.Allocation.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import PatientAllocation.Patient.Allocation.entity.Doctors;

@Repository
public interface DoctorsRepository extends JpaRepository<Doctors, Long> {

	List<Doctors> findByCityAndSpeciality(String city, String speciality);

	List<Doctors> findByCity(String city);

}
