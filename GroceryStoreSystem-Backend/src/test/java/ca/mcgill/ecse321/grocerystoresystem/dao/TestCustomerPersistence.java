package ca.mcgill.ecse321.grocerystoresystem.dao;

import ca.mcgill.ecse321.grocerystoresystem.model.Address;
import ca.mcgill.ecse321.grocerystoresystem.model.Person;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import ca.mcgill.ecse321.grocerystoresystem.model.Customer;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class TestCustomerPersistence {

	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	private AddressRepository addressRepository;
	
	/**
	 * Cleaning the database after the test
	 */
	@AfterEach
	public void clearDatabases() {
		this.customerRepository.deleteAll();
		this.addressRepository.deleteAll();
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
		boolean loginStatus = true;
		Customer customer = new Customer(first_name, last_name, email, password,loginStatus);
		this.customerRepository.save(customer);
		
		assertTrue(this.customerRepository.existsById(customer.getPersonID()));
	}

	@Test
	public void testPersistAndLoadCustomerAddress() {
		String streetName = "Huntington Street";
		String streetNum = "123";
		String city = "DrummondVille";
		String postalCode = "H3A1B9";
		String country = "Canada";
		boolean isLocal = true;

		Address address = new Address(streetName, streetNum, city, postalCode, country, isLocal);

		this.addressRepository.save(address);

		String first_name = "Mario";
		String last_name = "Bouzakhm";

		String email = "mariobouzakhm03@gmail.com";
		String password = "12345678";
		boolean loginStatus = true;

		Customer customer = new Customer(first_name, last_name, email, password, address,loginStatus);
		this.customerRepository.save(customer);

		Customer retrievedCustomer = this.customerRepository.findCustomerByPersonID(customer.getPersonID());
		assertNotNull(retrievedCustomer);

		Address retrievedAddress = retrievedCustomer.getAddress();
		assertNotNull(retrievedAddress);
		assertEquals(retrievedAddress.getCountry(), address.getCountry());
		assertEquals(retrievedAddress.getCity(), address.getCity());
	}
}
