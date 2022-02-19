package ca.mcgill.ecse321.grocerystoresystem.model;

import java.time.LocalDateTime;

import javax.persistence.*;

@Entity
public abstract class Order {
	
	
	
	@Id
	@GeneratedValue
	private int orderID;
	// Order Associations 
	@OneToMany (mappedBy = "order", cascade = CascadeType.ALL)
	private ItemQuantity portionNum;
	@ManyToOne (fetch = FetchType.LAZY)
	@JoinColumn (name = "orders")
	private Person person;
	
	
	private LocalDateTime orderTimeStamp;
	private int totalCost;
	private boolean isPaid;
	
	public Order(int totalCost, LocalDateTime orderTimeStamp, boolean isPaid, int orderID) {
		this.totalCost = totalCost;
		this.orderTimeStamp = orderTimeStamp;
		this.isPaid = isPaid;
		this.orderID = orderID;
	}

	public LocalDateTime getOrderTimeStamp() {
		return orderTimeStamp;
	}

	public void setOrderTimeStamp(LocalDateTime orderTimeStamp) {
		this.orderTimeStamp = orderTimeStamp;
	}

	public int getTotalCost() {
		return totalCost;
	}

	public void setTotalCost(int totalCost) {
		this.totalCost = totalCost;
	}

	public boolean isPaid() {
		return isPaid;
	}

	public void setPaid(boolean isPaid) {
		this.isPaid = isPaid;
	}

	public int getOrderID() {
		return orderID;
	}
	public void setOrderID(int orderID) {
		this.orderID = orderID;
	}
}
