package ca.mcgill.ecse321.grocerystoresystem.dto;

import java.time.LocalDateTime;

import ca.mcgill.ecse321.grocerystoresystem.model.Address;

public class DeliveryOrderDto {
	
	private int totalCost;
	private LocalDateTime orderTimeStamp;
	private boolean isPaid;
	private LocalDateTime deliveryTime;
	private Address deliveryAddress;
	
	public DeliveryOrderDto() {
	}

	public DeliveryOrderDto(int totalCost, LocalDateTime orderTimeStamp, boolean isPaid,
			LocalDateTime deliveryTime, Address deliveryAddress ) {
		this.totalCost = totalCost;
		this.orderTimeStamp = orderTimeStamp;
		this.isPaid = isPaid;
		this.deliveryTime = deliveryTime;
		this.deliveryAddress = deliveryAddress;
		
	}
	
	public int getTotalCost() {
		return totalCost;
	}
	
	public LocalDateTime getOrderTimeStamp() {
		return orderTimeStamp;
	}
	
	public boolean getPaymentStatus() {
		return isPaid;
	}
	
	public LocalDateTime getDeliveryTime() {
		return deliveryTime;
	}
	
	public Address getDeliveryAddress() {
		return deliveryAddress;
	}
}
