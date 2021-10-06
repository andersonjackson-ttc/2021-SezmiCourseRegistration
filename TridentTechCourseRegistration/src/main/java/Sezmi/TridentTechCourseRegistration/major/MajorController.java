package Sezmi.TridentTechCourseRegistration.major;

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

@RestController
public class MajorController 
{
	@Autowired 	//autowired info coming from the MajorService
	private MajorService service;
	
	//the list method maps all of the Majors to localhost:8080/majors
	@GetMapping("/majors")
	public List<Major> list()
	{
		return service.listAll();
	}
	
	//the get method maps the individual major to localhost:8080/majors/{major_id}
	@GetMapping("/majors/{major_id}")
	public ResponseEntity<Major> get(@PathVariable String major_id)
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
			service.save(major);
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
