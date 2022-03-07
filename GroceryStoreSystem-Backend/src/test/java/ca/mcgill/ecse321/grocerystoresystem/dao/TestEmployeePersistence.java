package ca.mcgill.ecse321.grocerystoresystem.dao;

import ca.mcgill.ecse321.grocerystoresystem.model.Address;
import ca.mcgill.ecse321.grocerystoresystem.model.Customer;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import ca.mcgill.ecse321.grocerystoresystem.model.Employee;
import ca.mcgill.ecse321.grocerystoresystem.model.EmployeeStatus;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class TestEmployeePersistence {

	@Autowired
	private EmployeeRepository employeeRepository;

	@Autowired
	private AddressRepository addressRepository;
	
	/**
	 * Cleaning the database after the test
	 */
	@AfterEach()
	public void clearDatabases() {
		this.employeeRepository.deleteAll();
		this.addressRepository.deleteAll();
	}
	
	/**
	 * Read and Write test for Employee Class.
	 */
	@Test
	public void testPersistAndLoadEmployee() {
		String first_name = "James";
		String last_name = "White";
		
		String email = "james.white@gmail.com";
		String password = "12345678";
		EmployeeStatus status = EmployeeStatus.hired;
		boolean loginStatus = true;
		
		Employee employee = new Employee(first_name, last_name, email, password, status,loginStatus);
		this.employeeRepository.save(employee);
		
		assertTrue(this.employeeRepository.existsByPersonID(employee.getPersonID()));
	}

	@Test
	public void testPersistAndLoadEmployeeAddress() {
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

		EmployeeStatus empStatus = EmployeeStatus.resigned;

		Employee customer = new Employee(first_name, last_name, email, password, empStatus, address, loginStatus);
		this.employeeRepository.save(customer);

		Employee retrievedEmployee = this.employeeRepository.findEmployeeByPersonID(customer.getPersonID());
		assertNotNull(retrievedEmployee);

		Address retrievedAddress = retrievedEmployee.getAddress();
		assertNotNull(retrievedAddress);
		assertEquals(retrievedAddress.getCountry(), address.getCountry());
		assertEquals(retrievedAddress.getCity(), address.getCity());
	}
}
