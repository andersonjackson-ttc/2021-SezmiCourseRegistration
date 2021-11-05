//Jeremy Hunton
//Team Sezmi
//9/27/21
//the CourseService implements the CourseRepository to communicate with the server and create beans. 
package Sezmi.TridentTechCourseRegistration.course;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service					//tag as a service. 
@Transactional				
public class CourseService
{
	@Autowired				//autowired to inject the CourseRepository into the service layer beans. 
	private CourseRepository repository;
	
	//the ListAll method displays the entire list of courses
	public List<Course> listAll()
	{
		return repository.findAll();
	}
	
	//the save method allows for courses to be saved to the repository
	public void save(Course courses)
	{
		repository.save(courses);
	}
	
	//the get method allows for a course to be pulled from the list based upon the course code matching the search
	public Course get(String courseID)
	{
		return repository.findById(courseID).get();
	}
	
	/*the getSectionsOnly returns a CourseIdSection that only has the id and sections
	public CourseIdSection getSectionsOnly(String courseID)
	{
		return repository.findById(courseID);
	}*/
	
	//the delete method removes a Course bean from the repository based upon its courseCode
	public void delete(String courseID)
	{
		repository.deleteById(courseID);
	}	
}//end CourseService
