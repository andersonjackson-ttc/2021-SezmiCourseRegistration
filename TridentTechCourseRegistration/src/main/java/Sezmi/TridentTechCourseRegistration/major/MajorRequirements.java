//Jeremy Hunton
//Team Sezmi
//9/28/21
//The Major class is responsible for creating the framework for Major objects to be created into beans by Spring Data JPA. 
package Sezmi.TridentTechCourseRegistration.major;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class MajorRequirements 
{
	//declare variable (i.e table categories) for the major table)
	private String majorId;		//this is the primary key
	private String majorName;
	private String courseIds;
	
	//declare no arg constructor and full constructor
	public MajorRequirements()
	{}
	
	public MajorRequirements(String majorId, String majorName, String courseIds)
	{
		this.majorId = majorId;
		this.majorName = majorName;
		this.courseIds = courseIds;
		
	}
	
	//Create the primary key with the ID tag. We might need to adjust the @GeneratedValue later
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "majorId", nullable = false, updatable = false)
	public String getMajorId()
	{
		return majorId;
	}
	
	//source created setters and getters


	public void setMajorId(String majorId) {
		this.majorId = majorId;
	}

	public String getMajorName() {
		return majorName;
	}

	public void setMajorName(String majorName) {
		this.majorName = majorName;
	}

	public String getCourseIds() {
		return courseIds;
	}

	public void setCourseIds(String courseIds) {
		this.courseIds = courseIds;
	}
	
}//end Major class
