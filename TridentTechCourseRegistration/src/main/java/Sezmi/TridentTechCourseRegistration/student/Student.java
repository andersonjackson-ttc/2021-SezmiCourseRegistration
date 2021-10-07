package Sezmi.TridentTechCourseRegistration.student;

import javax.annotation.processing.Generated;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "student")

public class Student{
	
	//declare variables for column in the table
	private String email;
	private String lastName;
	private String firstName;
	private String password;
	private String majorId;
	
	public Student() {
		
	}
		
	public Student(String email, String lastName, String firstName, String password, String majorId) {
		this.email = email;
		this.lastName = lastName;
		this.firstName = firstName;
		this.password = password;
		this.majorId = majorId;
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "email", nullable = false, updatable = false)
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	@Column(name = "lastName")
	public String getLastName(){
		return lastName;
	}
	
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	@Column(name = "firstName")
	public String getFirstName() {
		return firstName;
	}
	
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	@Column(name = "password")
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	@Column(name = "majorId")
	public String getMajorId() {
		return majorId;
	}
	
	public void setMajorId(String majorId) {
		this.majorId = majorId;
	}
}
	
	