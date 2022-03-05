package ca.mcgill.ecse321.grocerystoresystem.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ca.mcgill.ecse321.grocerystoresystem.dao.InStoreOrderRepository;
import ca.mcgill.ecse321.grocerystoresystem.model.InStoreOrder;

@Service
public class InStoreOrderService {
	
	@Autowired
	InStoreOrderRepository inStoreOrderRepository;
	
	@Transactional
	public InStoreOrder createInStoreOrder(int totalCost, LocalDateTime orderTimeStamp, boolean isPaid) {
		if (totalCost<0) throw new IllegalArgumentException("Please submit a valid totalCost");
		InStoreOrder inStoreOrder = new InStoreOrder();
		inStoreOrder.setOrderTimeStamp(orderTimeStamp);
		inStoreOrder.setPaid(isPaid);
		inStoreOrder.setTotalCost(totalCost);
		
		return inStoreOrder;
	}
	
	@Transactional
	public InStoreOrder getInStoreOrderByID(int id) {
		return inStoreOrderRepository.findInStoreOrderByOrderID(id);
	}
	
	@Transactional
	public List<InStoreOrder> getAllInStoreOrders(){
		return toList(inStoreOrderRepository.findAll());
	}

	private <T> List<T> toList(Iterable<T> iterable){
		List<T> resultList = new ArrayList<T>();
		for (T t : iterable) {
			resultList.add(t);
		}
		return resultList;
	}

}
