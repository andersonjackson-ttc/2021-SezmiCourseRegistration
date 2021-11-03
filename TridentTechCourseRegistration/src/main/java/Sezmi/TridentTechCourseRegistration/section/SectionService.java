package Sezmi.TridentTechCourseRegistration.section;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class SectionService 
{

	@Autowired
	private SectionRepository repository;

	//the ListAll method displays all of the sections in the Section table
	public List<Section> listAll()
	{
		return repository.findAll();
	}

	//the save method saves a new section to the Section table
	public void save (Section section)
	{
		repository.save(section);
	}

	//the get method finds a Section by the SectionID
	public Section get(String section_id)
	{
		return repository.findById(section_id).get();
	}

	//the delete method deletes a section by the SectionID
	public void delete(String courseID)
	{
		repository.deleteById(courseID);
	}
}
