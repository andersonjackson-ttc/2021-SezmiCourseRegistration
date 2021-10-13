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
	private String pre_reqs;
	
	//declare constructors with no args, and all args
	public Courses()
	{}
 
	
	public Courses(String course_id, String course_name, String course_type, String pre_reqs)
	{
		this.course_id = course_id;
		this.course_name = course_name;
		this.course_type = course_type;
		this.pre_reqs = pre_reqs;
	}
	
	//create the ID with the ID tag. I'm leaving out the @GeneratedValue tag since we are using a string for the PK. 
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "course_id", nullable = false, updatable = false)
	public String getCourse_id()
	{
		return course_id;
	}

	@Column(name = "course_name", nullable = false, updatable = false)
	public String getCourse_name() {
		return course_name;
	}

	public void setCourse_name(String course_name) {
		this.course_name = course_name;
	}

	@Column(name = "pre_reqs")
	public String getPre_reqs() {
		return pre_reqs;
	}

	public void setPre_reqs(String pre_reqs) {
		this.pre_reqs = pre_reqs;
	}

	public void setCourse_id(String course_id) {
		this.course_id = course_id;
	}

	@Column(name = "course_type")
	public String getCourse_type() {
		return course_type;
	}

	public void setCourse_type(String course_type) {
		this.course_type = course_type;
	}
	
	//auto generated setters/getters
	

}//end Course class
