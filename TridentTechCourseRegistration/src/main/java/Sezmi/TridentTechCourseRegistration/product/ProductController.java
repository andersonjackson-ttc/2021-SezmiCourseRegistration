package Sezmi.TridentTechCourseRegistration.product;

//the ProductController class is responsible for handling the RESTful services and APIs for CRUD operations
//(this communicates with the site). 

import java.util.*;

import org.springframework.beans.factory.annotation.*;
import org.springframework.http.*;

import org.springframework.web.bind.annotation.*;

@RestController 				//@RestController assigns this class as an API Rest controller.
								//This allows reading JSON data from the request and includes JSON data to the response. 
public class ProductController 
{
	@Autowired					//this is using dependency injection again. This creates a bean and adds it to the bean factory instead of having to
								//create a new object every time (Spring Boot creates the object automatically). 
	private ProductService service;

	
	/* when building an API controller, the key methods needed are:
	 * 1. RESTful API method(s) for Retrieval operations
	 * 2. RESTful API method for Create operation
	 * 3. RESTful API method for Update operation
	 * 4. RESTful API method for Delete operation
	 */
	
	//RESTful API for retrieval operations. This method will allow ALL of the products from the product table to be posted in JSON objects to the url
	//localhost:8080/products
	@GetMapping("/products")		//@GetMapping assigns the URL link for the GET request to the server, and posts it to the URL.
	public List<Product> list()
	{
		return service.listAll();
	}//end RESTful API retrieval operation
	
	//RESTful API for retrieval operations again. This method will allow the clients to get information about a specific product based on the ID.
	//The response will be to localhost:8080/product/{idNumberHere}
	@GetMapping("/products/{id}")				//@GetMapping assigns the URL link for the GET request to the server. 
	public ResponseEntity<Product> get(@PathVariable Integer id)			//ResponseEntity is the entity being returned, and @PathVariable is the 
	{																		//primary key variable that we are using for our search function
		//try to find the product
		try 
		{
			Product product = service.get(id);			//assign the product obj. bean to the get request from the service.
			return new ResponseEntity<Product>(product, HttpStatus.OK);		//declare the ResponseEntity to the URL, return the product bean found 
		}//end try block													//and set the HTTP status to OK.
		//catch that we cannot find the product
		catch (NoSuchElementException notFound) 		
		{
			return new ResponseEntity<Product>(HttpStatus.NOT_FOUND);		//respond that the product was not found and set the HTTP to not found.
		}//end catch no element found
	}//end RESTful API to search for a product by ID
	
	//RESTful API for create functions. This allows the user to create a product in the product table
	@PostMapping("/products")			//@PostMapping assigns the URL link for the POST annotation to the web/server. 
	public void add(@RequestBody Product product)
	{
		service.save(product); 			//call the API service method to save the product to the server. 
	}//end RESTful API create function. 
	
	//RESTful API for Update Operation. This allows the user to do a PUT request to change the product at a given ID
	@PutMapping("/products/{id}")					//@PutMapping commits a PUT request to the URL linked. 
	public ResponseEntity<?> update(@RequestBody Product product, @PathVariable Integer id)		//@RequestBody gets the obj, and @PathVariable gets the
	{																							//primary key info.
		try 
		{
			Product existProduct = service.get(id);				//find the existing product by the id and save the new product over it
			service.save(product);
			return new ResponseEntity<>(HttpStatus.OK);
		}//end try
		
		catch (NoSuchElementException e) 
		{
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}//end Update Operation
	
	//RESTful API for Delete Function. This allows the user to issue a DELETE request to remove the product. 
	@DeleteMapping("/products/{id}")
	public void delete(@PathVariable Integer id)
	{
		service.delete(id);				//call the delete function from the service to issue the delete command through the pipeline back to the server
	}
}//end of ProductController
