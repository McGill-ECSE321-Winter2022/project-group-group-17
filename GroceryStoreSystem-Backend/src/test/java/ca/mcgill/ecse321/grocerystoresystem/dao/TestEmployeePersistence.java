package ca.mcgill.ecse321.grocerystoresystem.dao;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import ca.mcgill.ecse321.grocerystoresystem.model.Employee;
import ca.mcgill.ecse321.grocerystoresystem.model.EmployeeStatus;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class TestEmployeePersistence {

	@Autowired
	private EmployeeRepository employeeRepository;
	
	/**
	 * Cleaning the database after the test
	 */
	@AfterEach()
	public void clearDatabases() {
		this.employeeRepository.deleteAll();
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
		
		Employee employee = new Employee(first_name, last_name, email, password, status);
		this.employeeRepository.save(employee);
		
		assertTrue(this.employeeRepository.existsByPersonID(employee.getPersonID()));
	}
}
