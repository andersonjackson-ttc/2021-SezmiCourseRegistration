package Sezmi.TridentTechCourseRegistration.section;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "section")
public class Section 
{
	//declare variables for column in the table
	private String sectionID;
	private String instructorID;
	private String schedule;
	private String term;
	private String duration;
	private String time;
	private String courseFormat;
	private String remainingSpace;
	private String courseID;
	
	public Section()
	{}

	public Section(String sectionID, String instructorID, String schedule, String term, String duration, String time,
			String courseFormat, String remainingSpace, String courseID) 
	{
		this.sectionID = sectionID;
		this.instructorID = instructorID;
		this.schedule = schedule;
		this.term = term;
		this.duration = duration;
		this.time = time;
		this.courseFormat = courseFormat;
		this.remainingSpace = remainingSpace;
		this.courseID = courseID;
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "SectionID", nullable = false, updatable = false)
	public String getSectionID()
	{
		return sectionID;
	}

	@Column(name = "InstructorID")
	public String getInstructorID() {
		return instructorID;
	}

	public void setInstructorID(String instructorID) {
		this.instructorID = instructorID;
	}

	@Column(name = "Schedule")
	public String getSchedule() {
		return schedule;
	}

	public void setSchedule(String schedule) {
		this.schedule = schedule;
	}

	@Column(name = "Term")
	public String getTerm() {
		return term;
	}

	public void setTerm(String term) {
		this.term = term;
	}

	@Column(name = "Duration")
	public String getDuration() {
		return duration;
	}

	public void setDuration(String duration) {
		this.duration = duration;
	}

	@Column(name = "Time")
	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	@Column(name = "CourseFormat")
	public String getCourseFormat() {
		return courseFormat;
	}

	public void setCourseFormat(String courseFormat) {
		this.courseFormat = courseFormat;
	}

	@Column(name = "RemainingSpaces")
	public String getRemainingSpace() {
		return remainingSpace;
	}

	public void setRemainingSpace(String remainingSpace) {
		this.remainingSpace = remainingSpace;
	}

	@Column(name = "CourseID")
	public String getCourseID() {
		return courseID;
	}

	public void setCourseID(String courseID) {
		this.courseID = courseID;
	}

	public void setSectionID(String sectionID) {
		this.sectionID = sectionID;
	}
	
	
	
	
	
	
	
	

}
