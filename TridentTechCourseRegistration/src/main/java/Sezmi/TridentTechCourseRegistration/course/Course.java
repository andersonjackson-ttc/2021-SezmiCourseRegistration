//Jeremy Hunton
//Team Sezmi
//9/27/21
//The Course class is responsible for creating the framework for Course objects to be created into beans by Spring Data JPA. 
package Sezmi.TridentTechCourseRegistration.course;


import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import Sezmi.TridentTechCourseRegistration.section.Section;

//the Course class creates course obj framework to be stored in the database. 
@Entity
@Table(name = "course")
public class Course 
{
	//declare variables
	//create the ID with the ID tag. I'm leaving out the @GeneratedValue tag since we are using a string for the PK. 
	@Id
	@Column(name = "course_id", nullable = false, updatable = false)
	private String course_id;
	
	@Column(name = "course_name", nullable = false, updatable = false)
	private String course_name;
	
	@Column(name = "course_type")
	private String course_type;
	
	//declare a set for the sections available for each course
		@ManyToMany
		@JoinTable(
				name = "course_section",
				joinColumns = @JoinColumn(name = "course_id"),
				inverseJoinColumns = @JoinColumn(name = "section_id"))
		private Set<Section> availableSections = new HashSet<Section>();

	//declare a set for the pre-req courses needed for each course
		@ManyToMany
		@JoinTable(
				name = "course_prereq",
				joinColumns = @JoinColumn(name = "course_id"),
				inverseJoinColumns = @JoinColumn(name = "course_prereq"))
		private Set<Course> preReqCourses = new HashSet<Course>();
		
	//declare constructors with no args, and all args
	public Course()
	{}


	public Course(String course_id, String course_name, String course_type)
	{
		this.course_id = course_id;
		this.course_name = course_name;
		this.course_type = course_type;
	}


	public String getCourse_id()
	{
		return course_id;
	}

	
	public String getCourse_name() {
		return course_name;
	}

	public void setCourse_name(String course_name) {
		this.course_name = course_name;
	}

	public void setCourse_id(String course_id) {
		this.course_id = course_id;
	}

	
	public String getCourse_type() {
		return course_type;
	}

	public void setCourse_type(String course_type) {
		this.course_type = course_type;
	}

	//auto generated setters/getters
	public Set<Section> getAvailableSections() {
		return availableSections;
	}

	public void setAvailableSections(Set<Section> availableSections) {
		this.availableSections = availableSections;
	}


	public Set<Course> getPreReqCourses() {
		return preReqCourses;
	}


	public void setPreReqCourses(Set<Course> preReqCourses) {
		this.preReqCourses = preReqCourses;
	}
	
	

}//end Course class
