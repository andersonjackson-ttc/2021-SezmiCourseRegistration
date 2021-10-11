package Sezmi.TridentTechCourseRegistration.student;

import org.hibernate.engine.query.spi.sql.NativeSQLQueryCollectionReturn;
import java.util.List;
import java.util.NoSuchElementException;
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
import org.w3c.dom.CDATASection;

@RestController
public class StudentController {

	@Autowired
	private StudentService service;
	
	@GetMapping("/student")
	public List<Student> list(){
		
		return service.listAll();
	}
	
	//the get mapping displays the Student based on the email (using as student id/username)
	@GetMapping("/student/{email}")
	public ResponseEntity<Student> get(@PathVariable String email)
	{
		try 
		{
			Student student = service.get(email);
			return new ResponseEntity<>(student, HttpStatus.OK);
		} catch (NoSuchElementException e) 
		{
			return new ResponseEntity<Student>(HttpStatus.NOT_FOUND);
		}
	}
	
	//the add method adds a new Student to the Student table
	@PostMapping("/student")
	public void add(@RequestBody Student student) {
		service.save(student);
	}
	
	//the update method allows a student to edit the Student info based on the email
	@PutMapping("/student/{email}")
	public ResponseEntity<?> update(@RequestBody Student student, @PathVariable String email)
	{
		try 
		{
			Student existingStudent = service.get(email);
			service.save(student);
			return new ResponseEntity<>(HttpStatus.OK);
		} 
		catch (NoSuchElementException e) 
		{
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}//end update method
	
	//the delete method allows an admin to delete a Student by email
	@DeleteMapping("/student/{email}")
	public void delete(@PathVariable String email)
	{
		service.delete(email);
	}
}//end section controller
