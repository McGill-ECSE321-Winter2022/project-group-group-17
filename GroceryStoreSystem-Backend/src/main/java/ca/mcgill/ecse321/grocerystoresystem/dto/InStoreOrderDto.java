package ca.mcgill.ecse321.grocerystoresystem.dto;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class InStoreOrderDto {

	private int id;
	private int totalCost;
	private LocalDateTime orderTimeStamp;
	private boolean isPaid;
	private List<ItemQuantityDto> itemQuantities;

	public InStoreOrderDto() {
	}
	
	public InStoreOrderDto(int id, int totalCost, LocalDateTime orderTimeStamp, boolean isPaid,
						   List<ItemQuantityDto> itemQuantities) {
		this.id = id;
		this.totalCost = totalCost;
		this.orderTimeStamp = orderTimeStamp;
		this.isPaid = isPaid;
		this.itemQuantities = itemQuantities;
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

	public List<ItemQuantityDto> getItemQuantities() { return this.itemQuantities; }

	public void setItemQuantities(ArrayList<ItemQuantityDto> itemQuantities) {
		this.itemQuantities = itemQuantities;
	}
}
