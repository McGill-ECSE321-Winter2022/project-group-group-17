package ca.mcgill.ecse321.grocerystoresystem.service;
import java.util.ArrayList;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import  ca.mcgill.ecse321.grocerystoresystem.dao.*; //imports all the classes
import  ca.mcgill.ecse321.grocerystoresystem.model.*;

@Service
public class ItemQuantityService {

	@Autowired
	ItemQuantityRepository itemQuantityRepository;
	
	@Transactional
	public ItemQuantity createItemQuantity(int itemNum) {
		if (itemNum < 0) throw new IllegalArgumentException("Input a valid itemNum!");
		ItemQuantity itemQuantity = new ItemQuantity();
		itemQuantity.setItemNum(itemNum);

		itemQuantityRepository.save(itemQuantity);

		return itemQuantity;
	}

	@Transactional
	public ItemQuantity getItemQuantityWithID(int itemID) {
		
		ItemQuantity itemQuantity = itemQuantityRepository.findItemQuantityByQuantityID(itemID);
	    if(itemQuantity == null) throw new NullPointerException("Item quantity not found");
		return itemQuantity;
	}

	@Transactional
	public List<ItemQuantity> getItemQuantityByOrderID(int orderID){
		return toList(itemQuantityRepository.findItemQuantitiesByOrderOrderID(orderID));
	}

	@Transactional
	public List<ItemQuantity> getItemQuantityByItemID(int itemID){
		return toList(itemQuantityRepository.findItemQuantitiesBySpecificItemItemID(itemID));
	}

	@Transactional
	public List<ItemQuantity> getItemQuantityWithNum(int itemNum) {
		return toList(itemQuantityRepository.findItemQuantityByItemNum(itemNum));
   	}
	@Transactional
	public List<ItemQuantity> getAllItemQuantities(){
		return toList(itemQuantityRepository.findAll());
	}

	@Transactional
	public boolean deleteItemQuantity(int itemQuantityID) {
		
	     ItemQuantity itemQuantity = itemQuantityRepository.findItemQuantityByQuantityID(itemQuantityID);
	        if(itemQuantity == null) throw new NullPointerException("Item quantity not found");
	        itemQuantityRepository.delete(itemQuantity);

	        return true;
	}
	private <T> List<T> toList(Iterable<T> iterable){
		List<T> resultList = new ArrayList<T>();
		for (T t : iterable) {
			resultList.add(t);
		}
		return resultList;
	}
	
}
 