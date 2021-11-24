package Sezmi.TridentTechCourseRegistration.major;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class MajorService 
{
	@Autowired
	private MajorRepository repository;
	
	//the ListAll methods displays all the Majors within the Major table
	public List<Major> listAll()
	{
		return repository.findAll();
	}
	
	//the save method allows for Majors to be saved to the Major table
	public void save(Major majors)
	{
		repository.save(majors);
	}
	
	//the get method allows for a Major to be pulled from the database table by its primary key
	public Major get(String major_Id)
	{
		return repository.findById(major_Id).get();
	}
	
	//the getMajorIdName returns  ALL the majors with ONLY the major_id and the major_name
	public List<MajorIdName> getMajorIdName()
	{
		return repository.getMajorIdName();
	}
	
	
	//the delete method allows for a Major to be removed from the Major database table based upon its MajorId
	public void delete(String major_Id)
	{
		repository.deleteById(major_Id);
	}
	

}
