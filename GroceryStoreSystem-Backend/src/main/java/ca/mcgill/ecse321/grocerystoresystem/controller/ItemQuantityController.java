package ca.mcgill.ecse321.grocerystoresystem.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import ca.mcgill.ecse321.grocerystoresystem.service.ItemQuantityService;
import ca.mcgill.ecse321.grocerystoresystem.dto.ItemQuantityDto;
import ca.mcgill.ecse321.grocerystoresystem.model.ItemQuantity;

@CrossOrigin(origins = "*")
@RestController
public class ItemQuantityController {
	@Autowired
	private ItemQuantityService itemQuantityService;
	
	@GetMapping(value = { "/itemquantities", "/itemquantities/" })
	public List<ItemQuantityDto> getAllItemQuantities() {
		return itemQuantityService.getAllItemQuantities().stream().map(this::convertToDto).collect(Collectors.toList());
	}
	@GetMapping(value = { "/itemquantity/get/id", "/itemquantity/get/id/" })
	public ItemQuantityDto getItemQuantityWithQuantityID(@RequestParam int quantityID) {
		try {
			return convertToDto(itemQuantityService.getItemQuantityWithQuantityID(quantityID));
		}catch(NullPointerException e){
			return null;
		}
	}

	@GetMapping(value = {"/itemquanties/get/itemnum/", "/itemquanties/get/itemnum"})
	public List<ItemQuantityDto> getItemQuantitiesWithNum(@RequestParam int itemNum) {
	     try {
	          return itemQuantityService.getItemQuantityWithNum(itemNum).stream().map(this::convertToDto).collect(Collectors.toList());
	     }catch (NullPointerException exp) {
	            return null;
	     }
	}
	@PostMapping (value= {"/itemquantity/create/","itemquantity/create"})
	public ItemQuantityDto createItemQuantity(@RequestParam int itemNum) {
		return convertToDto(itemQuantityService.createItemQuantity(itemNum));
	}

	private ItemQuantityDto convertToDto(ItemQuantity e) {
		if (e == null) {
			throw new  IllegalArgumentException("There is no such ItemQuantity");
		}
		return new ItemQuantityDto(e.getItemNum());
	}
	
}
