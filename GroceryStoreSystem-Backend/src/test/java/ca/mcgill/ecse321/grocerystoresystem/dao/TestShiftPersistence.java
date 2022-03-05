package ca.mcgill.ecse321.grocerystoresystem.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.time.LocalTime;

import ca.mcgill.ecse321.grocerystoresystem.model.Employee;
import ca.mcgill.ecse321.grocerystoresystem.model.EmployeeStatus;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import ca.mcgill.ecse321.grocerystoresystem.model.Shift;
import ca.mcgill.ecse321.grocerystoresystem.model.ShiftStatus;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class TestShiftPersistence {

	@Autowired
	private ShiftRepository shiftRepository;

	@Autowired
	private EmployeeRepository employeeRepository;
	
	/**
	 * Cleaning the database after the test
	 */
	@AfterEach
	public void clearDatabases() {

		this.shiftRepository.deleteAll();

		this.employeeRepository.deleteAll();
	}
	
	/**
	 * Read and Write test for Shift Class.
	 */
	@Test
	public void testPersistAndLoadShift() {
		LocalDate date = LocalDate.of(2021, 10, 28);
		LocalTime startTime = LocalTime.of(12, 0);
		LocalTime endTime = LocalTime.of(20, 0);
		ShiftStatus status = ShiftStatus.available;
		
		Shift shift = new Shift(date, startTime, endTime, status);
		this.shiftRepository.save(shift);
		
		assertTrue(this.shiftRepository.existsByShiftID(shift.getShiftID()));
	}

	@Test
	public void testPersistAndLoadShiftEmployee() {
		String first_name = "James";
		String last_name = "White";

		String email = "james.white@gmail.com";
		String password = "12345678";
		EmployeeStatus status = EmployeeStatus.hired;

		Employee employee = new Employee(first_name, last_name, email, password, status);
		this.employeeRepository.save(employee);

		LocalDate date = LocalDate.of(2021, 10, 28);
		LocalTime startTime = LocalTime.of(12, 0);
		LocalTime endTime = LocalTime.of(20, 0);
		ShiftStatus shiftStatus = ShiftStatus.available;

		Shift shift = new Shift(date, startTime, endTime, shiftStatus, employee);
		this.shiftRepository.save(shift);

		assertTrue(this.shiftRepository.existsByShiftID(shift.getShiftID()));

		Shift retrievedShift = this.shiftRepository.findShiftByShiftID(shift.getShiftID());
		assertEquals(retrievedShift.getEmployee().getPersonID(), shift.getEmployee().getPersonID());
		assertEquals(retrievedShift.getEmployee().getFirstName(), shift.getEmployee().getFirstName());
		assertEquals(retrievedShift.getEmployee().getLastName(), shift.getEmployee().getLastName());
	}

}
