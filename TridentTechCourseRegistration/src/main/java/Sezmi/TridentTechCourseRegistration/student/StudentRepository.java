package Sezmi.TridentTechCourseRegistration.student;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface StudentRepository extends JpaRepository<Student, Long> {
	
	//the Query will limit our repository to ONLY show info for the student logged in. 
	@Query
	List<Student> findByEmail(String email);

}
