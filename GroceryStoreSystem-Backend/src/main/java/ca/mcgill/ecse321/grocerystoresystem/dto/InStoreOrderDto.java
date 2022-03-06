package ca.mcgill.ecse321.grocerystoresystem.dto;

import java.time.LocalDateTime;

public class InStoreOrderDto {
	
	private int totalCost;
	private LocalDateTime orderTimeStamp;
	private boolean isPaid;

	public InStoreOrderDto() {
	}
	
	public InStoreOrderDto(int totalCost, LocalDateTime orderTimeStamp, boolean isPaid) {
		this.totalCost = totalCost;
		this.orderTimeStamp = orderTimeStamp;
		this.isPaid = isPaid;
		
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
}
