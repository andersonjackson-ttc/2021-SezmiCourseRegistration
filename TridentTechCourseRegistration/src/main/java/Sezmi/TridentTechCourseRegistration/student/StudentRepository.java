package Sezmi.TridentTechCourseRegistration.student;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;


public interface StudentRepository extends JpaRepository<Student, Long> {
	
/*	//the Query will limit our repository to ONLY show info for the student logged in.
	//this Query translates to select s from Student s where student.email = ?1
	@Query
	List<Student> findByEmail(String email);
*/		
	Student findByEmail(String email);
	
	//this Query will return a student's email and major given it's email address
	@Query(value = "SELECT email, major_id FROM student s WHERE s.email = ?1", nativeQuery = true)
	StudentEmailMajor getStudentEmailMajors(String email);
	
	
	//this Query returns the student's email and major given for ALL students (to be implemented in another sprint
	@Query(value = "SELECT email, major_id FROM student", nativeQuery = true)
	List<StudentEmailMajor> getAllStudentEmailMajor();
	
}
