//Jeremy Hunton
//Team Sezmi
//9/27/21
//The purpose of CourseRepository is to handle the Spring Boot JPA repository for the Course table/bean objects.
package Sezmi.TridentTechCourseRegistration.course;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;





public interface CourseRepository extends JpaRepository<Course, String> {

	//this Query will select all the courses for a major from the major_course table
/*	@Query(value = "SELECT course.* FROM course c JOIN major_course major ON c.course_id WHERE major_id = :major", nativeQuery = true)
	List<Course> findCoursesByMajor(String major_id);*/


}
