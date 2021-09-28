//Jeremy Hunton
//Team Sezmi
//9/28/21
//the MajorService implements the MajorRepository to communicate with the Major table and create beans for the Major web controller. 
package TridentTechCourseRegistration.major;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class MajorService 
{
	@Autowired			//autowired to inject MajorRepository into service layer beans.
	private MajorRepository repository;
	
	//the listAll method displays all of the Majors within the major database table
	public List<Major> listAll()
	{
		return repository.findAll();
	}
	
	//the save method allows for Majors to be saved to the major database table
	public void save(Major major)
	{
		repository.save(major);
	}
	
	//the get method allows for a Major to be pulled from the major database table based upon its MajorId.
	public Major get(String majorId)
	{
		return repository.findById(majorId).get();
	}
	
	//the delete method allows for a Major to be removed from the major database table based upon its MajorID
	public void delete(String majorId)
	{
		repository.deleteById(majorId);
	}
}//end MajorService
