package cms;

import java.io.Serializable;

public class Payment implements Serializable {
	public String uID;
	public String num_of_seats;
	public int amount;
	//public String status;
	
	public Payment(String uID, String num_of_seats, int amount) {
		this.uID = uID;
		this.num_of_seats = num_of_seats;
		this.amount = amount;
		//this.status = status;
	}
	
	public Payment(Payment paymentObjectFromClient) {
		this.uID = paymentObjectFromClient.uID;
		this.num_of_seats = paymentObjectFromClient.num_of_seats;
		this.amount = paymentObjectFromClient.amount;
	}
}
