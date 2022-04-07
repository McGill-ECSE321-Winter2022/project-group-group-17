package ca.mcgill.ecse321.grocerystoresystem.model;

import java.time.LocalDateTime;

import javax.persistence.Entity;

@Entity
public class PickupOrder extends Order {

	private LocalDateTime pickupDate;

	public PickupOrder() {};
	
	public PickupOrder(int total_cost, LocalDateTime order_timestamp, boolean isPaid, LocalDateTime pickupDate) {
		super(total_cost, order_timestamp, isPaid);
		this.pickupDate = pickupDate;
	}
	
	public PickupOrder(int total_cost, LocalDateTime order_timestamp, boolean isPaid, LocalDateTime pickupDate, int orderID) {
		super(total_cost, order_timestamp, isPaid, orderID);
		this.pickupDate = pickupDate;
	}

	public LocalDateTime getPickupDate() {
		return pickupDate;
	}

	public void setPickupDate(LocalDateTime pickupDate) {
		this.pickupDate = pickupDate;
	}

}
