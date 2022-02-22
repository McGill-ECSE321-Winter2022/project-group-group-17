package ca.mcgill.ecse321.grocerystoresystem.dao;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.time.LocalTime;

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
	
	/**
	 * Cleaning the database after the test
	 */
	@AfterEach
	public void clearDatabases() {
		this.shiftRepository.deleteAll();
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

}
