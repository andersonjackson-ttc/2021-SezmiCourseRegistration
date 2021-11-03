package Sezmi.TridentTechCourseRegistration.major;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface MajorRepository extends JpaRepository<Major, String>
{	
	//this query uses the MajorIdName interface to only return the major name and id. 
	@Query(value = "SELECT major_id, major_name FROM major", nativeQuery = true)
	List<MajorIdName> getMajorIdName();
}
