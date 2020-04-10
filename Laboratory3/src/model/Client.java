package model;

public class Client{
	//-------------------------------------------------------
	//Constants
	//-------------------------------------------------------
	public final static String BASIC = "Basic";
	public final static String SILVER = "Silver";
	public final static String GOLD = "Gold";
	public final static String PLATINUM = "Platinum";
	public final static double UPPLATINUMPAY = 5000000;
	public final static double UPGOLDPAY = 2000000;
	public final static double UPGOLDWEIGHT = 55000;
	public final static double UPSILVERWEIGHT = 35000;
	
	//-------------------------------------------------------
	//Attributes
	//-------------------------------------------------------
	private String nameClient;
	private String comercialRegNum;
	private String expeditionDate;
	private String customerType;
	private double kgTransport;
	private double valuePaid;
	
	//-------------------------------------------------------
	//Methods
	//-------------------------------------------------------
	/**
	* This method is the client constructor. <br>
	* @param nameClient Client's name.
	* @param comercialRegNum the comercial register number of client.
	* @param expeditionDate number expedition of comercialRegNum.
	*/
	public Client(String nameClient, String comercialRegNum, String expeditionDate){
		this.nameClient = nameClient;
		this.comercialRegNum = comercialRegNum;
		this.expeditionDate = expeditionDate;
		this.customerType = BASIC;
		this.kgTransport = 0;
		this.valuePaid = 0;
	}
	
	/**
	* This method update the type of client. <br>
	* <b>pre: </b> The list clients is initialized. <br>
	* <b>post:</b> Return type. This is a new type of client.
	* @param kgTransport A total of clients.
	* @param valuePaid Clients pay for loads.
	*/
	public String updateType(double kgTransport, double valuePaid){
		String type = "";
		if(valuePaid>=UPPLATINUMPAY){ type = PLATINUM;} 
		else if((valuePaid>=UPGOLDPAY)||(kgTransport>=UPGOLDWEIGHT)){ type = GOLD;} 
		else if(kgTransport>=UPSILVERWEIGHT){ type = SILVER;} 
		else{ type = BASIC;}
		return type;
	}
	
	public String getNameClient(){
		return nameClient;
	}
	
	public void setNameClient(String nameClient){
		this.nameClient = nameClient;
	}
	
	public String getComercialRegNum(){
		return comercialRegNum;
	}
	
	public void setComercialRegNum(String comercialRegNum){
		this.comercialRegNum = comercialRegNum;
	}
	
	public String getExpeditionDate(){
		return expeditionDate;
	}
	
	public void setExpeditionDate(String expeditionDate){
		this.expeditionDate = expeditionDate;
	}
	
	public String getCustomerType(){
		return customerType;
	}
	
	public void setCustomerType(String customerType){
		this.customerType = customerType;
	}
	
	public double getKgTransport(){
		return kgTransport;
	}
	
	public void setKgTransport(double kgTransport){
		this.kgTransport = kgTransport;
	}
	
	public double getValuePaid(){
		return valuePaid;
	}
	
	public void setValuePaid(double valuePaid){
		this.valuePaid = valuePaid;
	}
	
}