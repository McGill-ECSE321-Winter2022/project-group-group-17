package ca.mcgill.ecse321.grocerystoresystem.controller;

import ca.mcgill.ecse321.grocerystoresystem.dto.ItemQuantityDto;
import ca.mcgill.ecse321.grocerystoresystem.model.ItemQuantity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import ca.mcgill.ecse321.grocerystoresystem.dto.PickupOrderDto;
import ca.mcgill.ecse321.grocerystoresystem.model.PickupOrder;
import ca.mcgill.ecse321.grocerystoresystem.service.PickupOrderService;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.time.LocalDateTime;

@CrossOrigin(origins = "*")
@RestController
public class PickupOrderController {

	@Autowired
	private PickupOrderService pickupOrderService;
	
	@GetMapping(value = {"/pickuporders/", "/pickuporders"})
	public List<PickupOrderDto> getPickupOrders(){ 
		return pickupOrderService.getAllPickupOrders().stream().map(this::convertToDto).collect(Collectors.toList());
	}
	
	@GetMapping(value = {"/pickuporder/get/id/", "/pickuporder/get/id"})
	public PickupOrderDto getPickupOrderWithId(int id) {
		try {
			return convertToDto(pickupOrderService.getPickupOrderByID(id));
		} catch (NullPointerException exception) {
            return null;
        }
	}
	
	
	@PostMapping(value = {"/pickuporder/create/", "/pickuporder/create"})
	public PickupOrderDto PickupOrder(@RequestParam int totalCost,
									  @RequestParam("orderTimeStamp") @DateTimeFormat(pattern = "yyyy.MM.dd/HH.mm.ss") LocalDateTime orderTimeStamp,
									  @RequestParam boolean isPaid,
									  @RequestParam("pickupDateTime") @DateTimeFormat(pattern = "yyyy.MM.dd/HH.mm.ss") LocalDateTime pickupDateTime) {
		PickupOrder pickupOrder = pickupOrderService.createPickupOrder(totalCost, orderTimeStamp,
				isPaid, pickupDateTime);
		
		return convertToDto(pickupOrder);
	}
	
	@PostMapping(value = {"/pickuporder/updatepickuptime/", "/pickuporder/updatepickuptime"})
	public PickupOrderDto PickupOrder(@RequestParam int id, @RequestParam LocalDateTime newTime) {
		try {
			return convertToDto(pickupOrderService.updatePickupDateTime(id, newTime));
		}
		catch (NullPointerException exception) {
			return null;
		}
	}
	
	@DeleteMapping(value={"/pickuporder/delete/", "/pickuporder/delete"})
    public boolean deletePickupOrderByID(@RequestParam int id) {
        try {
            return pickupOrderService.deletePickupOrder(id);
        }
        catch (NullPointerException exception) {
            return false;
        }
    }

	@DeleteMapping(value={"/pickuporders/delete/", "/pickuporders/delete"})
    public boolean deletePickupOrders() {
        return pickupOrderService.deleteAllPickupOrders();

    }
	
	private PickupOrderDto convertToDto(PickupOrder pickupOrder) {
        if(pickupOrder == null) {
            throw new NullPointerException("Order is null");
        }

		if (pickupOrder.getPortionNum() != null) return new PickupOrderDto(pickupOrder.getOrderID(), pickupOrder.getTotalCost(), pickupOrder.getOrderTimeStamp(),
				pickupOrder.isPaid(), pickupOrder.getPickupDate(), convertToDto(pickupOrder.getPortionNum()));

		else{

			return new PickupOrderDto(pickupOrder.getOrderID(), pickupOrder.getTotalCost(), pickupOrder.getOrderTimeStamp(),
					pickupOrder.isPaid(), pickupOrder.getPickupDate(),new ArrayList<>());
		}

    }

	private List<ItemQuantityDto> convertToDto(List<ItemQuantity> itemQuantities){
		List<ItemQuantityDto> itemQuantityDtos = new ArrayList<>();

		for (ItemQuantity itemQuantity : itemQuantities){
			ItemQuantityDto itemQuantityDto = convertToDto(itemQuantity);
			itemQuantityDtos.add(itemQuantityDto);
		}

		return itemQuantityDtos;
	}

	private ItemQuantityDto convertToDto (ItemQuantity itemQuantity){
		if (itemQuantity == null){
			throw new NullPointerException("Item Quantity is null");
		}
		return new ItemQuantityDto(itemQuantity.getItemNum(), itemQuantity.getQuantityID());
	}

}
