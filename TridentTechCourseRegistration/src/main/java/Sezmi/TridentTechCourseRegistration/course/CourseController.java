//Jeremy Hunton
//Team Sezmi
//9/27/21
//the CourseController will handle RESTful services, API requests, and CRUD services for Course objects. 

package Sezmi.TridentTechCourseRegistration.course;

import java.util.*;

import org.springframework.beans.factory.annotation.*;
import org.springframework.http.*;

import org.springframework.web.bind.annotation.*;


@RestController				//declare a REST controller.
public class CourseController 
{
	@Autowired				//Autowire the CourseService to communicate with the CourseRepository
	private CourseService service;

	//This method is responsible for getting all the courses in the CourseRepository posted to localhost:8080/courses
	@GetMapping("/courses")
	public List<Course> list()
	{
		return service.listAll();
	}//end RESTful API retrieval operation

	//This method is responsible for getting a course by the course code and posting it to localhost:8080/courses/{id}
	@GetMapping("/courses/{courseID}")
	public ResponseEntity<Course> get(@PathVariable String courseID)
	{
		//try to find the course code
		try 
		{
			Course course = service.get(courseID);			
			return new ResponseEntity<Course>(course, HttpStatus.OK);		//return the course found and HTTP status as OK.	 
		}//end try block													
		//catch that we cannot find the product
		catch (NoSuchElementException notFound) 		
		{
			return new ResponseEntity<Course>(HttpStatus.NOT_FOUND);		//respond that the product was not found and set the HTTP to not found.
		}//end catch no element found

	}//end find course by course code. 

	//This method is responsible for allowing an admin to add a course to the course list.
	@PostMapping("/courses")												//@PostMapping assigns the URL link for the POST annotation to the web/server. 
	public void add(@RequestBody Course course)
	{
		service.save(course); 												//call the API service method to save the product to the server. 
	}//end RESTful API create function. 

	//This method is responsible for allowing an admin to update the information by course code in the course list on the server.
	@PutMapping("/courses/{courseID}")											//@PutMapping allows PUT API requests.
	public ResponseEntity<?> update(@RequestBody Course course, @PathVariable String courseID)
	{
		try 
		{
			Course existCourse = service.get(courseID);					//find the existing course by the id and save the new product over it
			service.save(course);
			return new ResponseEntity<>(HttpStatus.OK);
		}//end try

		catch (NoSuchElementException e) 
		{
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}//end PUT method for editing a course.

	//RESTful API for Delete Function. This allows the user to issue a DELETE request to remove the course by the course code. 
	@DeleteMapping("/courses/{courseID}")
	public void delete(@PathVariable String courseID)
	{
		service.delete(courseID);				//call the delete function from the service to issue the delete command through the pipeline back to the server
	}
}//end CourseController
