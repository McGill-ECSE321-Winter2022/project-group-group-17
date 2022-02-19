package ca.mcgill.ecse321.grocerystoresystem.model;

import java.time.LocalDateTime;

import javax.persistence.*;

@Entity
public class DeliveryOrder extends Order {

  // DeliveryOrder Associations
  @OneToOne(mappedBy = "order")
  private Address address;
  
  
	private LocalDateTime deliveryTime;
	
	public DeliveryOrder(int totalCost, LocalDateTime orderTimeStamp, boolean isPaid, LocalDateTime deliveryTime, int orderID) {
		super(totalCost, orderTimeStamp, isPaid, orderID);
		this.deliveryTime = deliveryTime;
	}

	public LocalDateTime getDeliveryTime() {
		return deliveryTime;
	}

	public void setDeliveryTime(LocalDateTime deliveryTime) {
		this.deliveryTime = deliveryTime;
	}

}
