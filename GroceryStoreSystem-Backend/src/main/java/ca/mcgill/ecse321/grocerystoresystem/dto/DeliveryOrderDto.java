package ca.mcgill.ecse321.grocerystoresystem.dto;

import java.time.LocalDateTime;

import ca.mcgill.ecse321.grocerystoresystem.model.Address;

public class DeliveryOrderDto {

	private int id;
	private int totalCost;
	private LocalDateTime orderTimeStamp;
	private boolean isPaid;
	private LocalDateTime deliveryTime;
	private Address deliveryAddress;
	
	public DeliveryOrderDto() {
	}

	public DeliveryOrderDto(int id, int totalCost, LocalDateTime orderTimeStamp, boolean isPaid,
			LocalDateTime deliveryTime, Address deliveryAddress ) {
		this.id = id;
		this.totalCost = totalCost;
		this.orderTimeStamp = orderTimeStamp;
		this.isPaid = isPaid;
		this.deliveryTime = deliveryTime;
		this.deliveryAddress = deliveryAddress;
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setTotalCost(int totalCost) {
		this.totalCost = totalCost;
	}

	public void setOrderTimeStamp(LocalDateTime orderTimeStamp) {
		this.orderTimeStamp = orderTimeStamp;
	}

	public boolean isPaid() {
		return isPaid;
	}

	public void setPaid(boolean paid) {
		isPaid = paid;
	}

	public void setDeliveryTime(LocalDateTime deliveryTime) {
		this.deliveryTime = deliveryTime;
	}

	public void setDeliveryAddress(Address deliveryAddress) {
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
