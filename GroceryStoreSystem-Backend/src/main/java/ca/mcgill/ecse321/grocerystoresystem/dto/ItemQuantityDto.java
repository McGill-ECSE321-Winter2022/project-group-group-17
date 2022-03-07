package ca.mcgill.ecse321.grocerystoresystem.dto;

public class ItemQuantityDto {
	private int id;
	private int itemQuantity;
	public ItemQuantityDto(int id, int itemQuantity) {
		super();
		this.id = id;
		this.itemQuantity = itemQuantity;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getItemQuantity() {
		return itemQuantity;
	}
	public void setItemQuantity(int itemQuantity) {
		this.itemQuantity = itemQuantity;
	}
	

}
