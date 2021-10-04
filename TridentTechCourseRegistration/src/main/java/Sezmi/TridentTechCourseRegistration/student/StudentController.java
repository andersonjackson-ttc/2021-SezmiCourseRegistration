package Sezmi.TridentTechCourseRegistration.student;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//the StudentController contains all the resources for the Student API. This will take incoming information from the Service layer
//and post it to the @RequestMapping path declared. 
@RestController 								//@RestController declares that this is an API controller.


public class StudentController {
  
	//declare class constructor that passes the studentService object.
		@Autowired 									//@Autowired allows for dependency injection (this keeps us from creating an ever expanding list 
													//of new objects [i.e. new studentService])
	//declare the studentService object that handles the Service layer information for the Student class object. 
	private StudentService studentService;
	
	@RequestMapping(path = "/students") 		//@RequestMapping declares the URL where the API will post.	
	public List<Student> list()
	{
		return studentService.listAll();
	}
	
}
