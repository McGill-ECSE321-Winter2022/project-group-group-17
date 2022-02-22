package ca.mcgill.ecse321.grocerystoresystem.dao;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import ca.mcgill.ecse321.grocerystoresystem.model.ItemQuantity;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class TestItemQuantityPersistence {

	@Autowired
	private ItemQuantityRepository itemQuantityRepository;
	
	@AfterEach
	public void clearDatabases() {
		this.itemQuantityRepository.deleteAll();
	}
	
	@Test
	public void testPersistAndLoadItemQuantity() {
		int itemNum = 5;
		ItemQuantity itemQuantity = new ItemQuantity(itemNum);
		this.itemQuantityRepository.save(itemQuantity);
		
		assertTrue(this.itemQuantityRepository.existsByQuantityID(itemQuantity.getQuantityID()));
	}
}
