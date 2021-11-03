package Sezmi.TridentTechCourseRegistration.section;

import java.util.List;

//import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;

public interface SectionRepository extends JpaRepository<Section, String> {
	
	//@Query("select u from Section u where u.section_id = ?1")
	//List<Section> findBySectionID(String section_id);
	//Section findBySection_id(String section_id);
	

}
