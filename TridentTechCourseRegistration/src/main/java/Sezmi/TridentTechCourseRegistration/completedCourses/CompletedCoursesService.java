package Sezmi.TridentTechCourseRegistration.completedCourses;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class CompletedCoursesService 
{
	@Autowired
	private CompletedCoursesRepository repository;
	
	//the ListAll methods displays all the completed courses within the completedCourses table
	public List<CompletedCourses> listAll()
	{
		return repository.findAll();
	}
	
	//the save method allows for completed courses to be saved to the completedCourses table
	public void save(CompletedCourses completedCourses)
	{
		repository.save(completedCourses);
	}
	//the get method allows for a Completed Course to be pulled from the database table by its primary key
		public CompletedCourses get(String email)
		{
			return repository.findById(email).get();
		}
		
		//the delete method allows for a Completed Course to be removed from the Major database table based upon its email (username)
		public void delete(String email)
		{
			repository.deleteById(email);
		}
		

}
