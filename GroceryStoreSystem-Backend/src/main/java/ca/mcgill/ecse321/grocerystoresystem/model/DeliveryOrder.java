package ca.mcgill.ecse321.grocerystoresystem.model;

import java.time.LocalDateTime;

import javax.persistence.Entity;

@Entity
public class DeliveryOrder extends Order {

  // DeliveryOrder Associations
  @OneToOne(mappedBy = "order")
  private Address address;
  
  
	private LocalDateTime deliveryTime;
	
	public DeliveryOrder(int total_cost, LocalDateTime order_timestamp, boolean isPaid, LocalDateTime deliveryTime) {
		super(total_cost, order_timestamp, isPaid);
		this.deliveryTime = deliveryTime;
	}

	public LocalDateTime getDeliveryTime() {
		return deliveryTime;
	}

	public void setDeliveryTime(LocalDateTime deliveryTime) {
		this.deliveryTime = deliveryTime;
	}

}
