//Jeremy Hunton
//Team Sezmi
//9/27/21
package Sezmi.TridentTechCourseRegistration.product;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


//the ProductService class will act as the middle layer between the persistence layer (repository) and the controller layer (responsible for
//handling incoming API requests from users). 

					//@Service states that this class is on the Service layer
							//@Transactional creates a proxy class that implements the same interfaces (ProductRepository in this case). 
							//When calls are made to ProductService, the calls are intercepted and injected.
							/*This link goes into further detail about @Transactional : 
							 * https://stackoverflow.com/questions/1099025/spring-transactional-what-happens-in-background
							 */
@Service
@Transactional
public class ProductService 
{

	//@Autowired allows us to pull primitive data types and strings from the corresponding repository bean that has been saved in the 
	//bean factory. A bean is an object that was instantiated, assembled, and managed by the Spring IoC container (i.e created automatically).
	@Autowired			
	private ProductRepository repository;
	
	//the listAll method returns all the Product objects in the repository (ie what comes from the corresponding database table)
	public List<Product> listAll()
	{
		return repository.findAll();		//findAll is a method within the Spring Boot CrudRepository repository 
											//that allows us to use CRUD functions to communicate with the database.
											//This is implemented within the ProductRepository interface.
	}//end listAll
	
	//the save method allows product objects to be created into beans and saved within the ProductRepository interface.
	public void save(Product product)
	{
		repository.save(product);		//save is a method within the Spring CRUD repository
	}//end save method
	
	//get method gets a Product object from the ProductRepository by using its primary key, id.
	public Product get(Integer id)
	{
		return repository.findById(id).get(); //findById is a method within the Spring CRUD repository
	}//end get method
	
	//the delete method removes a Product bean from the ProductRepository (and in turn, the server).
	public void delete(Integer id)
	{
		repository.deleteById(id); 			//deleteById is a method within the Spring CRUD repository.
	}//end delete method
}//end ProductService class
