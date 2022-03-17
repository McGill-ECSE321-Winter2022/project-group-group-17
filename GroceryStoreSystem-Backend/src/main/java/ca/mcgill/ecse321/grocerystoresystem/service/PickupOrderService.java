package ca.mcgill.ecse321.grocerystoresystem.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ca.mcgill.ecse321.grocerystoresystem.dao.ItemQuantityRepository;
import ca.mcgill.ecse321.grocerystoresystem.dao.PickupOrderRepository;
import ca.mcgill.ecse321.grocerystoresystem.model.DeliveryOrder;
import ca.mcgill.ecse321.grocerystoresystem.model.PickupOrder;

@Service
public class PickupOrderService {
	
	@Autowired
	PickupOrderRepository pickupOrderRepository;
	ItemQuantityRepository itemQuantityRepository;
	
	
	@Transactional
	public PickupOrder createPickupOrder() {
		return new PickupOrder();
	}
	
	
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
	
	@Transactional
	public PickupOrder createPickupOrder(int totalCost, LocalDateTime orderTimeStamp, boolean isPaid, LocalDateTime pickupDateTime, int orderID) {
		validateTotalCost(totalCost);
		PickupOrder pickupOrder = new PickupOrder();
		pickupOrder.setOrderTimeStamp(orderTimeStamp);
		pickupOrder.setPaid(isPaid);
		pickupOrder.setPickupDate(pickupDateTime);
		pickupOrder.setTotalCost(totalCost);
		pickupOrder.setOrderID(orderID);
		
		pickupOrderRepository.save(pickupOrder);
		
		return pickupOrder;
	}
	
	@Transactional 
	public PickupOrder getPickupOrderByID(int id) {
		return pickupOrderRepository.findPickupOrderByOrderID(id);
	}
	
	@Transactional
	public List<PickupOrder> getAllPickupOrders(){
		return toList(pickupOrderRepository.findAll());
	}
	
	@Transactional
	public List<PickupOrder> getAllPickupOrdersOfPersonWithPersonID(int personID){
		return toList(pickupOrderRepository.findPickupOrderByPersonPersonID(personID));
	}

	
	@Transactional
	public PickupOrder updatePickupDateTime(int id, LocalDateTime newPickupDateTime) {
		
		PickupOrder pickupOrder = pickupOrderRepository.findPickupOrderByOrderID(id);
		pickupOrder.setPickupDate(newPickupDateTime);
		
		pickupOrderRepository.save(pickupOrder);
		
		return pickupOrder;
	}
	
	@Transactional
	public boolean deletePickupOrder(int id) {
		PickupOrder pickupOrder = pickupOrderRepository.findPickupOrderByOrderID(id);
		if (pickupOrder == null) {
			throw new NullPointerException("Order not found");
		}
		
		pickupOrderRepository.deleteById(id);
		return true;
	}
	
	@Transactional
	public boolean deleteAllPickupOrders() {
		pickupOrderRepository.deleteAll();
		return true;
	}
	
	
	
	private void validateTotalCost(int totalCost) {
		if (totalCost < 0) throw new IllegalArgumentException("Please submit a valid total cost");
	}
	
	private <T> List<T> toList(Iterable<T> iterable){
		List<T> resultList = new ArrayList<T>();
		for (T t : iterable) {
			resultList.add(t);
		}
		return resultList;
	}
	
}
