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
import Sezmi.TridentTechCourseRegistration.section.SectionService;

@RestController
public class StudentController {

	@Autowired
	private StudentService service;
	
	@Autowired
	private CourseService courseService;
	
	@Autowired
	private SectionService sectionService;


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
	//compare courses taken (<Student> getCoursesTaken) with set of pre reqs for that course from
		//course_prereq table (<Course> getPreReqCourses) Returns true if pre reqs met for given course
		@GetMapping("/student/{id}/{course_id}/course_prereq")
		public ResponseEntity<String> getPreReqStatus (@PathVariable Long id,@PathVariable String course_id)
		//public ResponseEntity<Set<Course>> getPreReqStatus (@PathVariable Long id,@PathVariable String course_id)
		{
			
			
			try {
				String tellMeString = "";
				//Boolean preReqStatus=false;
				String preReqStatus = "false";
				Student student = service.get(id);
				//get the course completed by its course_id
				Course courseID = courseService.get(course_id);
				Set<Course> studentCoursesTaken = student.getCoursesTaken();
				Set<Course>preReqsNeeded = new TreeSet<Course>(courseID.getPreReqCourses());
				
				//compare the courses the student has taken to the PreReqs Needed for
				//a given course
				if(preReqsNeeded.isEmpty())
				{
					//preReqStatus = true;
					tellMeString = "THIS COURSE HAS NO PRE-REQS";
				}
				else {
					for(Course course : preReqsNeeded)
					{
						/*if(studentCoursesTaken.contains(course))
						{
							//preReqStatus = true;
							tellMeString = "Student has Taken one of the Pre-Reqs";
						}*/
						
						
						
						
						if(!studentCoursesTaken.contains(course))
						{
							preReqStatus="false";
						}
						else 
						{
							preReqStatus="true";
						}
							
											
					}
					
				}
				
				
				//return new ResponseEntity<Set<Course>>(preReqsNeeded, HttpStatus.OK);
				return new ResponseEntity<String>(preReqStatus, HttpStatus.OK);
			}catch(NoSuchElementException e){
				//return new ResponseEntity<Boolean>(HttpStatus.NOT_FOUND);
				return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
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
	
	//getChosenSections returns the list of sections the student has chosen
		@GetMapping("/student/{email}/sections")
		public ResponseEntity<Set<Section>> getChosenSections (@PathVariable String email)
		{
			try {
				Student bueller = service.getEmail(email);
				
				TreeSet<Section>sectionsChosen =new TreeSet<Section>(bueller.getSectionsChosen());
				return new ResponseEntity<Set<Section>>(sectionsChosen, HttpStatus.OK);
			} catch (NoSuchElementException e) {
				return new ResponseEntity<Set<Section>>(HttpStatus.NOT_FOUND);
			}
			
		}
	
	//GetStudentEmailMajor returns only the student's email and major
	@GetMapping("/student/{email}/major")
	public ResponseEntity<StudentEmailMajor> getStudentEmailMajoResponseEntity (@PathVariable String email)
	{
		try {
			StudentEmailMajor studentEmailMajor = service.getStudentMajor(email);
			return new ResponseEntity<StudentEmailMajor>(studentEmailMajor, HttpStatus.OK);
		}
		catch (NoSuchElementException x)
		{
			return new ResponseEntity<StudentEmailMajor>(HttpStatus.NOT_FOUND);
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
			//existingStudent.setCompleted_courses(student.getCompleted_courses());
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
	
	
	  //adds section selection to the student
	  @PatchMapping("/students/{id}/{section_id}") 
	  public ResponseEntity<Student> addSectionSelection(@PathVariable Long id, @PathVariable String section_id) 
	  {
	  try { 
		 //get the student by their id 
	  Student student = service.get(id); 
	  //get the section selected by it's section_id 
	  Section section = sectionService.get(section_id); 
	  //call the addSectionSelection method in the student to add the completed course to the set
	  student.addSectionSelection(section);
	  
	  //save the student object 
	  service.save(student); 
	  
	  //add the corresponding course to the courses taken for the student								Removed by Jeremy 11/23/21 since we don't want it to add a course when you add the section
	  //addCourseTaken(id, section.getCourse_id());
	  
	  //return the student http status
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
