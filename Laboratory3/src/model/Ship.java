package model;

public class Ship{
	//-------------------------------------------------------
	//Constants
	//-------------------------------------------------------
	public final static String NAMESHIP = "El Pirata";
	public final static char DANGEROUS = 'D';
	public final static char PERISHABLE = 'P';
	public final static char NOTPERISHABLE = 'N';
	public final static double PAYMENTKGDANGER = 390000;
	public final static double PAYMENTKGPERISHABLE = 250000;
	public final static double PAYMENTKGNOTPERISHABLE = 80000;
	public final static int MAXCAPACITY = 28000;
	public final static int MINCAPACITY = 12000;
	public final static int MAXLOADS = 50;
	public final static int MINLOADS = 2;
	
	//-------------------------------------------------------
	//Attributes
	//-------------------------------------------------------
	private String nameShip;
	private double weightShip;
	
	//-------------------------------------------------------
	//Relations
	//-------------------------------------------------------
	
	private Load[] listLoads;
	
	//-------------------------------------------------------
	//Methods
	//-------------------------------------------------------
	public Ship(){
		this.nameShip = NAMESHIP;
		this.weightShip = 0;
		listLoads = new Load[MAXLOADS];
	}
	
	/**
	* This method added a load. <br>
	* <b>pre: </b> The loads list is initialized. <br>
	* <b>post:</b> The new load has successfully registered.
	* @param numBoxes Boxes to load.
	* @param weightBox Weight of one box. 
	* @param ownerRegNum Nit of the owner. ownerRegNum != null && ownerRegNum != "".
	* @param typeLoad Type of load.
	* @param valuePaid value paid client.
	* @throws Exception <br>
	* 1. The commercial registration number entered does not match any customer.
	* 2. Load container is at its limit.
	*/
	public String addedLoad(int numBoxes, double weightBox, String ownerRegNum, char typeLoad, double valuePaid){
		String message = "";
		boolean addLoad = false;
		for(int i=0; (i<listLoads.length)&&(!addLoad); i++){
			if(listLoads[i]==null){
				boolean acceptedLoad = compatibleLoads(typeLoad);
				addLoad = true;
				if(acceptedLoad){
					listLoads[i] = new Load(numBoxes, weightBox, ownerRegNum, typeLoad, valuePaid);
					message = "The new load has successfully registered";
				} else{ message = "Error: The new load cannot be registered for sanitary terms";}
			}
		}
		if(!addLoad){ message = "Error: Load container is at its limit";}
		return message;
	}
	
	/**
	* This method verify if the loads are compatible for sanitary terms. <br>
	* <b>pre: </b> The loads list is initialized. <br>
	* <b>post:</b> Return boolean acceptLoad.	
	* @param typeLoad Type of load.	
	*/
	public boolean compatibleLoads(char typeLoad){
		boolean acceptedLoad = true;
		if(typeLoad!=NOTPERISHABLE){
			for(int i=0; (i<listLoads.length)&&(acceptedLoad); i++){
				if(listLoads[i]!=null){
					if((listLoads[i].getTypeLoad()==DANGEROUS)&&(typeLoad==PERISHABLE)){ acceptedLoad = false;} 
					else if((listLoads[i].getTypeLoad()==PERISHABLE)&&(typeLoad==DANGEROUS)){ acceptedLoad = false;} 
				}
			}
		}
		return acceptedLoad;
	}
	
	/**
		* This method start the travel. <br>
		* <b>pre: </b> The loads list is initialized. <br>
		* <b>post:</b> The ship is ready to set sail.
	*/
	public String departureShip(){
		String message = "";
		int numLoads = 0;
		weightShip = 0;
		for(int i=0; i<listLoads.length; i++){
			if(listLoads[i]!=null){ numLoads++;}
		}
		weightShip = calcWeightShip();
		if(weightShip<MINCAPACITY){ message = "The ship cannot set sail, it does not meet the minimum weight ("+MINCAPACITY+" kg)";}
		else if((numLoads<MINLOADS)&&!(weightShip>=MINCAPACITY)){ message = "The ship cannot set sail, it does not meet the number of minimum loads ("+MINLOADS+" loads)";} 
		else if(weightShip>MAXCAPACITY){ message = "The ship cannot sail, it exceeds the maximum weight ("+MAXCAPACITY+" kg)";} 
		else{ message = "The ship is ready to set sail";}
		return message;
	}
	
