package Sezmi.TridentTechCourseRegistration.student;

import java.util.ArrayList;
import java.util.Arrays;
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

import Sezmi.TridentTechCourseRegistration.completedCourses.CompletedCourses;
import Sezmi.TridentTechCourseRegistration.completedCourses.CompletedCoursesService;
import Sezmi.TridentTechCourseRegistration.course.Course;
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

	//getAllCourses
	@GetMapping("/student/{email}/all_courses")
	public ResponseEntity<TreeSet<Course>> getAllCourses(@PathVariable String email)
	{
		try 
		{
			//create a set of courses
			TreeSet<Course> courseList = new TreeSet<Course>();

			//get the student based on the id
			Student student = service.getEmail(email);

			//get the student's major based off of the major id
			Major studentMajor = majorService.get(student.getMajor_id());

			//get the list of courses within the student's major
			Set<Course> majorCourses = studentMajor.getRequiredCourses();

			//get the list of courses the student has 
			Set<Course> coursesStudentTaken = student.getCoursesTaken();

			//for each course in the major, compare the pre-reqs to the courses the student has taken
			for(Course course : majorCourses)
			{
				//get the list of pre-reqs needed for the course
				Set<Course> coursePrereqs = course.getPreReqCourses();

				//define the preReqStatus as false until the list is cycled through
				course.setAvailability("False");

				//if the coursePrereqs is null, there are no pre-reqs needed, so the status is true
				if(coursePrereqs.isEmpty() && !coursesStudentTaken.contains(course))
				{
					course.setAvailability("True");
					//course.setRadioButton("input type='radio' name = 'newRadioBtn' ");
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
							course.setAvailability("False");
							//course.setRadioButton("");
						}
						//else the student has all the courses, so the status is true
						else 
						{
							course.setAvailability("True");
							//course.setRadioButton("input type='radio' name = 'newRadioBtn' ");
						}

					}//end for loop cycling the list of pre-reqs needed for a course

				}//end else the pre-reqs are not null, so compare pre-reqs to the student courses taken

				//Add Course to List if Student has not taken the course
				if(!coursesStudentTaken.contains(course))
				{
					courseList.add(course);
				}


			}//end for loop cycling the list of courses the student has taken

			//return the list of courses from the major that the student has pre-reqs for
			return new ResponseEntity<TreeSet<Course>>(courseList, HttpStatus.OK);

		} 
		catch (NoSuchElementException e) 
		{
			//return new ResponseEntity<Boolean>(HttpStatus.NOT_FOUND);
			return new ResponseEntity<TreeSet<Course>>(HttpStatus.NOT_FOUND);
		}
	}//end getAllCourses



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
			service.save(existingStudent);
			return new ResponseEntity<>(HttpStatus.OK);
		} 
		catch (NoSuchElementException e) 
		{
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}//end update method

	//Jackson Patch logic for adding a course taken to the student
	@PatchMapping("/student/{id}/{string_of_selected_courses}")
	public ResponseEntity<Student> addCourseTaken(@PathVariable Long id, @PathVariable String string_of_selected_courses) 
	{
		try {
			//get the student by their id
			Student student = service.get(id);
			//Create an Array from String of Course IDs using a comma delimiter
			String [] course_ids = null;
			course_ids = string_of_selected_courses.split(",");
			//Loop through every course id in the Array
			for (String course_id : course_ids)
			{
				//Find Course by Course ID
				Course course = courseService.get(course_id);
				//Check to make sure course exists
				if (course != null)
				{
					//Add completed course to student object
					student.addCompletedCourse(course);
				}
			}
			//save the student object 
			service.save(student);

			return new ResponseEntity<Student>(HttpStatus.OK);

		} catch (Exception e) {
			return new ResponseEntity<Student>(HttpStatus.NOT_FOUND);
		}
	}

	//Adds all selected sections to the Student_Section Table
	@PatchMapping("/students/{id}/{string_of_section_ids}") 
	public ResponseEntity<Student> addSectionSelection(@PathVariable Long id, @PathVariable String string_of_section_ids) 
	{
		try { 
			//get the student by their id 
			Student student = service.get(id); 
			//Create an Array of Strings from section ids split by a comma delimiter
			String [] sectionStrings = null;
			sectionStrings = string_of_section_ids.split(",");
			//Loop through every section id in the Array
			for (String sectionAsString : sectionStrings)
			{
				//Find the section object with the section id
				Section section = sectionService.get(sectionAsString);
				//If the section object is not null add it to the student object
				if (section != null)
				{
					student.addSectionSelection(section);
				}
			}
			//Save the Student Object
			service.save(student);

			return new ResponseEntity<Student>(HttpStatus.OK);

		} catch (Exception e) 
		{ 
			return new ResponseEntity<Student>(HttpStatus.NOT_FOUND); 
		}

	}

	//the delete method to delete completed course from a student
	@DeleteMapping("/student/{id}/{course_ids}")
	public ResponseEntity<Student> deleteCourseSelection(@PathVariable Long id, @PathVariable String course_ids)
	{
		try 
		{
			//get the student by their id 
			Student student = service.get(id); 
			//Create an Array of Strings from course ids split by a comma delimiter
			String [] arrayOfCourseStrings = null;
			arrayOfCourseStrings = course_ids.split(",");
			//Loop through every course id in the array
			for (String course_id : arrayOfCourseStrings)
			{
				//Find the Course Object with the course_id
				Course course = courseService.get(course_id); 
				//If the course exists, remove it from the completed courses table
				if (course != null)
				{
					student.removeCompletedCourse(course);
					service.save(student); 
				}

			}
			/*
			//get the course selected by it's course_id 
			//Course course = courseService.get(course_id); 

			//list of courses the student has taken
			Set<Course> coursesTaken = student.getCoursesTaken();

			//remove the course from the courses the student has taken
			coursesTaken.remove(course);

			//assign the updated courses taken to the student 
			student.setCoursesTaken(coursesTaken);

			//save the student object 
			service.save(student); */

			return new ResponseEntity<Student>(HttpStatus.OK);
		}

		catch (Exception e)
		{
			return new ResponseEntity<Student>(HttpStatus.NOT_FOUND); 
		}
	}

	//the delete method to delete a section from a student
	//@PatchMapping("/students/{id}/{section_id}/remove_section")
	
	@DeleteMapping("/students/{id}/{section_id}")
	public ResponseEntity<Student> deleteSectionSelection(@PathVariable Long id, @PathVariable String section_id)
	{
		try 
		{
			//get the student by their id 
			Student student = service.get(id); 
			//get the section selected by it's section_id 
			Section section = sectionService.get(section_id); 
			//Remove Section From Student_Section Table
			student.deleteSectionSelection(section);

			//call the remove student method 
			//section.removeStudent(student);
			
			//save the student object 
			service.save(student); 

			return new ResponseEntity<Student>(HttpStatus.OK);
		}

		catch (Exception e)
		{
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
			return new ResponseEntity<Student>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	} 

	//the delete method allows an admin to delete a Student by email
	@DeleteMapping("/studentToDelete/{id}")
	public void delete(@PathVariable Long id)
	{
		service.delete(id);
	}
}//end section controller
