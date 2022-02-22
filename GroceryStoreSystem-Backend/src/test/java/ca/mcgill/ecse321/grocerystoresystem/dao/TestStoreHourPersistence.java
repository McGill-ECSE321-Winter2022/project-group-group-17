package ca.mcgill.ecse321.grocerystoresystem.dao;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalTime;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import ca.mcgill.ecse321.grocerystoresystem.model.StoreHour;
import ca.mcgill.ecse321.grocerystoresystem.model.Weekdays;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class TestStoreHourPersistence {
	@Autowired
	private StoreHourRepository storeHourRepository;
	
	/**
	 * Cleaning the database after the test
	 */
	@AfterEach
	public void clearDatabases() {
		this.storeHourRepository.deleteAll();
	}
	
	/**
	 * Read and Write test for StoreHour Class.
	 */
	@Test
	public void testPersistAndLoadStoreHour() {
		LocalTime startTime = LocalTime.of(8, 30);
		LocalTime endTime = LocalTime.of(16, 0);
		Weekdays weekDays = Weekdays.MONDAY;
		
		StoreHour storeHour = new StoreHour(startTime, endTime, weekDays);
		this.storeHourRepository.save(storeHour);
		
		assertTrue(this.storeHourRepository.existsByStoreHourID(storeHour.getStoreHourID()));
	}

}
