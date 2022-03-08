package ca.mcgill.ecse321.grocerystoresystem.service;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ca.mcgill.ecse321.grocerystoresystem.dao.DeliveryOrderRepository;
import ca.mcgill.ecse321.grocerystoresystem.model.Address;
import ca.mcgill.ecse321.grocerystoresystem.model.DeliveryOrder;

@Service
public class DeliveryOrderService {
	
	@Autowired
	DeliveryOrderRepository deliveryOrderRepository;
	
	@Transactional
	public DeliveryOrder createDeliveryOrder(int totalCost, LocalDateTime orderTimeStamp, boolean isPaid, LocalDateTime deliveryTime) {
		validateTotalCost(totalCost);
		DeliveryOrder deliveryOrder = new DeliveryOrder();
		deliveryOrder.setOrderTimeStamp(orderTimeStamp);
		deliveryOrder.setPaid(isPaid);
		deliveryOrder.setDeliveryTime(deliveryTime);
		deliveryOrder.setTotalCost(totalCost);
		
		return deliveryOrder;
	}
	
	@Transactional
	public DeliveryOrder createDeliveryOrder(int totalCost, LocalDateTime orderTimeStamp, boolean isPaid, LocalDateTime deliveryTime, Address deliveryAddress) {
		validateTotalCost(totalCost);
		DeliveryOrder deliveryOrder = new DeliveryOrder();
		deliveryOrder.setOrderTimeStamp(orderTimeStamp);
		deliveryOrder.setPaid(isPaid);
		deliveryOrder.setDeliveryTime(deliveryTime);
		deliveryOrder.setTotalCost(totalCost);
		deliveryOrder.setAddress(deliveryAddress);
		
		return deliveryOrder;
	}
	
	@Transactional
	public DeliveryOrder updateDeliveryAddressWithId(int id, Address deliveryAddress) {
		DeliveryOrder deliveryOrder = getDeliveryOrderByID(id);
		deliveryOrder.setAddress(deliveryAddress);
		return deliveryOrder;
	}
	
	@Transactional 
	public DeliveryOrder getDeliveryOrderByID(int id) {
		return deliveryOrderRepository.findDeliveryOrderByOrderID(id);
	}
	
	@Transactional
	public List<DeliveryOrder> getAllDeliveryOrders(){
		return toList(deliveryOrderRepository.findAll());
	}
	
	@Transactional
	public boolean deleteDeliveryOrder(int id) {
		DeliveryOrder deliveryOrder = deliveryOrderRepository.findDeliveryOrderByOrderID(id);
		if (deliveryOrder == null) throw new NullPointerException("Owner not found");
		deliveryOrderRepository.delete(deliveryOrder);
		
		return true;
	}
	
	@Transactional
    public boolean deleteDeliveryOrders() {
        deliveryOrderRepository.deleteAll();

        return true;
    }
	
	private void validateTotalCost(int totalCost) {
		if (totalCost<0) throw new IllegalArgumentException("Please submit a valid totalCost");
	}
	private <T> List<T> toList(Iterable<T> iterable){
		List<T> resultList = new ArrayList<T>();
		for (T t : iterable) {
			resultList.add(t);
		}
		return resultList;
	}

}
