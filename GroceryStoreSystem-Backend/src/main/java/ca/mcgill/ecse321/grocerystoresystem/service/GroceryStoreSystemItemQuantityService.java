package ca.mcgill.ecse321.grocerystoresystem.service;
import java.util.ArrayList;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import  ca.mcgill.ecse321.grocerystoresystem.dao.*; //imports all the classes
import  ca.mcgill.ecse321.grocerystoresystem.model.*;


public class GroceryStoreSystemItemQuantityService {

	@Autowired
	ItemQuantityRepository itemQuantityRepository;
	
	@Transactional
	public ItemQuantity createItemQuantity(int itemNum, int quantityID) {
		ItemQuantity itemQuantity = new ItemQuantity();
		try{
			checkEmptyInput(itemNum);
			checkEmptyInput(quantityID);
		}catch(IllegalArgumentException e){
			System.out.println(e);	
		}
		return itemQuantity;
	}
	private void checkEmptyInput(Integer input) {
		if(input == null) {
			throw new IllegalArgumentException("You must enter all the required information to proceed.");
		}
	}
	@Transactional
	public ItemQuantity getItemQuantityWithID(int itemID) {
		ItemQuantity itemQuantity = itemQuantityRepository.findItemQuantityByQuantityID(itemID);
		return itemQuantity;
	}
	@Transactional
	public List<ItemQuantity> getItemQuantityWithNum(int itemNum) {
		return toList(itemQuantityRepository.findItemQuantityByItemNum(itemNum));
	}
	@Transactional
	public List<ItemQuantity> getAllItemQuantities(){
		return toList(itemQuantityRepository.findAll());
	}
	private <T> List<T> toList(Iterable<T> iterable){
		List<T> resultList = new ArrayList<T>();
		for (T t : iterable) {
			resultList.add(t);
		}
		return resultList;
	}
	
}
 