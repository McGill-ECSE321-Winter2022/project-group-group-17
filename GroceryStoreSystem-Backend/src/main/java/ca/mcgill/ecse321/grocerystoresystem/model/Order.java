package ca.mcgill.ecse321.grocerystoresystem.model;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "Order")
@Inheritance(strategy=InheritanceType.TABLE_PER_CLASS)
public abstract class Order {
	
	@Id
	@GeneratedValue
	private int orderID;
	
	// Order Associations 
	@OneToMany (mappedBy = "order_quantity", cascade = CascadeType.ALL)
	private List<ItemQuantity> portionNum;
	
	@ManyToOne (fetch = FetchType.LAZY)
	private Person order_person;
	
	
	private LocalDateTime orderTimeStamp;
	private int totalCost;
	private boolean isPaid;
	
	
	public Order() {
		
	}
	
	public Order(int totalCost, LocalDateTime orderTimeStamp, boolean isPaid) {
		this.totalCost = totalCost;
		this.orderTimeStamp = orderTimeStamp;
		this.isPaid = isPaid;
	}
	
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
