package ca.mcgill.ecse321.grocerystoresystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import ca.mcgill.ecse321.grocerystoresystem.model.InStoreOrder;
import ca.mcgill.ecse321.grocerystoresystem.dto.InStoreOrderDto;
import ca.mcgill.ecse321.grocerystoresystem.service.InStoreOrderService;

import java.util.List;
import java.util.stream.Collectors;
import java.time.LocalDateTime;

@CrossOrigin(origins = "*")
@RestController
public class InStoreOrderController {

	@Autowired
	private InStoreOrderService inStoreOrderService;
	
	@PostMapping(value = {"/instoreorder/create/", "/instoreorder/create"})
	public InStoreOrderDto createInStoreOrder(@RequestParam int totalCost, 
			@RequestParam LocalDateTime orderTimeStamp, @RequestParam boolean isPaid) {
		InStoreOrder inStoreOrder = inStoreOrderService.createInStoreOrder(totalCost, orderTimeStamp, isPaid);
		
		return convertToDto(inStoreOrder);
	}
	
	@GetMapping(value = {"/instoreorders/", "/instoreorders"})
	public List<InStoreOrderDto> getAllInStoreOrderDtos(){
		return inStoreOrderService.getAllInStoreOrders().stream().map(this::convertToDto).collect(Collectors.toList());
	}
	
	 @GetMapping(value={"/instoreorder/get/id/", "/instoreorder/get/id"})
	 public InStoreOrderDto getInStoreOrderWithId(@RequestParam int id) {
	        try {
	            return convertToDto(inStoreOrderService.getInStoreOrderByID(id));
	        }
	        catch (NullPointerException exp) {
	            return null;
	        }
	 }  
	 
	 @DeleteMapping(value = {"/instoreorder/delete/", "/instoreorder/delete"})
	 public boolean deleteInStoreOrderWithId(@RequestParam int id) {
		 try {
			 return inStoreOrderService.deleteInStoreOrderWithID(id);
			 
		 } catch (NullPointerException exception) {
			 return false;
		 }
	 }
	 
	 @DeleteMapping(value = {"/instoreorders/delete/", "/instoreorders/delete"})
	 public boolean deleteInStoreOrders() {
		 return inStoreOrderService.deleteInStoreOrders();
	 }
	 
	private InStoreOrderDto convertToDto(InStoreOrder inStoreOrder) {
        if(inStoreOrder == null) {
            throw new NullPointerException("Order is null");
        }


        return new InStoreOrderDto(inStoreOrder.getOrderID(), inStoreOrder.getTotalCost(), inStoreOrder.getOrderTimeStamp(), 
        		inStoreOrder.isPaid());
    }
	
	
}
