//Jeremy Hunton
//Team Sezmi
//9/28/21
//The purpose of MajorRepository is to handle the Spring Boot JPA repository for the Major table/bean objects.
package Sezmi.TridentTechCourseRegistration.major;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MajorRequirementsRepository extends JpaRepository<MajorRequirements, String>{

}
