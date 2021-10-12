package Sezmi.TridentTechCourseRegistration.major;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "major")
public class Major 
{
	//declare variables (i.e table categories) for the Major table
	private String major_id;
	private String major;
	
	//create no arg constructor
	public Major()
	{}

	//create full arg constructor
	public Major(String major_id, String major) 
	{
		this.major_id = major_id;
		this.major = major;
	}
	
	//Create the primary key within the database using the @ID tag.
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "major_id", nullable = false, updatable = false)
	public String getMajor_id()
	{
		return major_id;
	}

	@Column(name = "major_name", nullable = false, updatable = false)
	public String getMajor() {
		return major;
	}

	public void setMajor(String major) {
		this.major = major;
	}

	public void setMajor_id(String major_id) {
		this.major_id = major_id;
	}
	
	
	
	
	

}//end Major Class
