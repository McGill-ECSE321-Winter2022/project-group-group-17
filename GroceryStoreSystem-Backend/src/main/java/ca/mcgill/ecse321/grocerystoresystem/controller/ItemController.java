package ca.mcgill.ecse321.grocerystoresystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import ca.mcgill.ecse321.grocerystoresystem.dto.ItemDto;
import ca.mcgill.ecse321.grocerystoresystem.model.InventoryType;
import ca.mcgill.ecse321.grocerystoresystem.model.Item;
import ca.mcgill.ecse321.grocerystoresystem.service.ItemService;

import java.util.List;
import java.util.stream.Collectors;


@CrossOrigin(origins = "*")
@RestController
public class ItemController {

	@Autowired
	private ItemService itemService;
	
	@GetMapping(value = {"/items/all/", "/items/all"})
	public List<ItemDto> getDeliveryOrders(){
		return itemService.getAllItems().stream().map(this::convertToDto).collect(Collectors.toList());
	}
	
	@GetMapping(value = {"/item/get/id/", "/item/get/id"})
	public ItemDto getItemById(int id) {
		try {
			return convertToDto(itemService.getItemByID(id));
		} catch (NullPointerException exception) {
            return null;
        }
	}
	
	@PostMapping(value = {"/item/create/", "/item/create"})
	public ItemDto Item(@RequestParam String name, @RequestParam int itemPrice, @RequestParam int inventoryAmount, @RequestParam boolean isDeliverable, @RequestParam String portionUnit, @RequestParam InventoryType inventoryType) {
		Item item = itemService.createItem(name, itemPrice, inventoryAmount, isDeliverable, portionUnit, inventoryType);
		
		return convertToDto(item);
	}
	
	@PostMapping(value = {"/item/update/inventory/", "/item/update/inventory"})
	public ItemDto setInventoryAmountById(@RequestParam int id, @RequestParam int newAmount) {
		try {
			return convertToDto(itemService.setInventoryAmountById(id, newAmount));
		}
		catch (NullPointerException exception) {
			return null;
		}
	}
	
	@DeleteMapping(value={"/item/delete/", "/item/delete"})
    public boolean deleteItemByID(@RequestParam int id) {
        try {
            return itemService.deleteItemById(id);
        }
        catch (NullPointerException exception) {
            return false;
        }
    }

	@DeleteMapping(value={"/items/deleteall/", "/items/deleteall"})
    public boolean deleteAllItems() {
        return itemService.deleteAllItems();
    }
	
	private ItemDto convertToDto(Item item) {
        if(item == null) {
            throw new NullPointerException("Item is null");
        }
        
        return new ItemDto(item.getName(), item.getItemPrice(), 
        		item.getInventoryAmount(), item.isDeliverable(), 
        		item.getPortionUnit(), item.getInventoryType(), item.getItemID());
    }
	
}
