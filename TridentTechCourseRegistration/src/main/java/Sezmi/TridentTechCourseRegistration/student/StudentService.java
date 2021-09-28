package Sezmi.TridentTechCourseRegistration.student;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//the purpose of the StudentService class is to handle the Student class messaging on the Service layer (aka, business layer).
//This will manage all of the data from the server and communicate it to the StudentController. 


//@Component/@Service declares that StudentService objects will be created into beans. This means
//StudentService objects no longer need to be instantiated and can be directly injected (Spring Boot will create these objects using 
//the framework we provided, and then delete them when they aren't being used). While @Component and @Service do the same thing,
//we are using @Service to show that this is on the Service layer. 
@Service
@Transactional
public class StudentService {
	
			@Autowired //Autowired is used to automatically create Student beans using the StudentRepository interface (set to repo object). 
			private StudentRepository repo;
			
			public List<Student> listAll()
			{
				return repo.findAll();		//find all the students in the repository interface that have been saved. 
			}
			
			public void save(Student student)
			{
				repo.save(student);			//add the ability to save the student in the repository interface. 
			}
			
			public Student get(Integer id)
			{
				return repo.findById(id).get();		//add the ability to find student by their id
			}
			
			public void delete(Integer id)
			{
				repo.deleteById(id);				//add the ability to delete the student by their id. 
			}

}
