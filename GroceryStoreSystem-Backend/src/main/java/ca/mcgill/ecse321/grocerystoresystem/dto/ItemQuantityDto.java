package ca.mcgill.ecse321.grocerystoresystem.dto;

public class ItemQuantityDto {
	private int quantityID;
	private int itemNum;

	public ItemQuantityDto(int itemNum, int quantityID){
		super();
		this.itemNum = itemNum;
		this.quantityID = quantityID;
	}

	public int getQuantityID() {
		return quantityID;
	}
	public void setQuantityID(int quantityID) {
		this.quantityID = quantityID;
	}
	public int getItemNum() {
		return itemNum;
	}
	public void setItemNum(int itemNum) {
		this.itemNum = itemNum;
	}
}
