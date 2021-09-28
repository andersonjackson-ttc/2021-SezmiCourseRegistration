//Jeremy Hunton
//Team Sezmi
//9/27/21
//The purpose of CourseRepository is to handle the Spring Boot JPA repository.
package Sezmi.TridentTechCourseRegistration.course;

import org.springframework.data.jpa.repository.JpaRepository;


public interface CourseRepository extends JpaRepository<Course, String> {

}
