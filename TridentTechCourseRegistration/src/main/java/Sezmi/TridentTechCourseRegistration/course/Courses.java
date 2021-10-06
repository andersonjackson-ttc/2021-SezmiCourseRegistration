//Jeremy Hunton
//Team Sezmi
//9/27/21
//The Course class is responsible for creating the framework for Course objects to be created into beans by Spring Data JPA. 
package Sezmi.TridentTechCourseRegistration.course;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

//the Course class creates course obj framework to be stored in the database. 
@Entity
@Table(name = "Courses")
public class Courses 
{
	//declare variables
	private String courseID;
	private String courseName;
	private String courseType;
	private String prereqs;
	
	//declare constructors with no args, and all args
	public Courses()
	{}
	
	public Courses(String courseID, String courseName, String courseType, String prereqs)
	{
		this.courseID = courseID;
		this.courseName = courseName;
		this.courseType = courseType;
		this.prereqs = prereqs;
	}
	
	//create the ID with the ID tag. I'm leaving out the @GeneratedValue tag since we are using a string for the PK. Might revisit later. 
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "CourseID", nullable = false, updatable = false)
	public String getCourseID()
	{
		return courseID;
	}

	@Column(name = "CourseName", nullable = false, updatable = false)
	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	@Column(name = "PreReqs")
	public String getPrereqs() {
		return prereqs;
	}

	public void setPrereqs(String prereqs) {
		this.prereqs = prereqs;
	}

	public void setCourseID(String courseID) {
		this.courseID = courseID;
	}

	@Column(name = "CourseType")
	public String getCourseType() {
		return courseType;
	}

	public void setCourseType(String courseType) {
		this.courseType = courseType;
	}
	
	//auto generated setters/getters
	

}//end Course class
