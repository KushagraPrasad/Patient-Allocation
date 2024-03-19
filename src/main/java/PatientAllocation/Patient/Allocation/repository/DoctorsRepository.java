package patientallocation.patient.allocation.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import patientallocation.patient.allocation.entity.Doctor;

@Repository
public interface DoctorsRepository extends JpaRepository<Doctor, Long> {

	List<Doctor> findByCityAndSpeciality(String city, String speciality);

	List<Doctor> findByCity(String city);

}
