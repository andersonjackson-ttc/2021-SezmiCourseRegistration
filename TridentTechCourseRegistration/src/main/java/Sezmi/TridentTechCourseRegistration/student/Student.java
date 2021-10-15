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
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false, unique = true, length = 45)
	private String email;
	
	@Column(nullable = false, length = 20)
	private String last_name;
	
	@Column(nullable = false, length = 20)
	private String first_name;
	
	@Column(nullable = false, length = 64)
	private String password;
	
	@Column(nullable = false, length = 25)
	private String major_id;
	
	public Student() {
		
	}
		
	public Student(Long id, String email, String last_name, String first_name, String password, String major_id) {
		this.id = id;
		this.email = email;
		this.last_name = last_name;
		this.first_name = first_name;
		this.password = password;
		this.major_id = major_id;
	}
	
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getLast_name(){
		return last_name;
	}
	
	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}
	
	public String getFirst_name() {
		return first_name;
	}
	
	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getMajor_id() {
		return major_id;
	}
	
	public void setMajor_id(String major_id) {
		this.major_id = major_id;
	}
}
	
	