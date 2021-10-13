package Sezmi.TridentTechCourseRegistration.student;

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
	private String last_name;
	private String first_name;
	private String password;
	private String major_id;
	
	public Student() {
		
	}
		
	public Student(String email, String last_name, String first_name, String password, String major_id) {
		this.email = email;
		this.last_name = last_name;
		this.first_name = first_name;
		this.password = password;
		this.major_id = major_id;
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
	
	@Column(name = "last_name")
	public String getLast_name(){
		return last_name;
	}
	
	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}
	
	@Column(name = "first_name")
	public String getFirst_name() {
		return first_name;
	}
	
	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}
	
	@Column(name = "password")
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	@Column(name = "major_id")
	public String getMajor_id() {
		return major_id;
	}
	
	public void setMajor_id(String major_id) {
		this.major_id = major_id;
	}
}
	
	