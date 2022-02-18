package ca.mcgill.ecse321.grocerystoresystem.model;

import javax.persistence.*;

@Entity
public class Item {
	@Id
	@GeneratedValue
	private String itemID;
	
	// Item Associations
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "items")
	private Order order;
	@OneToMany(mappedBy = "specificItem", cascade = CascadeType.ALL)
	private ItemQuantity portionNum;
	
	private int itemPrice;
	private String name;
	private int inventoryAmount;
	private boolean isDeliverable;
	private String portionUnit;
	private InventoryType inventoryType;
	
	public Item(String name, String itemPrice, int inventoryAmount, boolean isDeliverable, String portionUnit, InventoryType inventoryType) {
		this.name = name;
		this.itemPrice = itemPrice;
		this.inventoryAmount = inventoryAmount;
		this.isDeliverable = isDeliverable;
		this.portionUnit = portionUnit;
		this.inventoryType = inventoryType;
	}
	

	public int getItemPrice() {
		return itemPrice;
	}

	public void setItemPrice(String itemPrice) {
		this.itemPrice = itemPrice;
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

	public String getItemID() {
		return itemID;
	}
	
//	public void setItemID(String itemID) {
//	    this.itemID = itemID;
//	}
	
}
