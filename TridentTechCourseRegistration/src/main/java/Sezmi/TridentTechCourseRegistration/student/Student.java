package Sezmi.TridentTechCourseRegistration.student;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import Sezmi.TridentTechCourseRegistration.completedCourses.CompletedCourses;
import Sezmi.TridentTechCourseRegistration.course.Course;
import Sezmi.TridentTechCourseRegistration.section.Section;


@Entity
@Table(name = "student")
public class Student{

	//declare variables for column in the table
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false, unique = true, length = 45)
	private String email;

	@Column(nullable = false, length = 20)
	private String last_name;

	@Column(nullable = false, length = 20)
	private String first_name;

	@Column(nullable = false, length = 64)
	private String salt;

	@Column(nullable = false, length = 64)
	private String password;

	@Column(nullable = false, length = 25)
	private String major_id;

	//@Column(nullable = false, length = 999)
	//private String completed_courses;

	
	  //declare a ManyToMany set so that courses can be assigned to the student.
	  
	  @ManyToMany
	  
	  @JoinTable( name = "student_course", joinColumns = @JoinColumn(name = "id"),
	  inverseJoinColumns = @JoinColumn(name = "course_id")) private Set<Course>
	  coursesTaken = new HashSet<Course>();
	  
	  //declare a ManyToMany set so that section can be assigned to the student.
		
		  @ManyToMany
		  
		  @JoinTable( name = "student_section", joinColumns = @JoinColumn(name = "id"),
		  inverseJoinColumns = @JoinColumn(name = "section_id")) private Set<Section>
		  sectionSelection = new HashSet<Section>();
		 
	 

	public Student() {

	}

	public Student(Long id, String email, String last_name, String first_name, String salt, String password, String major_id, String completed_courses) {
		this.id = id;
		this.email = email;
		this.last_name = last_name;
		this.first_name = first_name;
		this.salt = salt;
		this.password = password;
		this.major_id = major_id;
		//this.completed_courses = completed_courses;
	}

	/*	
	public Set<Courses> getCoursesTaken() {
		return coursesTaken;
	}

	public void setCoursesTaken(Set<Courses> coursesTaken) {
		this.coursesTaken = coursesTaken;
	}
	 */
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getLast_name(){
		return last_name;
	}

	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}

	public String getFirst_name() {
		return first_name;
	}

	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getMajor_id() {
		return major_id;
	}

	public void setMajor_id(String major_id) {
		this.major_id = major_id;
	}

	/*
	public String getCompleted_courses() {
		return completed_courses;
	}*/

	/*
	public void setCompleted_courses(String completed_courses) {
		this.completed_courses = completed_courses;
	}  */

	public void addCompletedCourse(Course completed_course)
	{	
		//add the courseTaken to the set of coursesTaken
		coursesTaken.add(completed_course);
	}
	
	public void removeCompletedCourse(Course completed_course)
	{
		coursesTaken.remove(completed_course);
	}

	public Set<Course> getCoursesTaken() {
		return coursesTaken;
	}

	public void setCoursesTaken(Set<Course> coursesTaken) {
		this.coursesTaken = coursesTaken;
	}
	
	public void addSectionSelection(Section sectionChosen) {
		sectionSelection.add(sectionChosen);
	}
	
	
	 public void deleteSectionSelection(Section sectionToDelete) 
	 {
		 sectionSelection.remove(sectionToDelete); 
	 }
	 
	public Set<Section> getSectionsChosen() {
		return sectionSelection;
	}
	public void setSectionSelection(Set<Section> sectionSelection) {
		this.sectionSelection = sectionSelection;
	}
	
}

