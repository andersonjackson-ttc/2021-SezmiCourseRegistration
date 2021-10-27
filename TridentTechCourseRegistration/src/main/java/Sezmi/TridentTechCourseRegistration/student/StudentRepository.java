package Sezmi.TridentTechCourseRegistration.student;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import Sezmi.TridentTechCourseRegistration.course.Courses;

public interface StudentRepository extends JpaRepository<Student, Long> {
	
/*	//the Query will limit our repository to ONLY show info for the student logged in.
	//this Query translates to select s from Student s where student.email = ?1
	@Query
	List<Student> findByEmail(String email);
*/		
}
