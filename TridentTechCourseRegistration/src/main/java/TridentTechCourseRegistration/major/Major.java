//Jeremy Hunton
//Team Sezmi
//9/27/21
//The Major class is responsible for creating the framework for Major objects to be created into beans by Spring Data JPA. 
package TridentTechCourseRegistration.major;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Major 
{
	//declare variable (i.e table categories) for the major table)
	private String majorId;		//this is the primary key
	private String major;
	
	//declare no arg constructor and full constructor
	public Major()
	{}
	
	public Major(String majorId, String major)
	{
		this.majorId = majorId;
		this.major = major;
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

	public String getMajor() {
		return major;
	}

	public void setMajor(String major) {
		this.major = major;
	}

	public void setMajorId(String majorId) {
		this.majorId = majorId;
	}
}//end Major class
