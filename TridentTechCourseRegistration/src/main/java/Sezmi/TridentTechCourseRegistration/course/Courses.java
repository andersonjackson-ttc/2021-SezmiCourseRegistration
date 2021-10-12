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
@Table(name = "courses")
public class Courses 
{
	//declare variables
	private String course_id;
	private String course_name;
	private String course_type;
	private String prereqs;
	
	//declare constructors with no args, and all args
	public Courses()
	{}
 
	
	public Courses(String course_id, String course_name, String course_type, String prereqs)
	{
		this.course_id = course_id;
		this.course_name = course_name;
		this.course_type = course_type;
		this.prereqs = prereqs;
	}
	
	//create the ID with the ID tag. I'm leaving out the @GeneratedValue tag since we are using a string for the PK. 
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "course_id", nullable = false, updatable = false)
	public String getCourseID()
	{
		return course_id;
	}

	@Column(name = "course_name", nullable = false, updatable = false)
	public String getCourseName() {
		return course_name;
	}

	public void setCourseName(String courseName) {
		this.course_name = courseName;
	}

	@Column(name = "pre_reqs")
	public String getPrereqs() {
		return prereqs;
	}

	public void setPrereqs(String prereqs) {
		this.prereqs = prereqs;
	}

	public void setCourseID(String courseID) {
		this.course_id = courseID;
	}

	@Column(name = "course_type")
	public String getCourseType() {
		return course_type;
	}

	public void setCourseType(String courseType) {
		this.course_type = courseType;
	}
	
	//auto generated setters/getters
	

}//end Course class
