package ca.mcgill.ecse321.grocerystoresystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import ca.mcgill.ecse321.grocerystoresystem.dto.PickupOrderDto;
import ca.mcgill.ecse321.grocerystoresystem.model.PickupOrder;
import ca.mcgill.ecse321.grocerystoresystem.service.PickupOrderService;

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
			@RequestParam LocalDateTime orderTimeStamp, @RequestParam boolean isPaid,
			@RequestParam LocalDateTime pickupDateTime) {
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
        
        return new PickupOrderDto(pickupOrder.getTotalCost(), 
        		pickupOrder.getOrderTimeStamp(), pickupOrder.isPaid(), 
        		pickupOrder.getPickupDate(), pickupOrder.getOrderID());
    }

}
