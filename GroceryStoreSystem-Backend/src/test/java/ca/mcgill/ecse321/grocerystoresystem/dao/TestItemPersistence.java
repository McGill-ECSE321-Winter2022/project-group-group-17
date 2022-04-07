package ca.mcgill.ecse321.grocerystoresystem.dao;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import ca.mcgill.ecse321.grocerystoresystem.model.InventoryType;
import ca.mcgill.ecse321.grocerystoresystem.model.Item;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class TestItemPersistence {

	@Autowired
	private ItemRepository itemRepository;
	
	/**
	 * Cleaning the database after the test
	 */
	@AfterEach
	public void clearDatabases() {
		this.itemRepository.deleteAll();
	}
	/**
	 * Read and Write test for Item Class.
	 */
	@Test
	public void testPersistAndLoadItem() {
		int itemPrice = 5;
		String name = "White Rice";
		int inventoryAmount = 10;
		boolean isDeliverable = true;
		String portionUnit = "kg";
		InventoryType type = InventoryType.perishable;
		
		Item item = new Item(name, itemPrice, inventoryAmount, isDeliverable, portionUnit, type);
		this.itemRepository.save(item);
		
		assertTrue(this.itemRepository.existsByItemID(item.getItemID()));
		
	}
}
