package cms;

import java.io.Serializable;

public class Ticket implements Serializable {
	
	public String id;
	public String movie_name;
	public String timing;
	public String seat_num;
	public String status;
	
	
	public Ticket(String id, String movie_name, String timing, String seat_num, String status) {
		this.id = id;
		this.movie_name = movie_name;
		this.timing = timing;
		this.seat_num = seat_num;
		this.status = status;
	}
	
	public Ticket(Ticket ticketObjectFromClient) {
		this.id = ticketObjectFromClient.id;
		this.movie_name = ticketObjectFromClient.movie_name;
		this.timing = ticketObjectFromClient.timing;
		this.seat_num = ticketObjectFromClient.seat_num;
		this.status = ticketObjectFromClient.status;
	}
	
	
}
