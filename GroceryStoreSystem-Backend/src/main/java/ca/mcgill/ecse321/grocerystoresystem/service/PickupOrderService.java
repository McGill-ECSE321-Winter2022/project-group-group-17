package ca.mcgill.ecse321.grocerystoresystem.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ca.mcgill.ecse321.grocerystoresystem.dao.PickupOrderRepository;
import ca.mcgill.ecse321.grocerystoresystem.model.PickupOrder;

@Service
public class PickupOrderService {
	
	@Autowired
	PickupOrderRepository pickupOrderRepository;
	
	@Transactional
	public PickupOrder createPickupOrder(int totalCost, LocalDateTime orderTimeStamp, boolean isPaid, LocalDateTime pickupDateTime) {
		validateTotalCost(totalCost);
		PickupOrder pickupOrder = new PickupOrder();
		pickupOrder.setOrderTimeStamp(orderTimeStamp);
		pickupOrder.setPaid(isPaid);
		pickupOrder.setPickupDate(pickupDateTime);
		pickupOrder.setTotalCost(totalCost);
		
		pickupOrderRepository.save(pickupOrder);
		
		return pickupOrder;
	}
	
	private void validateTotalCost(int totalCost) {
		if (totalCost<0) throw new IllegalArgumentException("Please submit a valid total cost");
	}
	

}
