package Sezmi.TridentTechCourseRegistration.student;

import org.springframework.data.jpa.repository.JpaRepository;

//the StudentRepository is used to automatically create students in the database using the Spring Data JPA. 
public interface StudentRepository extends JpaRepository<Student, Integer>{

}
