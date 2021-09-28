package Sezmi.TridentTechCourseRegistration.product;

import org.springframework.data.jpa.repository.JpaRepository;

//the ProductRepository interface allows us to take advantage of the Spring Data JPA repository.
//The Spring Data JPA will access the data layer and develop a bean for the corresponding @Entity tag (used for Product class). 
//Spring Data JPA has all the boilerplate code for the repository, so all we need to do is create an interface to implement it. This 
//interface will allow us to use CRUD (Create, Read, Update, Delete) functions between the site and server without having to write a bunch of code. 

/*without the Spring Data JPA code, we would need to write something like this: 
 * https://stackify.com/wp-content/uploads/2017/05/CRUD-Example.png
 */
public interface ProductRepository extends JpaRepository<Product, Integer>{

}
