package ca.mcgill.ecse321.grocerystoresystem.dao;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import ca.mcgill.ecse321.grocerystoresystem.model.SpecialDay;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class TestSpecialDayPersistence {
	@Autowired
	private SpecialDayRepository specialDayRepository;

	
	@AfterEach
	public void clearDatabases() {
		this.specialDayRepository.deleteAll();
	}
	
	@Test
	public void testPersistAndLoadSpecialDay() {
		LocalDateTime startTime = LocalDateTime.of(2021, 2, 13, 15, 56);
		LocalDateTime endTime = LocalDateTime.of(2021, 2, 14, 15, 56);
		
		SpecialDay specialDay = new SpecialDay(startTime, endTime);
		this.specialDayRepository.save(specialDay);
		
		assertTrue(this.specialDayRepository.existsBySpecialDayID(specialDay.getSpecialdayID()));
	}
}
