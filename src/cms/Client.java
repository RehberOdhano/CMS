package cms;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Client {

	// client side
	public Client() throws Exception {

		// unlike server side, we've only regular communication socket on the client
		// side

		// passing the two parameters
		// 1. IP address of the server
		// 2. Listening port - port number that is our server is expecting for
		// connection

		// local host - server is on this machine
		// creating a socket object and connecting to the server by passing the address
		// of the server
		// and the port number
		Socket socket = new Socket("localhost", 2021);

		// creating I/O buffers
		// we need to store data that is coming out of the socket and the data that is
		// coming
		// into the socket

		// reads the data that is coming into the socket
		BufferedReader in_socket = new BufferedReader(new InputStreamReader(socket.getInputStream()));

		// storing the data into buffer which is coming out of the socket
		PrintWriter out_socket = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()), true);

		// get the output stream from the socket.
		OutputStream outputStream = socket.getOutputStream();

		// create an object output stream from the output stream so we can send an
		// object through it
		ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);

		// displaying the successful connection message
		System.out.println("Successful Connection to the Server!");
		System.out.println("Client " + in_socket.readLine() + " is connected successfully!");
		out_socket.println("");

		// receiving the message from the server and storing it in a string
		String message = in_socket.readLine();
		System.out.println("****************************************");
		System.out.println(message.toUpperCase());
		System.out.println("****************************************");

		Scanner keyboard = new Scanner(System.in);
		Scanner input = new Scanner(System.in);
		Scanner intInput = new Scanner(System.in);

		int choice, num_of_seats = 0, cost = 0;
		String name, id, language, genre, timing;
		String first_name, last_name, seat_nums;

		// creating an instance of the Random class
		Random random = new Random();
		// any random number between 10 and 100 is assigned to the cost
		int lower_bound = 10;
		int upper_bound = 100;

		do {
			System.out.println("\nMENU");
			System.out.println("1. MOVIES MENU");
			System.out.println("2. TICKETS MENU");
			System.out.println("3. CUSTOMERS MENU");
			System.out.println("4. EXIT");

			System.out.println("Select an option from the menu - [1-4]");
			choice = intInput.nextInt();

			if (choice <= 0 || choice > 4) {
				System.out.println("Please select a correct option - Try Again");
			} else {
				switch (choice) {
				case 1: {
					do {
						String sub_choice = "";
						System.out.println("\n1.1  ADD MOVIES TO THE DATABASE");
						System.out.println("1.2  VIEW MOVIES");
						System.out.println("1.3  SEARCH MOVIES");
						System.out.println("1.4  EXIT");

						System.out.println("Select an option from the menu - [1-4]");
						choice = intInput.nextInt();

						if (choice <= 0 || choice > 4) {
							System.out.println("Please select a correct option - Try Again");
						} else {
							sub_choice = "1." + Integer.toString(choice);
							switch (choice) {
							case 1: {
								out_socket.println(sub_choice);
								System.out.println(in_socket.readLine());
								System.out.println("Enter the following details...");

								// these "do while" loops are used so that user, without entering any data,
								// user don't move forward
								do {
									System.out.print("Enter movie ID: ");
									id = input.nextLine();
									if (id.isEmpty()) {
										System.out.println("Please enter a valid ID!");
										continue;
									}
									String result = FileDBHandling.SearchFile(id, "MovieDB.txt");
									if (result.contains("available")) {
										System.out.println("The movie with this ID is already available in the database! "
														+ "Please add another one...");
										continue;
									}
									break;
								} while (true);

								do {
									System.out.print("Enter movie name: ");
									name = input.nextLine();
									if (name.isEmpty()) {
										System.out.println("Please enter a valid movie name!");
										continue;
									}

									String result = FileDBHandling.SearchFile(name, "MovieDB.txt");
									if (result.contains("available")) {
										System.out.println("This movie is already available in the database! "
												+ "Please add another one...");
										continue;
									}
									break;
								} while (true);

								do {
									System.out.print("Enter the language of the movie: ");
									language = input.nextLine();
									if (language.isEmpty()) {
										System.out.println("Please enter a valid language!");
										continue;
									}
									break;
								} while (true);

								do {
									System.out.print("Enter the genre of movie: ");
									genre = input.nextLine();
									if (genre.isEmpty()) {
										System.out.println("Please enter a valid genre!");
										continue;
									} 
									break;
								} while (true);

								// setting the values of movie object with user defined data using the
								// constructor
								Movie movie = new Movie(id, name, language, genre);

								// sending the object to the server
								objectOutputStream.writeObject(movie);

								// getting the response from the server
								System.out.println("\n" + in_socket.readLine());
								break;
							}
							case 2: {
								out_socket.println(sub_choice);
								System.out.println(in_socket.readLine());
								out_socket.println("");

								// getting the file data from the server with the help of an array list
								ArrayList<String> fileContent = new ArrayList<String>(
										Arrays.asList(in_socket.readLine()));

								// System.out.println("Movies List");
								// System.out.println("ID\t\t\tName\t\t\tLanguage\t\t\tGenre");

								String[] rawData = fileContent.get(0).split(",");

								// traversing the array list
								if (!rawData[0].contains("null")) {
									System.out.println("MOVIES DETAILS");
									System.out.println("****************");
									System.out.println("ID\t\t\tNAME\t\t\tLANGUAGE\t\t\tGENRE");
									System.out.println(
											"*************************************************************************************");
									for (int i = 0; i < rawData.length; i++) {
										// checks if the file is empty or not
										// if (rawData[i].contains("null")) {
										// System.out.println("Database is empty!");
										// break;
										// }
										System.out.println(rawData[i]);
									}
								} else {
									System.out.println("Database is empty!");
								}
								break;
							}
							case 3: {
								out_socket.println(sub_choice);
								System.out.println(in_socket.readLine());
								System.out.print("Enter the movie name: ");
								name = input.nextLine();
								// sending the name of the movie that the user wants to search
								// and this string is always passed as a lower case string
								out_socket.println(name.toLowerCase());
								System.out.println(in_socket.readLine());
								break;
							}
							case 4: {
								out_socket.println(sub_choice);
								System.out.println(in_socket.readLine());
								break;
							}
							default:
								System.out.println("ERROR");
							}
						}
					} while (choice != 4);
					break;
				}
				case 2: {
					do {
						String sub_choice = "";
						System.out.println("\n2.1  BOOK A TICKET");
						System.out.println("2.2  SEARCH BOOKED TICKETS");
						System.out.println("2.3  MAKE PAYMENT");
						System.out.println("2.4  CANCEL BOOKING");
						System.out.println("2.5  EXIT");

						System.out.println("Select an option from the menu - [1-5]");

						choice = intInput.nextInt();

						if (choice <= 0 || choice > 5) {
							System.out.println("Please select a correct option - Try Again");
						} else {
							sub_choice = "2." + Integer.toString(choice);
							switch (choice) {
							case 1: {
								out_socket.println(sub_choice);
								System.out.println(in_socket.readLine());

								System.out.println("Enter the following details...");

								// these "do while" loops are used so that user, without entering any data,
								// user don't move forward
								do {
									System.out.print("Enter a ID: ");
									id = input.nextLine();
									if (id.isEmpty()) {
										System.out.println("Please enter a valid ID!");
										continue;
									} else {
										String result = FileDBHandling.SearchFile(id + "/", "Ticket.txt");
										if (result.contains("Ticket")) {
											System.out.println("The ticket with this user-id is already booked!");
											continue;
										}
										break;
									}
								} while (true);

								do {
									System.out.print("Enter movie name: ");
									name = input.nextLine();
									if (name.isEmpty()) {
										System.out.println("Please enter a valid movie name!");
										continue;
									} else {
										String result = FileDBHandling.SearchFile(name, "MovieDB.txt");
										if (result.contains("not")) {
											System.out.println("This movie, " + name
													+ ", is not available. Please try another one...");
											continue;
										} else {
											break;
										}
									}
								} while (true);

								do {
									seat_nums = "";
									System.out.print("Enter the number of seats: ");
									num_of_seats = intInput.nextInt();
									if (num_of_seats <= 0) {
										System.out.println("Please enter a valid number!");
										continue;
									} else {
										String seats = "";
										while (num_of_seats != 0) {
											System.out.println("Enter seat number: ");
											seats = input.nextLine();
											seat_nums += seats + ",";
											num_of_seats--;
										}
										break;
									}
								} while (true);

								do {
									System.out.print("Enter the timing of the movie: ");
									timing = input.nextLine();
									if (timing.isEmpty()) {
										System.out.println("Please enter a valid timing!");
										continue;
									} else
										break;
								} while (true);

								// setting the values of ticket and customer object with user defined data
								// using the constructor
								Ticket ticket = new Ticket(id, name, timing, seat_nums, "Unpaid");

								// sending the object to the server
								objectOutputStream.writeObject(ticket);

								// getting the response from the server
								System.out.println("\n" + in_socket.readLine());
								break;
							}
							case 2: {
								out_socket.println(sub_choice);
								System.out.println(in_socket.readLine());
								do {
									System.out.println("Enter the ticket id: ");
									id = input.nextLine();
									if (id.isEmpty()) {
										System.out.println("Please enter a valid ID!");
										continue;
									}
									break;
								} while (true);

								out_socket.println(id);
								System.out.println(in_socket.readLine());
								break;
							}
							case 3: {
								out_socket.println(sub_choice);
								System.out.println(in_socket.readLine());

								System.out.println("Enter the following details...");
								String uID = "";
								do {
									seat_nums = "";
									System.out.print("Enter a ID: ");
									id = input.nextLine();
									if (id.isEmpty()) {
										System.out.println("Please enter a valid ID!");
										continue;
									}

									String result = FileDBHandling.SearchFile(id + "P", "Ticket.txt");
									if (result.contains("NOT") || result.contains("payment")) {
										System.out.println(result);
										continue;
									}
									uID = id;
									break;

								} while (true);

								do {
									System.out.print("Enter number of seats: ");
									num_of_seats = intInput.nextInt();
									if (num_of_seats <= 0) {
										System.out.println("Please enter a valid number!");
										continue;
									}

									String result = FileDBHandling
											.SearchFile(Integer.toString(num_of_seats) + "*" + uID, "Ticket.txt");
									if (result.contains("valid")) {
										System.out.println(result);
										continue;
									}
									break;
								} while (true);

								cost = (random.nextInt(upper_bound - lower_bound) + lower_bound) * num_of_seats;

								Payment payment = new Payment(id, Integer.toString(num_of_seats), cost);
								objectOutputStream.writeObject(payment);

								System.out.println("\n" + in_socket.readLine());
								break;
							}
							case 4: {
								out_socket.println(sub_choice);
								System.out.println(in_socket.readLine());

								System.out.println("Enter the following details...");
								do {
									System.out.print("Enter a ID: ");
									id = input.nextLine();

									if (id.isEmpty()) {
										System.out.println("Please enter a valid ID!");
										continue;
									}

									String result = FileDBHandling.SearchFile(id + "#", "Ticket.txt");
									if (result.contains("not")) {
										System.out.println(result);
										continue;
									}

									result = FileDBHandling.SearchFile(id + "#", "Payment");
									if (result.contains("not")) {
										System.out.println(result);
										continue;
									}
									break;
								} while (true);

								out_socket.println(id);
								System.out.println(in_socket.readLine());
								break;
							}
							case 5: {
								out_socket.println(sub_choice);
								in_socket.readLine();
								break;
							}
							default:
								System.out.println("ERROR");
							}
						}

					} while (choice != 5);
					break;
				}

				case 3: {
					do {
						String sub_choice = "";
						System.out.println("\n3.1  ADD CUSTOMERS DATA");
						System.out.println("3.2  VIEW CUSTOMERS DATA");
						System.out.println("3.3  DELETE CUSTOMERS DATA");
						System.out.println("3.4  EXIT");

						System.out.println("Select an option from the menu - [1-4]");

						choice = intInput.nextInt();

						if (choice <= 0 || choice > 4) {
							System.out.println("Please select a correct option - Try Again");
						} else {
							sub_choice = "3." + Integer.toString(choice);
							switch (choice) {

							case 1: {
								out_socket.println(sub_choice);
								System.out.println(in_socket.readLine());

								System.out.println("Enter the following details...");

								// these "do while" loops are used so that user, without entering any data,
								// user don't move forward
								do {
									System.out.print("Enter a ID: ");
									id = input.nextLine();
									if (id.isEmpty()) {
										System.out.println("Please enter a valid ID!");
										continue;
									} else {
										String result = FileDBHandling.SearchFile(id, "Customer.txt");
										if (result.contains("customer")) {
											System.out.println("The customer with this user-id is already added!");
											continue;
										}
										break;
									}
								} while (true);

								do {
									System.out.print("Enter your first name: ");
									first_name = input.nextLine();
									if (first_name.isEmpty()) {
										System.out.println("Please enter a valid first name!");
										continue;
									} else
										break;
								} while (true);

								do {
									System.out.print("Enter your last name: ");
									last_name = input.nextLine();
									if (last_name.isEmpty()) {
										System.out.println("Please enter a valid last name!");
										continue;
									} else
										break;
								} while (true);

								// setting the values of ticket and customer object with user defined data
								// using the constructor
								Customer customer = new Customer(id, first_name, last_name);

								// sending the object to the server
								objectOutputStream.writeObject(customer);

								// getting the response from the server
								System.out.println("\n" + in_socket.readLine());
								break;
							}
							case 2: {
								out_socket.println(sub_choice);
								System.out.println(in_socket.readLine());
								out_socket.println("");

								// getting the file data from the server with the help of an array list
								ArrayList<String> fileContent = new ArrayList<String>(
										Arrays.asList(in_socket.readLine()));

								String[] rawData = fileContent.get(0).split(",");
								//System.out.println("3.2: size: " + rawData.length);
								// traversing the array list
								if(!rawData[0].contains("null")) {
									System.out.println("CUSTOMER DETAILS");
									System.out.println("*****************");
									System.out.println("ID\t\t\tFIRST_NAME\t\t\tLAST_NAME");
									System.out.println("*********************************************************************");
									for (int i = 0; i < rawData.length; i++) {
										// checks if the file is empty or not
//										if (rawData[i].contains("null")) {
//											System.out.println("Database is empty!");
//											break;
//										}
										System.out.println(rawData[i]);
									}
								} else {
									System.out.println("Database is empty!");
								}
								break;
							}
							case 3: {
								out_socket.println(sub_choice);
								System.out.println(in_socket.readLine());

								System.out.println("Enter the following details...");
								do {
									System.out.print("Enter a ID: ");
									id = input.nextLine();

									if (id.isEmpty()) {
										System.out.println("Please enter a valid ID!");
										continue;
									}

									String result = FileDBHandling.SearchFile(id, "Customer.txt");
									if (result.contains("not")) {
										System.out.println(result);
										continue;
									}
									break;
								} while (true);

								out_socket.println(id);
								System.out.println(in_socket.readLine());
								break;
							}
							case 4: {
								out_socket.println(sub_choice);
								in_socket.readLine();
								break;
							}
							default:
								System.out.println("ERROR");
							}
						}
					} while (choice != 4);
					break;
				}
				case 4: {
					out_socket.println(choice);
					in_socket.readLine();
					out_socket.println("");
					break;
				}
				default:
					System.out.println("ERROR");
				}
			}

		} while (choice != 4);

		// closing the client when option 4 is selected
		socket.close();
		keyboard.close();
		input.close();
		intInput.close();
		System.out.println("Client Socket is CLOSED!");

	}

	public static void main(String[] args) {

		// taking care of the exception
		try {
			new Client();
		} catch (Exception e) {
			// displaying the exception
			e.printStackTrace();
		}
	}
}
