package cms;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;


//server side
public class Server {

	// in order to keep track of which client number is connected or disconnected
	// we'll use this variable and when the client connects to the server, we'll
	// increment this value and send it to the client side
	private static int num = 0;
	PrintWriter out_socket;

	public Server(ServerSocket server_socket) throws Exception {

		do {

			System.out.println("Port 2021 is opened!");

			// creating a socket object which accepts the incoming connections, i.e,
			// we're creating this socket to communicate with the Client
			Socket socket = server_socket.accept();

			// creating I/O buffers
			// we need to store data that is coming out of the socket and the data that is
			// coming
			// into the socket

			// we're using this socket for the incoming stream of the data, i.e,
			// reads the data that is coming into the socket from the client to the server
			// InputStreamReader converts the gibberish, if any, from the getInputStream to
			// readable text
			// and BufferedReader is used to put all the data into the buffer
			BufferedReader in_socket = new BufferedReader(new InputStreamReader(socket.getInputStream()));

			// we're using this socket for the outgoing stream of data, which are all the
			// messages that the server sends
			// to the client
			// storing the data into buffer which is coming out of the socket
			// second parameter is true because we've to wait for the buffer to be filled in
			// order for the data
			// to be sent to the client
			out_socket = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()), true);

			// get the input stream from the connected socket
			InputStream inputStream = socket.getInputStream();

			// create a DataInputStream so we can read data from it.
			ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);

			// COMMUNICATION BETWEEN CLIENT AND SERVER

//			out_socket.println(++num);
//			in_socket.readLine();

			// sending a welcome message as server to the client
			out_socket.println("WELCOME TO CINEMA MANAGEMENT SYSTEM");

			while (true) {
				
				// receiving the selected option from the client
				String option = in_socket.readLine();
				System.out.println("In Server: " + option);
				
				// check whether the client has decided to exit or not
				if (option.equals("4")) {
					System.out.println("Client Exiting...\nThank you for visiting us... :)");
					System.out.println("Client " + num + " is disconnected");
					System.out.println("---------------------------------------------------");
					break;
				}
				
				switch (option) {
					case "1.1": {
						out_socket.println("Option " + option + " is selected");
						Movie movieObjFromClient = (Movie) objectInputStream.readObject();
						Movie movie = new Movie(movieObjFromClient);
						addMovie(movie, socket, "MovieDB.txt");
						break;
					}
					case "1.2": {
						out_socket.println("Option " + option + " is selected");
						in_socket.readLine();
						viewMoviesList(socket, "MovieDB.txt");
						break;
					}
					case "1.3": {
						out_socket.println("Option " + option + " is selected");
						String name = in_socket.readLine();
						searching(name, socket, "MovieDB.txt");
						break;
					}
					case "1.4": {
						out_socket.println("Option " + option + " is selected");
						break;
					}
					case "2.1": {
						out_socket.println("Option " + option + " is selected");
						Ticket ticketObjFromClient = (Ticket) objectInputStream.readObject();
						Ticket ticket = new Ticket(ticketObjFromClient);
						bookTicket(ticket, socket, "Ticket.txt");
						break;
					}
					case "2.2": {
						out_socket.println("Option " + option + " is selected");
						String ticketID = in_socket.readLine();
						searching(ticketID, socket, "Ticket.txt");
						break;
					}
					case "2.3": {
						out_socket.println("Option " + option + " is selected");
						Payment paymentObjFromClient = (Payment) objectInputStream.readObject();
						Payment payment = new Payment(paymentObjFromClient);
						makePayment(payment, socket, "Payment.txt");
						break;
					}
					case "2.4": {
						out_socket.println("Option " + option + " is selected");
						String userID = in_socket.readLine();
						out_socket.println(FileDBHandling.deleteData("Ticket.txt", userID));
						break;
					}
					case "2.5": {
						out_socket.println("");
						break;
					}
					case "3.1": {
						out_socket.println("Option " + option + " is selected");
						Customer customerObjFromClient = (Customer) objectInputStream.readObject();
						Customer customer = new Customer(customerObjFromClient);
						addCustomer(customer, socket, "Customer.txt");
						break;
					}
					case "3.2": {
						out_socket.println("Option " + option + " is selected");
						in_socket.readLine();
						viewCustomerDetails(socket, "Customer.txt");
						break;
					}
					case "3.3": {
						out_socket.println("Option " + option + " is selected");
						String customerID = in_socket.readLine();
						out_socket.println(FileDBHandling.deleteData("Customer.txt", customerID));
						break;
					}
					case "3.4": {
						out_socket.println("");
						break;
					}
					default:
						System.out.println("Invalid Choice!");
				}
				
			}

//			Closing the sockets after the communication ends
//			socket.close();
//			server_socket.close();
//			System.out.println("Socket is CLOSED!");

		} while (true);

	}

	private void addCustomer(Customer customer, Socket socket, String file) {
		try {
			String data = customer.id + "\t\t\t" + customer.first_name + "\t\t\t" + customer.last_name;
			String message = FileDBHandling.WriteToFile(data, file);
			PrintWriter out_socket = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()), true);
			out_socket.println(message);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void viewCustomerDetails(Socket socket, String file) {
		try {
			PrintWriter out_socket = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()), true);
			ArrayList<String> data = FileDBHandling.ReadFile(file);
			out_socket.println(data);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void makePayment(Payment payment, Socket socket, String file_3) {
		try {
			String data = payment.uID + "\t\t\t" + payment.num_of_seats + "\t\t\t" + payment.amount;
			String message = FileDBHandling.WriteToFile(data, file_3);
			PrintWriter out_socket = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()), true);
			out_socket.println(message);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void bookTicket(Ticket ticket, Socket socket, String file) {
		try {
			String data = ticket.id + "\t\t\t" + ticket.movie_name + "\t\t\t" + ticket.seat_num + 
					"\t\t\t" + ticket.timing + "\t\t\t" + "Unpaid";
			String message = FileDBHandling.WriteToFile(data, file);
			PrintWriter out_socket = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()), true);
			out_socket.println(message);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void searching(String value, Socket socket, String file) {
		try {
			PrintWriter out_socket = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()), true);
			out_socket.println(FileDBHandling.SearchFile(value, file));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void viewMoviesList(Socket socket, String file) {
		try {
			PrintWriter out_socket = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()), true);
			ArrayList<String> data = FileDBHandling.ReadFile(file);
			out_socket.println(data);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void addMovie(Movie movie, Socket socket, String file) {
		try {
			String data = movie.movie_id + "\t\t\t" + movie.movie_name + "\t\t\t" + movie.language + "\t\t\t"
					+ movie.genre;
			String message = FileDBHandling.WriteToFile(data, file);
//			PrintWriter out_socket = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()), true);
			out_socket.println(message);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


	// entry point of every program
	public static void main(String[] args) {

		// taking care of the exception
		try {
			// server socket is just going to hold the port in the OS for our application
			ServerSocket serverSocket = new ServerSocket(2021); // opening a new port - 2020
			new Server(serverSocket);
		} catch (Exception e) {

			// displaying the exception
			e.printStackTrace();
		}
	}

}
