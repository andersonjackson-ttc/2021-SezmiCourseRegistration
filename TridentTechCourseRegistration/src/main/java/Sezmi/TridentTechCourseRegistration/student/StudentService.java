package Sezmi.TridentTechCourseRegistration.student;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@Transactional
public class StudentService {

	@Autowired
	private StudentRepository repository;

	//the ListAll method displays all of the Students in the student table
	public List<Student> listAll()
	{
		return repository.findAll();
	}

	//the save method saves a new Student to the Student table
	public void save (Student student)
	{
		repository.save(student);
	}

	//the get method finds a student by the id

	public Student get(Long id)
	{
		return repository.findById(id).get();
	}

	//the getEmail method finds a student by the email
	public Student getEmail(String email)
	{
		return repository.findByEmail(email);
	}
	
	//the getStudentMajor searches the repository for a student by their email and returns their major
	public StudentEmailMajor getStudentMajor(String email)
	{
		return repository.getStudentEmailMajors(email);
		
	}

	//the delete method deletes a student by the email
	public void delete(Long id)
	{
		repository.deleteById(id);;
	}
	
	//NEW added 12/2
	//the delete method deletes a student section selection by section id
	public void delete(String section_id)
	{
		//repository.deleteAllInBatch(section_id);
	}
}
