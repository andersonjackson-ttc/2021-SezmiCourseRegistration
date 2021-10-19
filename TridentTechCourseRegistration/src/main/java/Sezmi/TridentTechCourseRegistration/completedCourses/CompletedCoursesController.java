package Sezmi.TridentTechCourseRegistration.completedCourses;

import org.hibernate.engine.query.spi.sql.NativeSQLQueryCollectionReturn;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


import java.util.List;
import java.util.NoSuchElementException;

@RestController
public class CompletedCoursesController 
{
	@Autowired
	private CompletedCoursesService service;
	
	@GetMapping("/completedCourses")
	public List<CompletedCourses> list()
	{
		return service.listAll();
	}
	
	//the get method displays the Section based on the section id
	
	@GetMapping("/completedCourses/{email}")
	public ResponseEntity<CompletedCourses> get(@PathVariable String email)
	{
		try 
		{
			CompletedCourses completedCourses = service.get(email);
			return new ResponseEntity<>(completedCourses, HttpStatus.OK);
		} 
		catch ( NoSuchElementException e) 
		{
			return new ResponseEntity<CompletedCourses>(HttpStatus.NOT_FOUND);
		}
	}
		//the add method adds a new completedCourse to the Completed Courses table
		@PostMapping("/completedCourses")
		public void add(@RequestBody CompletedCourses completedCourses) {
			service.save(completedCourses);
		}
		
		//the update method allows a student to edit the Completed Courses info based on the email
		@PutMapping("/completedCourses/{email}")
		public ResponseEntity<?> update(@RequestBody CompletedCourses completedCourses, @PathVariable String email)
		{
			try 
			{
				CompletedCourses existingCompletedCourses= service.get(email);
				service.save(completedCourses);
				return new ResponseEntity<>(HttpStatus.OK);
			} 
			catch (NoSuchElementException e) 
			{
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
		}//end update method
		
		//the delete method allows the student to delete a Completed Course by email
		@DeleteMapping("/completedCourses/{email}")
		public void delete(@PathVariable String email)
		{
			service.delete(email);
		}
	}//end completedCourses controller
		


