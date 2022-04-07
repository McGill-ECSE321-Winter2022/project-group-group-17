package ca.mcgill.ecse321.grocerystoresystem.dto;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.OneToMany;

import ca.mcgill.ecse321.grocerystoresystem.model.InventoryType;
import ca.mcgill.ecse321.grocerystoresystem.model.ItemQuantity;

public class ItemDto {

	private int itemID;
	
	private List<ItemQuantityDto> portionNum;
	
	private int itemPrice;
	private String name;
	private int inventoryAmount;
	private boolean isDeliverable;
	private String portionUnit;
	private InventoryType inventoryType;
	
	public ItemDto() {
	}
	
	public ItemDto(String name, int itemPrice, int inventoryAmount, boolean isDeliverable,
				   String portionUnit, InventoryType inventoryType, int itemID, List<ItemQuantityDto> portionNum) {
		this.itemID = itemID;
		this.name = name;
		this.itemPrice = itemPrice;
		this.inventoryAmount = inventoryAmount;
		this.isDeliverable = isDeliverable;
		this.portionUnit = portionUnit;
		this.inventoryType = inventoryType;
		this.portionNum = portionNum;
	}
	
	public int getItemPrice() {
		return this.itemPrice;
	}

	public void setItemPrice(int itemPrice) {
		this.itemPrice = itemPrice;
	}

	public String getName() {
		return this.name;
	}

	public void setPortionUnit(String portionUnit) {
		this.portionUnit = portionUnit;
	}
	public String getPortionUnit() {
		return this.portionUnit;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getInventoryAmount() {
		return this.inventoryAmount;
	}

	public void setInventoryAmount(int inventoryAmount) {
		this.inventoryAmount = inventoryAmount;
	}

	public boolean isDeliverable() {
		return this.isDeliverable;
	}

	public void setDeliverable(boolean isDeliverable) {
		this.isDeliverable = isDeliverable;
	}
	public InventoryType getInventoryType() {
		return this.inventoryType;
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
