package ca.mcgill.ecse321.grocerystoresystem.model;

import javax.persistence.*;

@Entity
public class Item {
	@Id
	@GeneratedValue
	private int item_ID;
	
	// Item Associations
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "items")
	private Order order;
	
	
	private int item_price;
	private String name;
	private int inventoryAmount;
	private boolean isDeliverable;
	private String portionUnit;
	private InventoryType inventoryType;
	
	public Item(String name, int item_price, int inventoryAmount, boolean isDeliverable, String portionUnit, InventoryType inventoryType) {
		this.name = name;
		this.item_price = item_price;
		this.inventoryAmount = inventoryAmount;
		this.isDeliverable = isDeliverable;
		this.portionUnit = portionUnit;
		this.inventoryType = inventoryType;
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

	public void setPortionUnit(String portionUnit) {
		this.portionUnit = portionUnit;
	}
	public String getPortionUnit() {
		return portionUnit;
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
	public InventoryType getInventoryType() {
		return inventoryType;
	}
	
	public void setInventoryType(InventoryType inventoryType) {
		this.inventoryType = inventoryType;
	}

	public int getItem_ID() {
		return item_ID;
	}
	
}
