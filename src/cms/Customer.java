package cms;

import java.io.Serializable;

public class Customer implements Serializable {
	public String id;
	public String first_name;
	public String last_name;
	
	// parameterized constructor
	public Customer(String id, String first_name, String last_name) {
		this.id = id;
		this.first_name = first_name;
		this.last_name = last_name;
	}
	
	public Customer(Customer customerObjFromClient) {
		this.id = customerObjFromClient.id;
		this.first_name = customerObjFromClient.first_name;
		this.last_name = customerObjFromClient.last_name;
	}
	
	// copy constructor
//	Customer(Customer customer) {
//		if(customer == null) {
//			System.out.println("ERROR - Customer object is NULL!");
//			System.exit(0);
//		} else {
//			this.first_name = customer.first_name;
//			this.last_name = customer.last_name;
//			this.id = customer.id;
//		}
//	}

}