	/**
	* This method calculate the weight of the ship. <br>
	* <b>pre: </b> The loads list is initialized. <br>
	* <b>post:</b> Return weightShip.	
	*/
	public double calcWeightShip(){
		weightShip = 0;
		for(int i=0; i<listLoads.length; i++){
			if(listLoads[i]!=null){ weightShip = weightShip + (((listLoads[i].getNumBoxes())*(listLoads[i].getWeightBox()))/1000);}
		}
		return weightShip;
	}
	
	/**
	* This method calculate the total value of the loads in the ship. <br>
	* <b>pre: </b> The loads list is initialized. <br>
	* <b>post:</b> Return valuePaid.	
	*/
	public double paymentShip(){
		double valuePaid = 0;
		for(int i=0; i<listLoads.length; i++){
			if(listLoads[i]!=null){ 
				if(listLoads[i].getTypeLoad()==DANGEROUS){ valuePaid += ((listLoads[i].getNumBoxes()*listLoads[i].getWeightBox()/1000)*PAYMENTKGDANGER);} 
				else if(listLoads[i].getTypeLoad()==PERISHABLE){ valuePaid += ((listLoads[i].getNumBoxes()*listLoads[i].getWeightBox()/1000)*PAYMENTKGPERISHABLE);} 
				else if(listLoads[i].getTypeLoad()==NOTPERISHABLE){ valuePaid += ((listLoads[i].getNumBoxes()*listLoads[i].getWeightBox()/1000)*PAYMENTKGNOTPERISHABLE);}
			}
		}
		return valuePaid;
	}
	
	/**
	* This method calculate the total pay of the client. <br>
	* <b>pre: </b> The loads list is initialized. <br>
	* <b>post:</b> Return valuePaid.	
	* @param ownerRegNum Nit of the owner. ownerRegNum != null && ownerRegNum != "".	
	*/
	public double valuePaidClient(String ownerRegNum){
		double valuePaid = 0;
		for(int i=0; i<listLoads.length; i++){
			if(listLoads[i]!=null){
				if(listLoads[i].getOwnerRegNum().equals(ownerRegNum)){
					valuePaid += listLoads[i].getValuePaid();
				}
			}
		}
		return valuePaid;
	}
	
	/**
	* This method calculate the total kg of the client. <br>
	* <b>pre: </b> The loads list is initialized. <br>
	* <b>post:</b> Return kgTransport.	
	* @param ownerRegNum Nit of the owner. ownerRegNum != null && ownerRegNum != "".
	*/
	public double kgTransportClient(String ownerRegNum){
		double kgTransport = 0;
		for(int i=0; i<listLoads.length; i++){
			if(listLoads[i]!=null){
				if(listLoads[i].getOwnerRegNum().equals(ownerRegNum)){
					kgTransport = kgTransport + (listLoads[i].getNumBoxes()*listLoads[i].getWeightBox()/1000);
				}
			}
		}
		return kgTransport;
	}
	
	/**
	* This method calculate the total pay of the client. <br>
	* <b>pre: </b> The loads list is initialized. <br>
	* <b>post:</b> Return valuePaid.	
	* @param ownerRegNum Nit of the owner. ownerRegNum != null && ownerRegNum != "".	
	*/
	public double paymentLoad(int numBoxes, double weightBox, char typeLoad){
		double valuePaid = 0;
		if(typeLoad==DANGEROUS){ valuePaid = (numBoxes*weightBox/1000)*PAYMENTKGDANGER;} 
		else if(typeLoad==PERISHABLE){ valuePaid = (numBoxes*weightBox/1000)*PAYMENTKGPERISHABLE;} 
		else if(typeLoad==NOTPERISHABLE){ valuePaid = (numBoxes*weightBox/1000)*PAYMENTKGNOTPERISHABLE;}
		return valuePaid;
	}
	
	/**
		* This method download a client load. <br>
		* <b>pre: </b> The ship is ready to set sail. <br>
		* <b>post:</b> Charges associated with the client have been eliminated. <br>
		* @param ownerRegNum Nit of the owner. ownerRegNum != null && ownerRegNum != "".
		* @throws Exception <br>
		* 1. No client was found with the business registration number entered.
	*/
	public String offLoadClient(String ownerRegNum){
		for(int i=0; i<listLoads.length; i++){
			if(listLoads[i]!=null){
				if(listLoads[i].getOwnerRegNum().equals(ownerRegNum)){
					listLoads[i] = null;
				}
			}
		}
		String message = "Charges associated with the client have been eliminated";
		return message;
	}
	
	public String getNameShip(){
		return nameShip;
	}
	
	public void setNameShip(String nameShip){
		this.nameShip = nameShip;
	}
	
	public double getWeightShip(){
		return weightShip;
	}
	
	public void setWeightShip(double weightShip){
		this.weightShip = weightShip;
	}
}