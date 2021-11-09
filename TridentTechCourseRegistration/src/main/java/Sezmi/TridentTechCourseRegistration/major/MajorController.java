package Sezmi.TridentTechCourseRegistration.major;

import java.util.ArrayList;
	import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.TreeSet;

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

import Sezmi.TridentTechCourseRegistration.course.Course;
import Sezmi.TridentTechCourseRegistration.course.CourseService;
import Sezmi.TridentTechCourseRegistration.student.Student;
import Sezmi.TridentTechCourseRegistration.student.StudentService;

@RestController
public class MajorController 
 {
	@Autowired 	//autowired info coming from the MajorService
	private MajorService service;
	@Autowired 
	private StudentService studentService; //pulls information from the student object. 
	
	
	//the list method lists ALL of the majors for the major dropdown menu (uses MajorIdName interface for relevant info)
	@GetMapping("/majors")
	public List<MajorIdName> list()
	{
		return service.getMajorIdName();
	}
	
	//the get method maps the individual major to localhost:8080/majors/{major_id}
	@GetMapping("/majors/{major_id}")
	public ResponseEntity<Major> getMajorIdName(@PathVariable String major_id)
	{
		try
		{
			Major major = service.get(major_id);
			return new ResponseEntity<>(major, HttpStatus.OK);
		}
		catch (NoSuchElementException e) 
		{
			return new ResponseEntity<Major>(HttpStatus.NOT_FOUND);
		}
	}//end get method 
	
	
	//the getCourses method maps the classes for the major selected COMPARED TO the classes the student has taken
	//(shows only the classes the student DOESN'T HAVE)
	@GetMapping("/majors/{email}/courses") //does this needs to access student email
	
	public ResponseEntity<Set<Course>> getCourses(@PathVariable String email)
	{
		//create a local set to hold the courses the student needs
		TreeSet<Course> coursesStudentNeeds = new TreeSet<Course>();
				
		try {
			
			//declare the student using the email given
			Student student = studentService.getEmail(email);
			//get the set of courses the student has taken 
			Set<Course> studentCoursesTaken = student.getCoursesTaken();
			//declare the major at the major id given
			Major major = service.get(student.getMajor_id());
			//get the set of courses needed in the major from the major
		
			Set<Course> majorCourses = major.getRequiredCourses();
					
			//compare the courses the student has taken to the courses within the major
			//for each course within majorCourses
			for(Course course : majorCourses)
			{
				//see if the student has taken that course. If the student HASN'T, add it to the coursesStudentNeeds
				if(!studentCoursesTaken.contains(course) && !course.getAvailableSections().isEmpty())
				{
					//add the course to the courseseStudentNeeds set
					coursesStudentNeeds.add(course);
				}
			}//end for loop cycling the courses within the major
					
			return new ResponseEntity<Set<Course>>(coursesStudentNeeds, HttpStatus.OK);
		} catch (NoSuchElementException e) {
			
			return new ResponseEntity<Set<Course>>(HttpStatus.NOT_FOUND);
		}
	}//end getCourses method that returns the relevant courses the student user needs. 
	
	//the method is responsible for allowing an admin to add a major to the major table
	@PostMapping("/majors")
	public void add(@RequestBody Major major)
	{
		service.save(major);
	}//end add method 
	
	//this method is responsible for allowing an admin to update the info in a major by searching its ID
	@PutMapping("/majors/{major_id}")
	public ResponseEntity<?> update(@RequestBody Major major, @PathVariable String major_id)
	{
		try
		{
			Major existingMajor = service.get(major_id);
			service.save(existingMajor);
			return new ResponseEntity<>(HttpStatus.OK);
		}
		catch(NoSuchElementException e)
		{
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}//end update method 
	
	//this method is for admin deleting function of majors
	@DeleteMapping("/majors/{major_id}")
	public void delete(@PathVariable String major_id)
	{
		service.delete(major_id);
	}

	

	
	

}
