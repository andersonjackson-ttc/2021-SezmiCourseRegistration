package Sezmi.TridentTechCourseRegistration.student;

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
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import Sezmi.TridentTechCourseRegistration.course.Course;
import Sezmi.TridentTechCourseRegistration.course.CourseNoSection;
import Sezmi.TridentTechCourseRegistration.course.CourseService;
import Sezmi.TridentTechCourseRegistration.major.Major;
import Sezmi.TridentTechCourseRegistration.major.MajorService;
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

	@Autowired 
	private MajorService majorService;
	


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

	//getCoursesWithPrereqs returns the list of courses the student has prereqs for
	//changed to return CourseNoSection to limit load times from server
	@GetMapping("/student/{email}/course_prereq_true")
	public ResponseEntity<TreeSet<Course>> getCoursesWithPrereqs(@PathVariable String email)
	{
		try 
		{
			//create a set of courses
			TreeSet<Course> coursesWithPrereq = new TreeSet<Course>();

			//get the student based on the id
			Student student = service.getEmail(email);

			//get the student's major based off of the major id
			Major studentMajor = majorService.get(student.getMajor_id());

			//get the list of courses within the student's major
			Set<Course> majorCourses = studentMajor.getRequiredCourses();

			//get the list of courses the student has 
			Set<Course> coursesStudentTaken = student.getCoursesTaken();

			//for each course in the major, compare the pre-reqs to the courses the student has taken
			for(Course majorCourse : majorCourses)
			{
				//get the list of pre-reqs needed for the course
				Set<Course> coursePrereqs = majorCourse.getPreReqCourses();

				//define the preReqStatus as false until the list is cycled through
				boolean preReqStatus = false;

				//if the coursePrereqs is null, there are no pre-reqs needed, so the status is true
				if(coursePrereqs.isEmpty())
				{
					preReqStatus = true;
				}//end no pre-reqs, so the student can take it
				//else there are courses needed, so we need to compare to the student's courses taken
				else 
				{
					//compare the course pre-reqs of the single course to the courses the student has taken
					for(Course prereq : coursePrereqs)
					{
						//if the student hasn't taken the course, the status is false
						if(!coursesStudentTaken.contains(prereq))
						{
							preReqStatus=false;
						}
						//else the student has all the courses, so the status is true
						else {
							preReqStatus = true;
						}

					}//end for loop cycling the list of pre-reqs needed for a course

				}//end else the pre-reqs are not null, so compare pre-reqs to the student courses taken

				//if the prereq status is true after comparing ALL the courses in the coursePrereqs, add the course to the coursesWithPrereq
				if(preReqStatus)
				{
					//this is ugly as sin, but assign values of majorCourse to CourseNoSection object
					CourseNoSection courseNoSection;
					
					
					
					coursesWithPrereq.add(majorCourse);
				}
			}//end for loop cycling the list of courses the student has taken
			
			//return the list of courses from the major that the student has pre-reqs for
			return new ResponseEntity<TreeSet<Course>>(coursesWithPrereq, HttpStatus.OK);

		} 
		catch (NoSuchElementException e) 
		{
			//return new ResponseEntity<Boolean>(HttpStatus.NOT_FOUND);
			return new ResponseEntity<TreeSet<Course>>(HttpStatus.NOT_FOUND);
		}

	}//end getCoursesWithPrereq
	
	//getCoursesWithoutPrereqs returns the list of courses the student DOES NOT HAVE prereqs for (same logic as above, just returning the inverse)
		@GetMapping("/student/{email}/course_prereq_false")
		public ResponseEntity<TreeSet<Course>> getCoursesWithoutPrereqs(@PathVariable String email)
		{
			try 
			{
				//create a set of courses
				TreeSet<Course> coursesWOPrereq = new TreeSet<Course>();

				//get the student based on the id
				Student student = service.getEmail(email);

				//get the student's major based off of the major id
				Major studentMajor = majorService.get(student.getMajor_id());

				//get the list of courses within the student's major
				Set<Course> majorCourses = studentMajor.getRequiredCourses();

				//get the list of courses the student has 
				Set<Course> coursesStudentTaken = student.getCoursesTaken();

				//for each course in the major, compare the pre-reqs to the courses the student has taken
				for(Course majorCourse : majorCourses)
				{
					//get the list of pre-reqs needed for the course
					Set<Course> coursePrereqs = majorCourse.getPreReqCourses();

					//define the preReqStatus as false until the list is cycled through
					boolean preReqStatus = false;

					//if the coursePrereqs is null, there are no pre-reqs needed, so the status is true
					if(coursePrereqs.isEmpty())
					{
						preReqStatus = true;
					}//end no pre-reqs, so the student can take it
					//else there are courses needed, so we need to compare to the student's courses taken
					else 
					{
						//compare the course pre-reqs of the single course to the courses the student has taken
						for(Course prereq : coursePrereqs)
						{
							//if the student hasn't taken the course, the status is false
							if(!coursesStudentTaken.contains(prereq))
							{
								preReqStatus=false;
							}
							//else the student has all the courses, so the status is true
							else {
								preReqStatus = true;
							}

						}//end for loop cycling the list of pre-reqs needed for a course

					}//end else the pre-reqs are not null, so compare pre-reqs to the student courses taken

					//if the prereq status is true after comparing ALL the courses in the coursePrereqs, add the course to the coursesWithPrereq
					if(preReqStatus == false)
					{
						coursesWOPrereq.add(majorCourse);
					}
				}//end for loop cycling the list of courses the student has taken
				
				//return the list of courses from the major that the student has pre-reqs for
				return new ResponseEntity<TreeSet<Course>>(coursesWOPrereq, HttpStatus.OK);

			} 
			catch (NoSuchElementException e) 
			{
				//return new ResponseEntity<Boolean>(HttpStatus.NOT_FOUND);
				return new ResponseEntity<TreeSet<Course>>(HttpStatus.NOT_FOUND);
			}

		}//end getCoursesWithPrereq
	


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
