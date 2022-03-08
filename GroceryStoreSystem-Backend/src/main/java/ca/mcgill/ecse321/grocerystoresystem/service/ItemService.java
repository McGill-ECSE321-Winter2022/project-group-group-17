package ca.mcgill.ecse321.grocerystoresystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ca.mcgill.ecse321.grocerystoresystem.dao.ItemRepository;
import ca.mcgill.ecse321.grocerystoresystem.model.InventoryType;
import ca.mcgill.ecse321.grocerystoresystem.model.Item;

@Service
public class ItemService {

	@Autowired
	ItemRepository itemRepository;
	
	@Transactional
	public Item createItem() {
		Item item = new Item();
		
		itemRepository.save(item);
		
		return item;
	}
	
	@Transactional
	public Item createItem(String name, int itemPrice, int inventoryAmount, boolean isDeliverable, String portionUnit, InventoryType inventoryType, int itemID) {
		
		validateItemPrice(itemPrice);
		validateInventoryAmount(inventoryAmount);
		
		Item item = new Item(name, itemPrice, inventoryAmount, isDeliverable, portionUnit, inventoryType, itemID);
		
		itemRepository.save(item);
		return item;
		
	}
	
	
	private void validateItemPrice(int itemPrice) {
		if (itemPrice < 0) throw new IllegalArgumentException("Please submit a valid item price.");
	}
	
	private void validateInventoryAmount(int inventoryAmount) {
		if (inventoryAmount < 0) throw new IllegalArgumentException("Please submit a valid inventory amount.");
	}
	
	
}
