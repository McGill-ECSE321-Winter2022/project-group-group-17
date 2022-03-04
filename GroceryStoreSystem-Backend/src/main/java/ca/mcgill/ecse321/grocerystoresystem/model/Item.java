package ca.mcgill.ecse321.grocerystoresystem.model;

import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Item {
	@Id
	@GeneratedValue
	private int itemID;
	
	// Item Associations
//	@OneToMany(mappedBy = "specificItem", cascade = CascadeType.ALL)
//	private List<ItemQuantity> portionNum;
	
	private int itemPrice;
	private String name;
	private int inventoryAmount;
	private boolean isDeliverable;
	private String portionUnit;
	private InventoryType inventoryType;
	
	public Item() {};
	
	public Item(String name, int itemPrice, int inventoryAmount, boolean isDeliverable, String portionUnit, InventoryType inventoryType, int itemID) {
		this.name = name;
		this.itemPrice = itemPrice;
		this.inventoryAmount = inventoryAmount;
		this.isDeliverable = isDeliverable;
		this.portionUnit = portionUnit;
		this.inventoryType = inventoryType;
		this.itemID = itemID;
	}
	
	public Item(String name, int itemPrice, int inventoryAmount, boolean isDeliverable, String portionUnit, InventoryType inventoryType) {
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

	public void setItemPrice(int itemPrice) {
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

	public int getItemID() {
		return itemID;
	}
	
	public void setItemID(int itemID) {
	    this.itemID = itemID;
	}
	
}
