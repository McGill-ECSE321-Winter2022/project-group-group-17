package ca.mcgill.ecse321.grocerystoresystem.dto;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import ca.mcgill.ecse321.grocerystoresystem.model.ItemQuantity;


public class PickupOrderDto {
	
	private int totalCost;
	private LocalDateTime pickupDate;
	private LocalDateTime orderTimeStamp;
	private boolean isPaid;
	private int orderId;

	
	public PickupOrderDto() {
	}
	
	public PickupOrderDto(int totalCost, LocalDateTime orderTimestamp, boolean isPaid, LocalDateTime pickupDate, int orderId) {
		this.totalCost = totalCost;
		this.orderTimeStamp = orderTimestamp;
		this.isPaid = isPaid;
		this.pickupDate = pickupDate;
		this.orderId = orderId;
		
	}
	
	public int getId() {
		return orderId;
	}

	public void setId(int orderId) {
		this.orderId = orderId;
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
		this.isPaid = paid;
	}

	public void setDeliveryTime(LocalDateTime pickupDate) {
		this.pickupDate = pickupDate;
	}

	public int getTotalCost() {
		return this.totalCost;
	}
	
	public LocalDateTime getOrderTimeStamp() {
		return this.orderTimeStamp;
	}
	
	public boolean getPaymentStatus() {
		return this.isPaid;
	}
	
	public LocalDateTime getPickupTime() {
		return this.pickupDate;
	}
	
	
	
}
