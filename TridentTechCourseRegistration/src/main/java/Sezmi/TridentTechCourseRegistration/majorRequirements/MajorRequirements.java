//Jeremy Hunton
//Team Sezmi
//9/28/21
//The Major class is responsible for creating the framework for Major objects to be created into beans by Spring Data JPA. 
package Sezmi.TridentTechCourseRegistration.majorRequirements;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "major_requirements")
public class MajorRequirements 
{
	//declare variable (i.e table categories) for the major table) and map them to the table
	private String major_id;		//this is the primary key
	private String course_id;
	
	//declare no arg constructor and full constructor
	public MajorRequirements()
	{}
	
	public MajorRequirements(String major_id, String course_id)
	{
		this.major_id = major_id;
		this.course_id = course_id;
		
	}
	
	//Create the primary key with the ID tag. We might need to adjust the @GeneratedValue later
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "major_id", nullable = false, updatable = false)
	

	public String getMajor_id() {
		return major_id;
	}

	public void setMajor_id(String major_id) {
		this.major_id = major_id;
	}
	//was getJames. make sure to fix in JS
	public String getCourse_id() {
		return course_id;
	}
	//was setJames
	public void setCourse_id(String course_id) {
		this.course_id = course_id;
	}
	
}//end Major_requirements class
