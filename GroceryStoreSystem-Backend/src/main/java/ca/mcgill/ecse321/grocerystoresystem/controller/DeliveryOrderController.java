package ca.mcgill.ecse321.grocerystoresystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import ca.mcgill.ecse321.grocerystoresystem.model.Address;
import ca.mcgill.ecse321.grocerystoresystem.model.DeliveryOrder;
import ca.mcgill.ecse321.grocerystoresystem.model.InStoreOrder;
import ca.mcgill.ecse321.grocerystoresystem.dto.AddressDto;
import ca.mcgill.ecse321.grocerystoresystem.dto.DeliveryOrderDto;
import ca.mcgill.ecse321.grocerystoresystem.dto.InStoreOrderDto;
import ca.mcgill.ecse321.grocerystoresystem.service.DeliveryOrderService;

import java.util.List;
import java.util.stream.Collectors;
import java.time.LocalDateTime;

@CrossOrigin(origins = "*")
@RestController
public class DeliveryOrderController {

	@Autowired
	private DeliveryOrderService deliveryOrderService;
	
	@GetMapping(value = {"/deliveryorders/", "/deliveryorders"})
	public List<DeliveryOrderDto> getDeliveryOrders(){
		return deliveryOrderService.getAllDeliveryOrders().stream().map(this::convertToDto).collect(Collectors.toList());
	}
	
	@GetMapping(value = {"/deliveryorder/get/id/", "/deliveryorder/get/id"})
	public DeliveryOrderDto getDeliveryOrderWithId(int id) {
		try {
			return convertToDto(deliveryOrderService.getDeliveryOrderByID(id));
		} catch (NullPointerException exp) {
            return null;
        }
	}
	
	@GetMapping(value = {"deliveryorder/get/address/", "deliveryorder/get/address"})
	public AddressDto getAddressForDeliveryOrder(int id) {
		if (getDeliveryOrderWithId(id) != null) {
			return getDeliveryOrderWithId(id).getDeliveryAddress();
		}
		return null;
	}
	
	@PostMapping(value = {"/deliveryorder/create/", "/deliveryorder/create"})
	public DeliveryOrderDto DeliveryOrder(@RequestParam int totalCost, 
			@RequestParam LocalDateTime orderTimeStamp, @RequestParam boolean isPaid,
			@RequestParam LocalDateTime deliveryTime) {
		DeliveryOrder deliveryOrder = deliveryOrderService.createDeliveryOrder(totalCost, orderTimeStamp,
				isPaid, deliveryTime);
		
		return convertToDto(deliveryOrder);
	}
	
	@PostMapping(value = {"/deliveryorder/update/", "/deliveryorder/update"})
	public DeliveryOrderDto DeliveryOrder(@RequestParam int id, @RequestParam Address address) {
		try {
			return convertToDto(deliveryOrderService.updateDeliveryAddressWithId(id, address));
		}
		catch (NullPointerException exception) {
			return null;
		}
	}
	
	@DeleteMapping(value={"/deliveryorder/delete/", "/deliveryorder/delete"})
    public boolean deleteDeliveryOrderByID(@RequestParam int id) {
        try {
            return deliveryOrderService.deleteDeliveryOrder(id);
        }
        catch (NullPointerException exp) {
            return false;
        }
    }

	@DeleteMapping(value={"/deliveryorders/delete/", "/deliveryorders/delete"})
    public boolean deleteDeliveryOrders() {
        return deliveryOrderService.deleteDeliveryOrders();

    }
	
	private DeliveryOrderDto convertToDto(DeliveryOrder deliveryOrder) {
        if(deliveryOrder == null) {
            throw new NullPointerException("Order is null");
        }
        
        return new DeliveryOrderDto(deliveryOrder.getOrderID(), deliveryOrder.getTotalCost(), 
        		deliveryOrder.getOrderTimeStamp(), deliveryOrder.isPaid(), 
        		deliveryOrder.getDeliveryTime(), convertToDto(deliveryOrder.getAddress()));
    }
	
	private AddressDto convertToDto(Address a) throws NullPointerException {
        if(a == null) {
            throw new NullPointerException("Address is null");
        }

        return new AddressDto(a.getAddressID(), a.isLocal(), a.getStreetName(), a.getStreetNum(), a.getCity(), a.getPostalCode(), a.getCountry());
    }
}
