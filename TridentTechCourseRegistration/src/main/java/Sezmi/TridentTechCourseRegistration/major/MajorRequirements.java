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
import javax.persistence.Table;

@Entity
@Table(name = "majorrequirements")
public class MajorRequirements 
{
	//declare variable (i.e table categories) for the major table) and map them to the table
	private String majorid;		//this is the primary key
	private String majorname;
	private String courseids;
	
	//declare no arg constructor and full constructor
	public MajorRequirements()
	{}
	
	public MajorRequirements(String majorid, String majorname, String courseids)
	{
		this.majorid = majorid;
		this.majorname = majorname;
		this.courseids = courseids;
		
	}
	
	//Create the primary key with the ID tag. We might need to adjust the @GeneratedValue later
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "majorid", nullable = false, updatable = false)
	public String getMajorId()
	{
		return majorid;
	}
	
	//source created setters and getters


	public void setMajorId(String majorId) {
		this.majorid = majorId;
	}

	public String getMajorName() {
		return majorname;
	}

	public void setMajorName(String majorName) {
		this.majorname = majorName;
	}

	public String getCourseIds() {
		return courseids;
	}

	public void setCourseIds(String courseIds) {
		this.courseids = courseIds;
	}
	
}//end Major class
