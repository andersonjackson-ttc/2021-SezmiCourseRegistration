//Billy Stockton
//Team Sezmi
//10/10/21
//The completedCourses class is responsible for creating the framework for completedCourses objects to be created into beans by Spring Data JPA. 
package Sezmi.TridentTechCourseRegistration.completedCourses;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="completedCourses")
public class CompletedCourses 
{
	//Declare variables for column in the table
	private String email;
	private String courseId;
	private String grade;
	private String term;
	
	public CompletedCourses()
	{}
	
	public CompletedCourses(String email, String courseId, String grade, String term)
	{
		this.email = email;
		this.courseId = courseId;
		this.grade = grade;
		this.term = term;		
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
		
	@Column(name = "email")
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	@Column(name = "courseId")
	public String getCourseId() {
		return courseId;
	}

	public void setCourseId(String courseId) {
		this.courseId = courseId;
	}
	@Column(name = "grade")
	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}
	@Column(name = "term")
	public String getTerm() {
		return term;
	}

	public void setTerm(String term) {
		this.term = term;
	}
	






}
