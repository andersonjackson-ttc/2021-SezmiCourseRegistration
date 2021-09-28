package Sezmi.TridentTechCourseRegistration.student;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

//the purpose of student class is to create a student object. Following along with Spring Boot Tutorial. 

@Entity //specifies that this class is going to be an entity (needed for JPAs)
@Table	//specifies that this class is going to be used to create a table. 
public class Student 
{
	
	private Long id;
	private String name;
	private int age; 
	private String email;
	private LocalDate dob;
	
	
	//generated constructor for student without an id
	public Student(String name, int age, String email, LocalDate dob) {
		this.name = name;
		this.age = age;
		this.email = email;
		this.dob = dob;
	}
	
	//generated no args constructor
	public Student() {
		super();
	}
	
	//generated constructor for student with full information
	public Student(Long id, String name, int age, String email, LocalDate dob) {
		this.id = id;
		this.name = name;
		this.age = age;
		this.email = email;
		this.dob = dob;
	}
	//generated getters and setters
	
	@Id				//specifies the primary key of a table being created
	@SequenceGenerator( name = "student_sequence",						//@SequenceGenerator creates a block of code to create the formula for
						sequenceName = "student_sequence",				//generating a primary key for a Student object in the table.  
						allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE,					//@GeneratedValue calls the SequenceGenerator (above) to generate the id (NAME).
					generator = "student_sequence")
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public LocalDate getDob() {
		return dob;
	}
	public void setDob(LocalDate dob) {
		this.dob = dob;
	}

	
	//generated toString to send the student object over the API. 
	@Override
	public String toString() {
		return "Student [id=" + id + ", name=" + name + ", age=" + age + ", email=" + email + ", dob=" + dob + "]";
	}
	
	
	
	
}
