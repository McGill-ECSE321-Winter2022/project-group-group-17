package ca.mcgill.ecse321.grocerystoresystem.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import ca.mcgill.ecse321.grocerystoresystem.service.ItemQuantityService;
import ca.mcgill.ecse321.grocerystoresystem.dao.ItemQuantityRepository;
import ca.mcgill.ecse321.grocerystoresystem.dto.ItemQuantityDto;
import ca.mcgill.ecse321.grocerystoresystem.model.ItemQuantity;
public class ItemQuantityController {
	@Autowired
	private ItemQuantityService itemQuantityService;
	
	@GetMapping(value = { "/itemquantities", "/itemquantities/" })
	public List<ItemQuantityDto> getAllItemQuantities() {
		return itemQuantityService.getAllItemQuantities().stream().map(p -> convertToDto(p)).collect(Collectors.toList());
	}
	@GetMapping(value = { "/itemquantity/get/id", "/itemquantity/get/id/" })
	public ItemQuantityDto getItemQuantityWithId(@RequestParam int id) {
		try {
			return convertToDto(itemQuantityService.getItemQuantityWithID(id));
		}catch(NullPointerException e){
			return null;
		}
	}

	@GetMapping(value = {"/itemquantity/get/itemnum/", "/itemquantity/get/itemnum"})
	public List<ItemQuantityDto> getOwnerWithFirstName(@RequestParam int itemNum) {
	     try {
	          return itemQuantityService.getItemQuantityWithNum(itemNum).stream().map(this::convertToDto).collect(Collectors.toList());
	     }catch (NullPointerException exp) {
	            return null;
	     }
	}
	@PostMapping (value= {"/itemquantity/create/","itemquantity/create"})
	public ItemQuantityDto createItemQuantity(@RequestParam int itemNum, @RequestParam int itemId) {
		ItemQuantity itemQuantity = itemQuantityService.createItemQuantity(itemNum, itemId);
		return convertToDto(itemQuantity);
	}

	private ItemQuantityDto convertToDto(ItemQuantity e) {
		if (e == null) {
			throw new  IllegalArgumentException("There is no such ItemQuantity");
		}
		ItemQuantityDto itemQuantityDto = new ItemQuantityDto(e.getItemNum());
		
		return null;
	}
	
}
