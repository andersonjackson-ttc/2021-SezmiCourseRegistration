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
	
	//the get method finds a student by the email
	
	public Student get(Long id)
	{
		return repository.findById(id).get();
	}
	
	//the delete method deletes a student by the email
	public void delete(Long id)
	{
		repository.deleteById(id);;
	}
}
