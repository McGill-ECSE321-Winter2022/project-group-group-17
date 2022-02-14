package ca.mcgill.ecse321.grocerystoresystem.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Item {
	@Id
	@GeneratedValue
	private int item_ID;
	
	private int item_price;
	private String name;
	private int inventoryAmount;
	private boolean isDeliverable;
	
	public Item(String name, int item_price, int inventoryAmount, boolean isDeliverable) {
		this.name = name;
		this.item_price = item_price;
		this.inventoryAmount = inventoryAmount;
		this.isDeliverable = isDeliverable;
	}

	public int getItem_price() {
		return item_price;
	}

	public void setItem_price(int item_price) {
		this.item_price = item_price;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getInventoryAmount() {
		return inventoryAmount;
	}

	public void setInventoryAmount(int inventoryAmount) {
		this.inventoryAmount = inventoryAmount;
	}

	public boolean isDeliverable() {
		return isDeliverable;
	}

	public void setDeliverable(boolean isDeliverable) {
		this.isDeliverable = isDeliverable;
	}

	public int getItem_ID() {
		return item_ID;
	}
	
}
