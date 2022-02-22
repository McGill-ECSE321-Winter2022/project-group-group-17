package ca.mcgill.ecse321.grocerystoresystem.dao;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import ca.mcgill.ecse321.grocerystoresystem.model.Customer;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class TestCustomerPersistence {

	@Autowired
	private CustomerRepository customerRepository;
	
	/**
	 * Cleaning the database after the test
	 */
	@AfterEach
	public void clearDatabases() {
		this.customerRepository.deleteAll();
	}
	
	/**
	 * Read and Write test for Customer Class.
	 */
	@Test
	public void testPersistAndLoadCustomer() {
		
		String first_name = "Mario";
		String last_name = "Bouzakhm";
		
		String email = "mariobouzakhm03@gmail.com";
		String password = "12345678";
		
		Customer customer = new Customer(first_name, last_name, email, password);
		this.customerRepository.save(customer);
		
		assertTrue(this.customerRepository.existsById(customer.getPersonID()));
	}
}
