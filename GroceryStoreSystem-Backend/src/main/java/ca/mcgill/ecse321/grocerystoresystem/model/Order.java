package ca.mcgill.ecse321.grocerystoresystem.model;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public abstract class Order {
	
	@Id
	@GeneratedValue
	private int order_ID;
	
	private LocalDateTime order_timestamp;
	private int total_cost;
	
	private boolean isPaid;
	
	public Order(int total_cost, LocalDateTime order_timestamp, boolean isPaid) {
		this.total_cost = total_cost;
		this.order_timestamp = order_timestamp;
		this.isPaid = isPaid;
	}

	public LocalDateTime getOrder_timestamp() {
		return order_timestamp;
	}

	public void setOrder_timestamp(LocalDateTime order_timestamp) {
		this.order_timestamp = order_timestamp;
	}

	public int getTotal_cost() {
		return total_cost;
	}

	public void setTotal_cost(int total_cost) {
		this.total_cost = total_cost;
	}

	public boolean isPaid() {
		return isPaid;
	}

	public void setPaid(boolean isPaid) {
		this.isPaid = isPaid;
	}

	public int getOrder_ID() {
		return order_ID;
	}
}
