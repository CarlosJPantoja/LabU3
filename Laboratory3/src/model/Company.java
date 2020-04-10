package model;

public class Company{
	//-------------------------------------------------------
	//Constants
	//-------------------------------------------------------
	public final static String OWNER = "Morgan";
	public final static int MAXCLIENTS = 5;
	public final static double DISCOUNTSILVER = 0.015;
	public final static double DISCOUNTGOLD = 0.03;
	public final static double DISCOUNTPLATINUM = 0.05;
	
	//-------------------------------------------------------
	//Attributes
	//-------------------------------------------------------
	private String nameCompany;
	private String ownerCompany;
	
	//-------------------------------------------------------
	//Relations
	//-------------------------------------------------------
	
	private Client[] listClients;
	private Ship mainShip;
	
	//-------------------------------------------------------
	//Methods
	//-------------------------------------------------------
	/**
	* This method is the company constructor. <br>
	* @param nameCompany Company's name.
	*/
	public Company(String nameCompany){
		this.nameCompany = nameCompany;
		this.ownerCompany = OWNER;
		listClients = new Client[MAXCLIENTS];
		mainShip = new Ship();
	}
	
	/**
	* This method search a client. <br>
	* <b>pre: </b> The clients list is initialized. <br>
	* <b>post: </b> Return existingCustomer. <br>
	* @param comercialRegNum ID client. pNit != null && pNit != "".
	*/
	public Client searchClient(String comercialRegNum){
		Client existingCustomer = null;
		boolean customerFound = false;
		for(int i=0; (i<listClients.length)&&(!customerFound); i++){
			if(listClients[i]!=null){
				if(listClients[i].getComercialRegNum().equalsIgnoreCase(comercialRegNum)){
					existingCustomer = listClients[i];
					customerFound = true;
				}
			}
		}
		return existingCustomer;
	}
	
	/**
	* This method added a client. <br>
	* <b>pre: </b> The clients list is initialized. <br>
	* <b>post:</b> The new client has successfully registered. <br>
	* @param nameClient Client name. nameClient != null && nameClient != "".
	* @param comercialRegNum Client ID. comercialRegNum != null && comercialRegNum != "".
	* @param expeditionDate ID expedition date expeditionDate != null && expeditionDate != "".
	* @throws Exception <br>
	* 1. The client entered already exists.
	* 2. Client container is at its limit.
	*/
	public String addClient(String nameClient, String comercialRegNum, String expeditionDate){
		String message = "";
		boolean addedCustomer = false;
		Client existingCustomer = searchClient(comercialRegNum);
		if(existingCustomer!=null){ message = "Error: The client entered already exists";} 
		else{
			for(int i=0; (i<listClients.length)&&(!addedCustomer); i++){
				if(listClients[i]==null){
					listClients[i] = new Client(nameClient, comercialRegNum, expeditionDate);
					addedCustomer = true;
					message = "The new client has successfully registered";
				}
			}
			if(!addedCustomer){ message = "Error: Client container is at its limit";}
		}
		return message;
	}
	
	/**
	* This method delete a client. <br>
	* <b>pre: </b> The clients list is initialized. <br>
	* <b>post: </b> The client has been successfully removed. <br>
	* @param comercialRegNum Client ID. comercialRegNum != null && comercialRegNum != "".
	* @return message <br>
	* @throws Exception <br>
	* 1. No client was found with the business registration number entered.
	*/
	public String deleteClient(String comercialRegNum){
		String message = "";
		boolean deleteCustomer = false;
		for(int i=0; (i<listClients.length)&&(!deleteCustomer); i++){
			if(listClients[i]!=null){
				if(listClients[i].getComercialRegNum().equalsIgnoreCase(comercialRegNum)){
					message = "The client: "+listClients[i].getNameClient()+", has been successfully removed";
					listClients[i] = null;
					deleteCustomer = true;
				}
			}
		}
		if(!deleteCustomer){ message = "Error: No client was found with the business registration number entered";}
		return message;
	}
	
