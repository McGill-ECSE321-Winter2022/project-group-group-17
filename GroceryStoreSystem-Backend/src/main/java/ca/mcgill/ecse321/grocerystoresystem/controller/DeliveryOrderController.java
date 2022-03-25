package ca.mcgill.ecse321.grocerystoresystem.controller;

import ca.mcgill.ecse321.grocerystoresystem.dto.ItemQuantityDto;
import ca.mcgill.ecse321.grocerystoresystem.dto.OwnerDto;
import ca.mcgill.ecse321.grocerystoresystem.model.ItemQuantity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import ca.mcgill.ecse321.grocerystoresystem.model.Address;
import ca.mcgill.ecse321.grocerystoresystem.model.DeliveryOrder;
import ca.mcgill.ecse321.grocerystoresystem.dto.AddressDto;
import ca.mcgill.ecse321.grocerystoresystem.dto.DeliveryOrderDto;
import ca.mcgill.ecse321.grocerystoresystem.service.DeliveryOrderService;

import java.util.ArrayList;
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
										  @RequestParam("orderTimeStamp") @DateTimeFormat(pattern = "yyyy.MM.dd/HH.mm.ss") LocalDateTime orderTimeStamp,
										  @RequestParam boolean isPaid,
										  @RequestParam("deliveryTime") @DateTimeFormat(pattern = "yyyy.MM.dd/HH.mm.ss") LocalDateTime deliveryTime) {
		DeliveryOrder deliveryOrder = deliveryOrderService.createDeliveryOrder(totalCost, orderTimeStamp,
				isPaid, deliveryTime);
		
		return convertToDto(deliveryOrder);
	}

	@PostMapping(value={"/deliveryorderr/update/address/", "/deliveryorder/update/address"})
	public DeliveryOrderDto updateDeliveryOrderAddress(@RequestParam int id, @RequestParam int addressID) {
		try {
			return convertToDto(deliveryOrderService.updateDeliveryAddressWithId(id, addressID));
		}
		catch(NullPointerException exp) {
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
        		deliveryOrder.getDeliveryTime(), convertToDto(deliveryOrder.getAddress()),
				convertToDto(deliveryOrder.getPortionNum()));
    }
	
	private AddressDto convertToDto(Address a) throws NullPointerException {
        if(a == null) {
            throw new NullPointerException("Address is null");
        }

        return new AddressDto(a.getAddressID(), a.isLocal(), a.getStreetName(), a.getStreetNum(), a.getCity(), a.getPostalCode(), a.getCountry());
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
		return new ItemQuantityDto(itemQuantity.getItemNum());
	}
}
