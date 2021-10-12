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
	private String majorid;		//this is the primary key
	private String courseId;
	
	//declare no arg constructor and full constructor
	public MajorRequirements()
	{}
	
	public MajorRequirements(String majorid, String courseid)
	{
		this.majorid = majorid;
		this.courseId = courseid;
		
	}
	
	//Create the primary key with the ID tag. We might need to adjust the @GeneratedValue later
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "major_id", nullable = false, updatable = false)
	public String getMajorId()
	{
		return majorid;
	}
	
	//source created setters and getters


	public void setMajorId(String majorId) {
		this.majorid = majorId;
	}

	//was getCourseId
	@Column(name = "course_id", nullable = false, updatable = false)
	public String getJames() {
		return courseId;
	}

	//was setCourseId
	public void setJames(String courseId) {
		this.courseId = courseId;
	}
	
}//end Major class
