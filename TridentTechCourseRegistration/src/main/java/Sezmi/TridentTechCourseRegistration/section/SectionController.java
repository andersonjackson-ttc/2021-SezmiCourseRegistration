package Sezmi.TridentTechCourseRegistration.section;

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

import Sezmi.TridentTechCourseRegistration.course.Course;
import Sezmi.TridentTechCourseRegistration.major.Major;
import Sezmi.TridentTechCourseRegistration.student.Student;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;

@RestController
public class SectionController 
{
	@Autowired
	private SectionService service;

	@GetMapping("/section")
	public List<Section> list()
	{
		return service.listAll();
	}

	//the get method displays the Section based on the section id
	@GetMapping("/section/{section_id}")
	public ResponseEntity<Section> get(@PathVariable String section_id)
	{
		try 
		{
			Section section = service.get(section_id);
			return new ResponseEntity<Section>(section, HttpStatus.OK);
		} 
		catch ( NoSuchElementException e) 
		{
			return new ResponseEntity<Section>(HttpStatus.NOT_FOUND);
		}
	}

	//the add method adds a new Section to the Section table
	@PostMapping("/section")
	public void add(@RequestBody Section section)
	{
		service.save(section);		
	}

	//the update method allows an admin to edit the Section based upon the SectionID
	@PutMapping("/section/{courseID}")
	public ResponseEntity<?> update(@RequestBody Section section, @PathVariable String sectionID)
	{
		try 
		{
			Section existingSection = service.get(sectionID);
			service.save(section);
			return new ResponseEntity<>(HttpStatus.OK);

		} catch (NoSuchElementException e) 
		{
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}//end update method

	//the delete method allows an admin to delete a Section by the SectionID
	@DeleteMapping("/section/{sectionID")
	public void delete(@PathVariable String sectionID)
	{
		service.delete(sectionID);
	}





}//end SectionController
