package ca.mcgill.ecse321.grocerystoresystem.model;

import java.time.LocalDateTime;

import javax.persistence.*;

@Entity
public class DeliveryOrder extends Order {

	// DeliveryOrder Associations
	@ManyToOne
	private Address deliveryAddress;
  
	private LocalDateTime deliveryTime;
	
	public DeliveryOrder() {};
	
	public DeliveryOrder(int totalCost, LocalDateTime orderTimeStamp, boolean isPaid, LocalDateTime deliveryTime) {
		super(totalCost, orderTimeStamp, isPaid);
		this.deliveryTime = deliveryTime;
	}

	public DeliveryOrder(int totalCost, LocalDateTime orderTimeStamp, boolean isPaid, LocalDateTime deliveryTime, Address deliveryAddress) {
		this(totalCost, orderTimeStamp, isPaid, deliveryTime);
		this.deliveryAddress = deliveryAddress;
	}

	public LocalDateTime getDeliveryTime() {
		return deliveryTime;
	}

	public void setDeliveryTime(LocalDateTime deliveryTime) {
		this.deliveryTime = deliveryTime;
	}
	
	public Address getAddress() {
		return this.deliveryAddress;
	}

	public void setAddress(Address address) {
		this.deliveryAddress = address;
	}

}
