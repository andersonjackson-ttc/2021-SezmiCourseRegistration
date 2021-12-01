package Sezmi.TridentTechCourseRegistration.major;

import java.util.ArrayList;
	import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.TreeSet;

import org.hibernate.tool.schema.internal.exec.ScriptSourceInputFromReader;
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
import Sezmi.TridentTechCourseRegistration.section.Section;
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
			return new ResponseEntity<Major>(major, HttpStatus.OK);
		}
		catch (NoSuchElementException e) 
		{
			return new ResponseEntity<Major>(HttpStatus.NOT_FOUND);
		}
	}//end get method 
	
	/*
	//the getCourses method maps the classes for the major selected COMPARED TO the classes the student has taken
	//(shows only the classes the student DOESN'T HAVE)
	@GetMapping("/majors/{email}/courses") //does this needs to access student email
	
	public ResponseEntity<Set<Course>> getCourses(@PathVariable String email)
	{
		//create a local set to hold the courses the student needs
		TreeSet<Course> coursesStudentNeeds = new TreeSet<Course>();
		//TreeSet<Course> sortedCoursesStudentNeedSet = new TreeSet<Course>();
				
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
				//TreeSet<Section>availableSections =new TreeSet<Section>(course.getAvailableSections());

					
				//see if the student has taken that course. If the student HASN'T, add it to the coursesStudentNeeds
				if(!studentCoursesTaken.contains(course) && !course.getAvailableSections().isEmpty())
				{
					coursesStudentNeeds.add(course);
					
					/*for (Section section : course.getAvailableSections())
					{
						//if (!availableSections.isEmpty())
						//{
							availableSections.add(section);
						//}
				
					}*/
					
					//sortedCoursesStudentNeedSet.addAll(coursesStudentNeeds);
					
					//Set<Section> sortedSections = course.getAvailableSections();
					//List<Section> tguSections = new ArrayList<Section>(sortedSections);
					//Collections.sort(tguSections, (sectionOne, sectionTwo) -> sectionOne.getSection_id().compareToIgnoreCase(sectionTwo.getSection_id()));
					//Course sortedStuffCourse = new Course();
					//TreeSet<Section>availableSections =new TreeSet<Section>(course.getAvailableSections());
					//TreeSet<Section>availableSections =new TreeSet<Section>(course.getAvailableSections());
					//add the course to the courseseStudentNeeds set
					/*
				}
			}//end for loop cycling the courses within the major
					
			return new ResponseEntity<Set<Course>>(coursesStudentNeeds, HttpStatus.OK);
		} catch (NoSuchElementException e) {
			
			return new ResponseEntity<Set<Course>>(HttpStatus.NOT_FOUND);
		}
	}//end getCourses method that returns the relevant courses the student user needs. 
	*/
	
	//the getCourses method maps the classes for the major selected COMPARED TO the classes the student has taken
	//(shows only the classes the student DOESN'T HAVE)
	@GetMapping("/majors/{email}/courses") //does this needs to access student email
	public ResponseEntity<Set<Course>> getCourses(@PathVariable String email)
	{
		//create a local set to hold the courses the student needs
		TreeSet<Course> coursesStudentNeeds = new TreeSet<Course>();
		//TreeSet<Course> sortedCoursesStudentNeedSet = new TreeSet<Course>();
				
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
				if(!studentCoursesTaken.contains(course) && !course.getAvailableSections().isEmpty() && !studentCoursesTaken.contains(course))
				{
					//get the list of pre-reqs needed for the course
					Set<Course> coursePrereqs = course.getPreReqCourses();

					//define the preReqStatus as false until the list is cycled through
					//course.setRadioButton("");
					course.setAvailability("False");

					//if the coursePrereqs is null, there are no pre-reqs needed, so the status is true
					if(coursePrereqs.isEmpty())
					{
						course.setAvailability("True");
						//course.setRadioButton("input type='radio' name = 'radioBtn' ");
					}//end no pre-reqs, so the student can take it
					//else there are courses needed, so we need to compare to the student's courses taken
					else 
					{
						//compare the course pre-reqs of the single course to the courses the student has taken
						for(Course prereq : coursePrereqs)
						{
							//if the student hasn't taken the course, the status is false
							if(!studentCoursesTaken.contains(prereq))
							{
								course.setAvailability("False");
								//course.setRadioButton("");
							}
							//else the student has all the courses, so the status is true
							else 
							{
								course.setAvailability("True");
								//course.setRadioButton("input type='radio' name = 'radioBtn' ");
							}

						}//end for loop cycling the list of pre-reqs needed for a course

					}//end else the pre-reqs are not null, so compare pre-reqs to the student courses taken
					coursesStudentNeeds.add(course);
					
					/*for (Section section : course.getAvailableSections())
					{
						//if (!availableSections.isEmpty())
						//{
							availableSections.add(section);
						//}
				
					}*/
					
					//sortedCoursesStudentNeedSet.addAll(coursesStudentNeeds);
					
					//Set<Section> sortedSections = course.getAvailableSections();
					//List<Section> tguSections = new ArrayList<Section>(sortedSections);
					//Collections.sort(tguSections, (sectionOne, sectionTwo) -> sectionOne.getSection_id().compareToIgnoreCase(sectionTwo.getSection_id()));
					//Course sortedStuffCourse = new Course();
					//TreeSet<Section>availableSections =new TreeSet<Section>(course.getAvailableSections());
					//TreeSet<Section>availableSections =new TreeSet<Section>(course.getAvailableSections());
					//add the course to the courseseStudentNeeds set
					
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
