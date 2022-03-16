package ca.mcgill.ecse321.grocerystoresystem.dto;

public class ItemQuantityDto {
	private int id;
	private int itemNum;

	public ItemQuantityDto(int itemNum){
		super();
		this.itemNum = itemNum;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getItemNum() {
		return itemNum;
	}
	public void setItemNum(int itemNum) {
		this.itemNum = itemNum;
	}
}
