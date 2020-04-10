package model;

public class Load{
	//-------------------------------------------------------
	//Attributes
	//-------------------------------------------------------
	private int numBoxes;
	private double weightBox;
	private String ownerRegNum;
	private char typeLoad;
	private String loadIdNum;
	private double valuePaid;
	
	//-------------------------------------------------------
	//Methods
	//-------------------------------------------------------
	/**
	* This method is the load constructor. <br>
	* @param numBoxes number of boxes.
	* @param weightBox weight per box.
	* @param ownerRegNum Nit of the owner. ownerRegNum != null && ownerRegNum != "".
	* @param typeLoad Type of load. typeLoad != null && typeLoad != "".
	* @param valuePaid value paid client.
	*/
	public Load(int numBoxes, double weightBox, String ownerRegNum, char typeLoad, double valuePaid){
		this.numBoxes = numBoxes;
		this.weightBox = weightBox;
		this.ownerRegNum = ownerRegNum;
		this.typeLoad = typeLoad;
		this.loadIdNum = loadIdNum;
		this.valuePaid = valuePaid;
	}
	
	public int getNumBoxes(){
		return numBoxes;
	}
	
	public void setNumBoxes(int numBoxes){
		this.numBoxes = numBoxes;
	}
	
	public double getWeightBox(){
		return weightBox;
	}
	
	public void setWeightbox(double weightBox){
		this.weightBox = weightBox;
	}
	
	public String getOwnerRegNum(){
		return ownerRegNum;
	}
	
	public void SetOwnerRegNum(String ownerRegNum){
		this.ownerRegNum = ownerRegNum;
	}
	
	public char getTypeLoad(){
		return typeLoad;
	}
	
	public void setTypeLoad(char typeLoad){
		this.typeLoad = typeLoad;
	}
	
	public double getValuePaid(){
		return valuePaid;
	}
	
	public void setValuePaid(double valuePaid){
		this.valuePaid = valuePaid;
	}
}