package ca.mcgill.ecse321.grocerystoresystem.model;

import java.time.LocalDateTime;

import javax.persistence.Entity;

@Entity
public class DeliveryOrder extends Order {

	private boolean isLocal;
	private LocalDateTime deliveryTime;
	
	public DeliveryOrder(int total_cost, LocalDateTime order_timestamp, boolean isPaid, boolean isLocal, LocalDateTime deliveryTime) {
		super(total_cost, order_timestamp, isPaid);
		this.isLocal = isLocal;
		this.deliveryTime = deliveryTime;
	}
	
	public boolean isLocal() {
		return isLocal;
	}

	public void setLocal(boolean isLocal) {
		this.isLocal = isLocal;
	}

	public LocalDateTime getDeliveryTime() {
		return deliveryTime;
	}

	public void setDeliveryTime(LocalDateTime deliveryTime) {
		this.deliveryTime = deliveryTime;
	}

}
