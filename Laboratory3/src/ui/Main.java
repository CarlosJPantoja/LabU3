package ui;

import model.Company;
import java.util.Scanner;
import java.io.IOException;

public class Main{
	//-------------------------------------------------------
	//Attributes
	//-------------------------------------------------------
	private Company mainCompany;
	
	//-------------------------------------------------------
	//Global Variables
	//-------------------------------------------------------
	public static Scanner lector;
	
	//-------------------------------------------------------
	//Methods
	//-------------------------------------------------------
	/**
		* This method is a main constructor. <br>
		* <b>post:</b> The mainCompany has been created. <br>
	*/
	public Main(){
		createCompany();
	}
	
	public static void main(String[] args) {
		lector = new Scanner(System.in);
		Main mainController = new Main();
		int option = 0;
		do{
			System.out.println("\nWelcome to the options menu \n1: Clients \n2: Loads\n3: Ship");
			option = lector.nextInt();
			lector.nextLine();
			switch(option){
				case 1:
					System.out.println("\nWelcome to the client's menu \n1: Add Client \n2: Delete Client");
					option = lector.nextInt();
					lector.nextLine();
					switch(option){
						case 1:
							mainController.loginClient();
						break;
						
						case 2:
							mainController.deleteClient();
						break;
					}
				break;
				
				case 2:
					System.out.println("\nWelcome to the load's menu \n1: Add load \n2: Delete load");
					option = lector.nextInt();
					lector.nextLine();
					switch(option){
						case 1:
							mainController.addedLoad();
						break;
						
						case 2:
							mainController.deleteLoad();
						break;
					}
				break;
				
				case 3:
					System.out.println("\nWelcome to the load's menu \n1: Verify Ship \n2: Empty Ship");
					option = lector.nextInt();
					lector.nextLine();
					switch(option){
						case 1:
							mainController.departureShip();
						break;
						
						case 2:
							mainController.emptyShip();
						break;
					}
				break;
				
				default:
					System.out.println("\nThe option entered is not valid");
				break;
			}
			int bucle = 0, add = 0;
			do{
				if(add!=0){System.out.println("\nThe option entered is not valid, please try again");}
				System.out.println("\n00: Return to the menu\n01: End program execution");
				option = lector.nextInt(); lector.nextLine();
				if(option == 0){cls(); bucle=0;} else if(option == 1){bucle=0;} else{bucle=1; add++;}
			}while(bucle==1);
		} while(option == 0);
	}
	
	/**
		* This method create the company. <br>
		* <b>pre: </b> The company name. <br>
		* <b>post:</b> The company has been created. <br>
	*/
	public void createCompany(){
		cls();
		System.out.println("Welcome to the program");
		System.out.println("First, type the name of the company");
		String nameCompany = lector.nextLine();
		mainCompany = new Company(nameCompany);
	}
	
	/**
		* This method added a client. <br>
		* <b>pre: </b> The list clients is initialized. <br>
		* <b>post:</b> The new client has successfully registered. <br>
		* @throws Exception <br>
		* 1. The client entered already exists.
		* 2. Client container is at its limit.
	*/
	public void loginClient(){
		System.out.println("Type the client's name");
		String nameClient = lector.nextLine();
		System.out.println("Enter the client's business registration number");
		String comercialRegNum = lector.nextLine();
		System.out.println("Enter the date of issue of the client's business registration number");
		String expeditionDate = lector.nextLine();
		String message = mainCompany.addClient(nameClient, comercialRegNum, expeditionDate);
		System.out.println(message);
	}
	
	/**
		* This method delete a client. <br>
		* <b>pre: </b> The list clients is initialized. <br>
		* <b>post: </b> The client has been successfully removed. <br>
		* @throws Exception <br>
		* 1. No client was found with the business registration number entered.
	*/
	public void deleteClient(){
		System.out.println("Enter the client's business registration number");
		String comercialRegNum = lector.nextLine();
		String message = mainCompany.deleteClient(comercialRegNum);
		System.out.println(message);
	}
	
	/**
		* This method add a load. <br>
		* <b>pre: </b> The list loads is initialized. <br>
		* <b>post:</b> The new load has successfully registered. <br>
		* @throws Exception <br>
		* 1. No client was found with the business registration number entered.
		* 2. Load container is at its limit.
	*/
	public void addedLoad(){
		System.out.println("Enter the client's business registration number");
		String ownerRegNum = lector.nextLine();
		System.out.println("Enter the load's type (capital letter)\nD: DANGEROUS\nP: PERISHABLE\nN: NOTPRESHISHABLE");
		char typeLoad = lector.nextLine().charAt(0);
		System.out.println("Enter the number of boxes");
		int numBoxes = lector.nextInt();
		lector.nextLine();
		System.out.println("Enter weight per box (Grams)");
		double weightBox = lector.nextDouble();
		lector.nextLine();
		String message = mainCompany.addedLoad(numBoxes, weightBox, ownerRegNum, typeLoad);
		System.out.println(message);
	}
	
	/**
		* This method delete a load. <br>
		* <b>pre: </b> The list loads is initialized. <br>
		* <b>post:</b> Charges associated with the client have been eliminated. <br>
		* @throws Exception <br>
		* 1. No client was found with the business registration number entered.
	*/
	public void deleteLoad(){
		System.out.println("Enter the client's business registration number");
		String ownerRegNum = lector.nextLine();
		String message = mainCompany.offLoadClient(ownerRegNum);
		System.out.println(message);
	}
	
	/**
		* This method empty ship. <br>
		* <b>post:</b> The ship has been unloaded. <br>
	*/
	public void emptyShip(){
		mainCompany.clearShip();
		System.out.println("The ship has been unloaded, it is ready to be loaded again");
	}
	
	/**
		* This method start the travel. <br>
		* <b>pre: </b> The list loads is initialized. <br>
		* <b>post:</b> The ship is ready to set sail. <br>
		* @throws Exception <br>
		* 1. The ship cannot set sail, it does not meet the minimum weight.
		* 2. The ship cannot set sail, it does not meet the number of minimum loads.
		* 3. The ship cannot sail, it exceeds the maximum weight.
	*/
	public void departureShip(){
		String message = "";
		message = mainCompany.departureShip(true);
		System.out.println(message);
		if(message.equals("The ship is ready to set sail")){
			message = mainCompany.departureShip(false);
			System.out.println(message);
			System.out.println("Do you want the ship to sail?\n1: YES! Let's sail!\n2: No, go back to the menu");
			int option = lector.nextInt();
			lector.nextLine();
			switch(option){
				case 1:
					mainCompany.updateClients();
					mainCompany.clearShip();
					System.out.println("\nThe trip has been carried out successfully, the boat is ready to be loaded again");
					System.out.println("\nAlso the clients categories have been updated");
				break;
				
				case 2:
					System.out.println("\nBack to the menu!");
				break;
				
				default:
					System.out.println("\nThe option entered is not valid. Back to the menu!");
				break;
			}
		}
	}
	
	/**
		* This method empty screen. <br>
	*/
	public static void cls(){
		try{ new ProcessBuilder("cmd","/c","cls").inheritIO().start().waitFor();}
		catch(Exception E){ System.out.println(E);}
	}
}