	/**
	* This method added a load. <br>
	* <b>pre: </b> The loads list is initialized. <br>
	* <b>post:</b> The new load has successfully registered.
	* @param numBoxes Boxes to load.
	* @param weightBox Weight of one box. 
	* @param ownerRegNum Nit of the owner. ownerRegNum != null && ownerRegNum != "".
	* @param typeLoad Type of load.
	* @throws Exception <br>
	* 1. The commercial registration number entered does not match any customer.
	* 2. Load container is at its limit.
	*/
	public String addedLoad(int numBoxes, double weightBox, String ownerRegNum, char typeLoad){
		String message = "";
		Client existingCustomer = searchClient(ownerRegNum);
		if(existingCustomer==null){ message = "Error: The commercial registration number entered does not match any customer";} 
		else{ 
			String customerType = existingCustomer.getCustomerType();
			double valuePaid = mainShip.paymentLoad(numBoxes, weightBox, typeLoad);
			valuePaid = discountClient(valuePaid, typeLoad, customerType, existingCustomer);
			message = mainShip.addedLoad(numBoxes, weightBox, ownerRegNum, typeLoad, valuePaid);
			if(message.equals("The new load has successfully registered")){
				message = message+"\nThe client: "+existingCustomer.getNameClient()+". He will pay a total of "+valuePaid+" pesos";
			}
		}
		return message;
	}
	
	/**
	* This method discount price at client. <br>
	* <b>pre: </b> The loads list is initialized. <br>
	* @param valuePaid value paid client.
	* @param typeLoad Type of load.
	* @param customerType Type of client. customerType != null && customerType != "".
	* @param mainClient Client. mainClient != null.
	*/
	public double discountClient(double valuePaid, char typeLoad, String customerType, Client mainClient){
		if(customerType.equals(mainClient.PLATINUM)){
			valuePaid -= (valuePaid*DISCOUNTPLATINUM);
		} else if(customerType.equals(mainClient.GOLD)&&((typeLoad==mainShip.PERISHABLE)||typeLoad==mainShip.NOTPERISHABLE)){
			valuePaid -= (valuePaid*DISCOUNTGOLD);
		} else if(customerType.equals(mainClient.SILVER)&&(typeLoad==mainShip.PERISHABLE)){
			valuePaid -= (valuePaid*DISCOUNTSILVER);
		}
		return valuePaid;
	}
	
	/**
		* This method start the travel. <br>
		* <b>pre: </b> The loads list is initialized. <br>
		* <b>post:</b> The ship is ready to set sail.
		* @param typeMessage This decided a message return.
	*/
	public String departureShip(boolean typeMessage){
		String message = "";
		if(typeMessage){
			message = mainShip.departureShip();
		} else{
			double valuePaid = mainShip.paymentShip();
			double weightShip = mainShip.calcWeightShip();
			message = "A total of "+weightShip+"kg for a value of "+valuePaid+" pesos";
		}
		return message;
	}
	
	/**
	* This method update the type of client. <br>
	* <b>pre: </b> The list clients is initialized. <br>
	* <b>post:</b> Set customerType.
	*/
	public void updateClients(){
		for(int i=0; i<listClients.length; i++){
			if(listClients[i]!=null){
				double valuePaid = mainShip.valuePaidClient(listClients[i].getComercialRegNum());
				double kgTransport = mainShip.kgTransportClient(listClients[i].getComercialRegNum());
				valuePaid += listClients[i].getValuePaid();
				kgTransport += listClients[i].getKgTransport();
				String customerType = listClients[i].updateType(kgTransport, valuePaid);
				listClients[i].setCustomerType(customerType);
				listClients[i].setValuePaid(valuePaid);
				listClients[i].setKgTransport(kgTransport);
			}
		}
	}
	
	/**
		* This method download a client load. <br>
		* <b>pre: </b> The ship is ready to set sail. <br>
		* <b>post:</b> download client load. <br>
		* @param ownerRegNum Nit of the owner. ownerRegNum != null && ownerRegNum != "".
		* @throws Exception <br>
		* 1. No client was found with the business registration number entered.
	*/
	public String offLoadClient(String ownerRegNum){
		String message = "";
		Client existingCustomer = searchClient(ownerRegNum);
		if(existingCustomer!=null){
			message = mainShip.offLoadClient(ownerRegNum);
		} else{ message = "Error: No client was found with the business registration number entered";}
		return message;
	}
	
	/**
	* This method download the ship. <br>
	* <b>pre: </b> The loads list is initialized. <br>
	*/
	public void clearShip(){
		mainShip = new Ship();
	}
	
	public String getNameCompany(){
		return nameCompany;
	}
	
	public void setNameCompany(String nameCompany){
		this.nameCompany = nameCompany;
	}
	
	public String getOwnerCompany(){
		return ownerCompany;
	}
	
	public void setOwnerCompany(String ownerCompany){
		this.ownerCompany = ownerCompany;
	}
}