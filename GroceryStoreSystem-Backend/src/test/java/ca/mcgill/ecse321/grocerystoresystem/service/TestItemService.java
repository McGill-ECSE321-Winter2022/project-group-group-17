package ca.mcgill.ecse321.grocerystoresystem.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.*;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.Answer;

import ca.mcgill.ecse321.grocerystoresystem.dao.ItemRepository;
import ca.mcgill.ecse321.grocerystoresystem.model.InventoryType;
import ca.mcgill.ecse321.grocerystoresystem.model.Item;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.lenient;

@ExtendWith(MockitoExtension.class)
public class TestItemService {
	
	@Mock 
	private ItemRepository itemRepository;
	
	@InjectMocks
	private ItemService itemService;
	
	private static final int ITEM_ID = 1234;
	private static final int ITEM_PRICE = 2;
	private static final String ITEM_NAME = "Apple";
	private static final int INVENTORY_AMOUNT = 54;
	private static final boolean IS_DELIVERABLE = true;
	private static final String PORTION_UNIT = "Unit";
	private static final InventoryType INVENTORY_TYPE = InventoryType.perishable;

	private static final int ITEM_ID2 = 2345;
	private static final int ITEM_PRICE2 = 15;
	private static final String ITEM_NAME2 = "Honey";
	private static final int INVENTORY_AMOUNT2 = 30;
	private static final boolean IS_DELIVERABLE2 = true;
	private static final String PORTION_UNIT2 = "500g Jar";
	private static final InventoryType INVENTORY_TYPE2 = InventoryType.readyMade;
	
	private static final int ITEM_ID3 = 2313;
	private static final int ITEM_PRICE3 = 12;
	private static final String ITEM_NAME3 = "Bagels";
	private static final int INVENTORY_AMOUNT3 = 23;
	private static final boolean IS_DELIVERABLE3 = true;
	private static final String PORTION_UNIT3 = "Bag of 6";
	private static final InventoryType INVENTORY_TYPE3 = InventoryType.readyMade;


	private static final int PERSON_KEY = 1002;

	
	@BeforeEach
	public void setMockOutput() {
		
		 lenient().when(itemRepository.findItemByItemID(anyInt())).thenAnswer( (InvocationOnMock invocation) -> {
	            if(invocation.getArgument(0).equals(ITEM_ID)) {
	                return createItem();
	            } else {
	                return null;
	            }
	        });

		lenient().when(itemRepository.existsById(ITEM_ID)).thenAnswer((InvocationOnMock invocation) -> 
		invocation.getArgument(0).equals(ITEM_ID));
		
		
		 Answer<?> returnParamAsAnswer = (InvocationOnMock invocation) -> {return invocation.getArgument(0);};
		 lenient().when(itemRepository.save(any(Item.class))).thenAnswer(returnParamAsAnswer);
	}
	
	 @Test
	 public void successfullyCreateItem() {
		 Item savedItem = null;
		 
		 try {
			savedItem = itemService.createItem(ITEM_NAME, 
	        		ITEM_PRICE, INVENTORY_AMOUNT, IS_DELIVERABLE, PORTION_UNIT, INVENTORY_TYPE);
	        
		 } catch(IllegalArgumentException exp) {
			 fail(exp.getMessage());
		 }
	        
	     assertNotNull(savedItem);
	     assertEquals(ITEM_NAME, savedItem.getName());
	     assertEquals(ITEM_PRICE, savedItem.getItemPrice());
	     assertEquals(INVENTORY_AMOUNT, savedItem.getInventoryAmount());
	     assertEquals(IS_DELIVERABLE, savedItem.isDeliverable());
	     assertEquals(PORTION_UNIT, savedItem.getPortionUnit());
	     assertEquals(INVENTORY_TYPE, savedItem.getInventoryType());
	    }


	@Test
	public void successfullyGetPickupOrderByID() {
		Item savedItem = null;

		try {
			savedItem = itemService.getItemByID(ITEM_ID);
		} catch(NullPointerException exception) {
			fail(exception.getMessage());
		}

		assertNotNull(savedItem);
	     assertEquals(ITEM_NAME, savedItem.getName());
	     assertEquals(ITEM_PRICE, savedItem.getItemPrice());
	     assertEquals(INVENTORY_AMOUNT, savedItem.getInventoryAmount());
	     assertEquals(IS_DELIVERABLE, savedItem.isDeliverable());
	     assertEquals(PORTION_UNIT, savedItem.getPortionUnit());
	     assertEquals(INVENTORY_TYPE, savedItem.getInventoryType());
	}
	
	private Item createItem() {
		Item item = new Item();
		item.setItemID(ITEM_ID);
		item.setItemPrice(ITEM_PRICE);
		item.setName(ITEM_NAME);
		item.setInventoryAmount(INVENTORY_AMOUNT);
		item.setDeliverable(IS_DELIVERABLE);
		item.setPortionUnit(PORTION_UNIT);
		item.setInventoryType(INVENTORY_TYPE);
		return item;
	}
		
	
	private List<Item> createItems(){
		ArrayList<Item> items = new ArrayList<>();

		Item item = new Item();
		item.setItemID(ITEM_ID);
		item.setItemPrice(ITEM_PRICE);
		item.setName(ITEM_NAME);
		item.setInventoryAmount(INVENTORY_AMOUNT);
		item.setDeliverable(IS_DELIVERABLE);
		item.setPortionUnit(PORTION_UNIT);
		item.setInventoryType(INVENTORY_TYPE);

		Item item2 = new Item();
		item.setItemID(ITEM_ID2);
		item.setItemPrice(ITEM_PRICE2);
		item.setName(ITEM_NAME2);
		item.setInventoryAmount(INVENTORY_AMOUNT2);
		item.setDeliverable(IS_DELIVERABLE2);
		item.setPortionUnit(PORTION_UNIT2);
		item.setInventoryType(INVENTORY_TYPE2);

		items.add(item);
		items.add(item2);

		return items;
	}


	private List<Item> createItems2(){
		ArrayList<Item> items = new ArrayList<>();

		Item item = new Item();
		item.setItemID(ITEM_ID3);
		item.setItemPrice(ITEM_PRICE3);
		item.setName(ITEM_NAME3);
		item.setInventoryAmount(INVENTORY_AMOUNT3);
		item.setDeliverable(IS_DELIVERABLE3);
		item.setPortionUnit(PORTION_UNIT3);
		item.setInventoryType(INVENTORY_TYPE3);

		items.add(item);

		return items;
	}


}
