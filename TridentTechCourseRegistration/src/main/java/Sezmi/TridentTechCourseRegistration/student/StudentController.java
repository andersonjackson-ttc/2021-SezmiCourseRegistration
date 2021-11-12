package Sezmi.TridentTechCourseRegistration.student;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.TreeSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import Sezmi.TridentTechCourseRegistration.course.Course;
import Sezmi.TridentTechCourseRegistration.course.CourseService;
import Sezmi.TridentTechCourseRegistration.section.Section;

@RestController
public class StudentController {

	@Autowired
	private StudentService service;
	
	@Autowired
	private CourseService courseService;


	@GetMapping("/student")
	public List<Student> list(){

		return service.listAll();
	}

	//Get the student based upon their email address
	@GetMapping("/student/{email}")
	public ResponseEntity<Student> get(@PathVariable String email)
	{
		try 
		{
			Student student = service.getEmail(email);
			return new ResponseEntity<>(student, HttpStatus.OK);
		} catch (NoSuchElementException e) 
		{
			return new ResponseEntity<Student>(HttpStatus.NOT_FOUND);
		}
	}
	
	
	//getStudentCourses returns the list of courses the student has taken
	@GetMapping("/student/{email}/courses")
	public ResponseEntity<Set<Course>> getStudentCourses (@PathVariable String email)
	{
		try {
			Student bueller = service.getEmail(email);
			
			TreeSet<Course>coursesTaken =new TreeSet<Course>(bueller.getCoursesTaken());
			return new ResponseEntity<Set<Course>>(coursesTaken, HttpStatus.OK);
		} catch (NoSuchElementException e) {
			return new ResponseEntity<Set<Course>>(HttpStatus.NOT_FOUND);
		}
		
	}

	//the add method adds a new Student to the Student table
	@PostMapping("/student")
	public void add(@RequestBody Student student) {
		service.save(student); //was save(student) new Student("testemail@gmail.com", "Hunton", "Jeremy", "12345", "CPT")
	}

	//the update method allows a student to edit the Student info based on the id
	@PutMapping("/student/{id}")
	public ResponseEntity<?> update(@RequestBody Student student, @PathVariable Long id)
	//public ResponseEntity<Student> update(@PathVariable(value = "id") Long studentId, @Validated @RequestBody Student student)
	{
		try 
		{
			Student existingStudent = service.get(id);
			existingStudent.setEmail(student.getEmail());
			existingStudent.setLast_name(student.getLast_name());
			existingStudent.setFirst_name(student.getFirst_name());
			existingStudent.setCompleted_courses(student.getCompleted_courses());
			service.save(existingStudent);
			return new ResponseEntity<>(HttpStatus.OK);
		} 
		catch (NoSuchElementException e) 
		{
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}//end update method

	//Jackson Patch logic for adding a course taken to the student
	@PatchMapping("/student/{id}/{course_id}")
	public ResponseEntity<Student> addCourseTaken(@PathVariable Long id, @PathVariable String course_id) 
	{
		try {
			//get the student by their id
			Student student = service.get(id);
			//get the course completed by its course_id
			Course course = courseService.get(course_id);
			//call the addCompletedCourse method in the student to add the completed course to the set
			student.addCompletedCourse(course);
			
			//save the student object 
			service.save(student);
			//return the student
			return new ResponseEntity<Student>(HttpStatus.OK);
			
		} catch (Exception e) {
			return new ResponseEntity<Student>(HttpStatus.NOT_FOUND);
		}
		
	}
	//was original patch method
	@PatchMapping("/student/{email}")
	public ResponseEntity<Student> updateStudentPartially(@RequestBody Student student, @PathVariable String email)
	{
		try
		{
			Student existingStudent = service.getEmail(email);
			existingStudent.setMajor_id(student.getMajor_id());
			service.save(existingStudent); 
			return new ResponseEntity<Student>(HttpStatus.OK);
		}

		catch (Exception e) 
		{
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	} 

	//the delete method allows an admin to delete a Student by email
	@DeleteMapping("/student/{id}")
	public void delete(@PathVariable Long id)
	{
		service.delete(id);
	}
}//end section controller
