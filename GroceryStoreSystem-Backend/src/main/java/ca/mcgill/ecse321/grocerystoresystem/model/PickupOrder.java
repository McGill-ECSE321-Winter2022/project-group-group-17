package ca.mcgill.ecse321.grocerystoresystem.model;

import java.time.LocalDateTime;

import javax.persistence.Entity;

@Entity
public class PickupOrder extends Order {

	private LocalDateTime pickupDate;
	private int basketNum;
	
	public PickupOrder(int total_cost, LocalDateTime order_timestamp, boolean isPaid, LocalDateTime pickupDate, int basketNum) {
		super(total_cost, order_timestamp, isPaid);
		this.pickupDate = pickupDate;
		this.basketNum = basketNum;
	}

	public LocalDateTime getPickupDate() {
		return pickupDate;
	}

	public void setPickupDate(LocalDateTime pickupDate) {
		this.pickupDate = pickupDate;
	}

	public int getBasketNum() {
		return basketNum;
	}

	public void setBasketNum(int basketNum) {
		this.basketNum = basketNum;
	}
}
