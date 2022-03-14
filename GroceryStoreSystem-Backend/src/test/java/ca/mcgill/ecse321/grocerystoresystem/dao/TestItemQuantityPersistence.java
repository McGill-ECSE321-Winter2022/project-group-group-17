package ca.mcgill.ecse321.grocerystoresystem.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import ca.mcgill.ecse321.grocerystoresystem.model.DeliveryOrder;
import ca.mcgill.ecse321.grocerystoresystem.model.InventoryType;
import ca.mcgill.ecse321.grocerystoresystem.model.Item;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import ca.mcgill.ecse321.grocerystoresystem.model.ItemQuantity;

import java.time.LocalDateTime;
import java.util.List;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class TestItemQuantityPersistence {

	@Autowired
	private DeliveryOrderRepository deliveryOrderRepository;

	@Autowired
	private ItemRepository itemRepository;

	@Autowired
	private ItemQuantityRepository itemQuantityRepository;
	
	/**
	 * Cleaning the database after the test
	 */
	@AfterEach
	public void clearDatabases() {

		this.itemQuantityRepository.deleteAll();

		this.deliveryOrderRepository.deleteAll();

		this.itemRepository.deleteAll();
	}
	
	/**
	 * Read and Write test for ItemQuantity Class.
	 */
	@Test
	public void testPersistAndLoadItemQuantity() {
		int itemNum = 5;
		ItemQuantity itemQuantity = new ItemQuantity(itemNum);
		this.itemQuantityRepository.save(itemQuantity);
		
		assertTrue(this.itemQuantityRepository.existsByQuantityID(itemQuantity.getQuantityID()));
	}

	@Test
	public void testPersistAndLoadMultipleQuantitiesItem() {
		int itemPrice = 5;
		String name = "White Rice";
		int inventoryAmount = 10;
		boolean isDeliverable = true;
		String portionUnit = "kg";
		InventoryType type = InventoryType.perishable;

		Item item = new Item(name, itemPrice, inventoryAmount, isDeliverable, portionUnit, type);
		this.itemRepository.save(item);

		int itemNum = 5;
		ItemQuantity itemQuantity = new ItemQuantity(itemNum);
		itemQuantity.setSpecificItem(item);
		this.itemQuantityRepository.save(itemQuantity);

		int itemNum2 = 10;
		ItemQuantity itemQuantity2 = new ItemQuantity(itemNum2);
		itemQuantity2.setSpecificItem(item);
		this.itemQuantityRepository.save(itemQuantity2);


		List<ItemQuantity> quantities = this.itemQuantityRepository.findItemQuantitiesBySpecificItemItemID(item.getItemID());

		assertEquals(quantities.size(), 2);
	}


	@Test
	public void testPersistAndLoadMultipleQuantitiesOrder() {
		int totalCost = 100;
		LocalDateTime orderTime = LocalDateTime.of(2021, 12, 28, 15, 36);
		LocalDateTime deliveryTime = LocalDateTime.of(2021, 12, 29, 9, 30);
		boolean isPaid = true;

		DeliveryOrder order = new DeliveryOrder(totalCost, orderTime, isPaid, deliveryTime);
		this.deliveryOrderRepository.save(order);

		int itemNum = 5;
		ItemQuantity itemQuantity = new ItemQuantity(itemNum);
		itemQuantity.setOrder(order);
		this.itemQuantityRepository.save(itemQuantity);

		int itemNum2 = 10;
		ItemQuantity itemQuantity2 = new ItemQuantity(itemNum2);
		itemQuantity2.setOrder(order);
		this.itemQuantityRepository.save(itemQuantity2);


		List<ItemQuantity> quantities = this.itemQuantityRepository.findItemQuantitiesByOrderOrderID(order.getOrderID());

		assertEquals(quantities.size(), 2);
	}
}
