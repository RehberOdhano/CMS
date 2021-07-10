package cms;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class FileDBHandling {

	// function for writing data to the file
	public static String WriteToFile(String data, String file) throws IOException {
		String message = "";
		if (file.contains("MovieDB.txt")) {
			message = "Movie is added to the database successfully!";
		}

		if (file.contains("Ticket.txt")) {
			message = "Ticket is successfully booked!";
		}

		if (file.contains("Payment.txt")) {
			message = "Payment is successfully made!";
		}

		if (file.contains("Customer.txt")) {
			message = "Customer is added successfully!";
		}

		int value = 1;

		try {
			File new_File = new File(file);
			FileWriter fileWriter = new FileWriter(new_File, true);
			// FileWriter newFileWriter = new FileWriter("Customer.txt", true);
			// check whether file is empty or not
			if (file.length() == 0) {
				fileWriter.write(data + "\n");
				fileWriter.close();
			}
			// if file is not empty then append the data to the file
			else {
				Scanner myReader = new Scanner(file);
				while (myReader.hasNextLine()) {
					String line = myReader.nextLine();
					// check if the data that the user want to add is already available or not
					// if available then simply skip it set "value" to 0 so that we can get track
					// of duplicate data
					if (line.toLowerCase().contains(data.toLowerCase())) {
						value = 0;
						break;
					}
				}
				myReader.close();

				if (value == 0 && file.contains("MovieDB.txt")) {
					message = "Movie already exists!";
				} else if (value == 0 && file.contains("Customer.txt")) {
					message = "Customer already exists!";
				}
//				else if (value == 0 && file.contains("Ticket.txt")) {
//					message = "Ticket is already booked!";
//				} else if (value == 0 && file.contains("Payment.txt")) {
//					message = "Payment is already made!";
//				} 
				else {
					BufferedWriter br = new BufferedWriter(fileWriter);
					br.write(data + "\n");
					br.close();
				}
			}

		} catch (IOException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}
		return message;
	}

	// this function is used to retrieve the data from the file and store that data
	// in an array list line by line
	public static ArrayList<String> ReadFile(String new_file) throws IOException {

		ArrayList<String> fileContent = new ArrayList<String>();
		File file = new File(new_file);

		try {
//			File file = new File(new_file);
			if (file.length() == 0) {
				fileContent.add("null");
			} else {
				Scanner myReader = new Scanner(file);
				while (myReader.hasNextLine()) {
					String data = myReader.nextLine();
					if (!data.isEmpty()) {
						fileContent.add(data);
					}
				}
				myReader.close();
			}
		} catch (FileNotFoundException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}

		// sorting the array list
		Collections.sort(fileContent);
		return fileContent;
	}

	public static String SearchFile(String value, String new_file) throws IOException {
		ArrayList<String> fileContent = ReadFile(new_file);
		String data = "", message = "", response = "The Ticket-ID: " + value + " does not exist!"; 
		String replyFromServer = "";

		File file = new File(new_file);

		// check whether the file is empty or not
		if (file.length() == 0) {
			data = "Database is empty!";
		} else {
			// traversing the file and check whether the movie name that user want to search
			// exists or not
			for (String element : fileContent) {
				if (new_file.contains("Ticket.txt")) {
					String uID = element.split("\t\t\t")[0];

					if (value.contains("/")) {
						if (value.contains(uID)) {
							data = "Ticket is already booked!";
							break;
						}
					} else if (value.contains("#")) {
						if (value.contains(uID)) {
							message = "Ticket is successfully cancelled!";
							break;
						} else {
							message = "You've not booked a ticket with this user-id...";
						}
					} else if (value.contains("P")) {
						// checks whether the user with "uID" has booked a ticket or not
						if (value.contains(uID)) {
							message = "ID EXISTS!";
							String id = uID;

							// traverse the array list to check whether the payment is already made or not
							for (String line : fileContent) {
								String userID = line.split("\t\t\t")[0];
								String status = line.split("\t\t\t")[4];
//								System.out.println("In server: "+status);
								if (userID.equals(id) && status.equals("Paid")) {
									message = "The payment against this id is already made!";
									break;
								}
							}
							break;
						} else {
//							System.out.println(name + " " + uID);
							message = "ID DOES NOT EXIST!";
						}

					} else if (value.contains("*")) {
						String userID = element.split("\t\t\t")[0];
						if (value.contains(userID)) {
							String[] totalSeats = (element.split("\t\t\t")[2]).split(",");
							// number of seats user has booked
							int count = totalSeats.length;
							// number of seats user has entered
							int user_defined_seats = Integer.parseInt(Character.toString(value.charAt(0)));
							// checks whether the number of seats entered by user is valid or not
							if (user_defined_seats > count || user_defined_seats < count) {
								message = "Enter valid number of seats...";
								break;
							} else {

								String ID = Character.toString(value.charAt(2));
								String updatedLine = "";

								BufferedReader br = new BufferedReader(new FileReader(new_file));
								String line = br.readLine();

								// traversing the file line by line and checks if the line contains the
								// user_ID that is entered by the user, if yes then update the payment
								// status of the user from Unpaid to Paid
								while (line != null) {
									String user_ID = line.split("\t\t\t")[0];
									if (user_ID.equals(ID)) {
										updatedLine = line.replace("Unpaid", "Paid");
										break;
									}
									line = br.readLine();
								}
								br.close();

								// overwriting the file with the new data
								FileWriter newFileWriter = new FileWriter(file);
								BufferedWriter newBR = new BufferedWriter(newFileWriter);

								for (int i = 0; i < fileContent.size(); i++) {
									String id = fileContent.get(i).split("\t\t\t")[0];
									String updatedLineID = updatedLine.split("\t\t\t")[0];
									if (id.equals(updatedLineID) && updatedLine.contains("Paid")) {
										newBR.write(updatedLine + "\n");
									} else {
										newBR.write(fileContent.get(i) + "\n");
									}
								}

								newBR.close();
								newFileWriter.close();
							}
						}
					} else if (uID.equals(value)) {
						String ticketID = element.split("\t\t\t")[1];
						String movie_name = element.split("\t\t\t")[2];
						response = "This ticket is found: " + ticketID + "->" + movie_name;
						break;
					} 
//					else {
//						// this block of code is used to check whether the seat number entered by the
//						// user is already booked or not
//						String seat_num = element.split("\t\t\t")[4];
//						if (seat_num.contains(",")) {
//							String[] seat_numbers = seat_num.split(",");
//							if (seat_numbers.length == 1 && value.contains(seat_numbers[0])) {
//								data = "This seat is reserved!";
//								break;
//							} else {
//								for (int i = 0; i < seat_numbers.length; i++) {
//									if (value.contains(seat_numbers[i])) {
//										data = "This seat is reserved!";
//										break;
//									}
//								}
//							}
//						}
//					}
				} else if (new_file.contains("Customer.txt")) {
					String customerID = element.split("\t\t\t")[0];
					if (!value.equals(customerID)) {
						replyFromServer = "Customer with ID: " + value + " does not exits!";
					} else {
						replyFromServer = "Customer with ID: " + value + " exits!";
						break;
					}
				} else {
					System.out.println("In server: element: " + element);
					String movie_name = element.split("\t\t\t")[1].toLowerCase();
					String ID = element.split("\t\t\t")[0];
					if (value.toLowerCase().equals(movie_name) || value.equals(ID)) {
						data = "Movie is available!";
						break;
					} else {
						data = "Movie not found!";
					}
				}
			}
		}
		
//		System.out.println("In server: " + data + "\n" + message + "\n" + replyFromServer + "\n" + response);
		if (data.isEmpty()) {
			if(!message.isEmpty()) {
				data = message;
			} else if(!response.isEmpty()) {
				data = response;
			} else {
				data = replyFromServer;
			}
		}
		return data;
	}

	// this function is used to delete the specific record from the file
	public static String deleteData(String file, String data) {
		ArrayList<String> lines = new ArrayList<String>();

		String message = "";
		File new_file = new File(file);

		// check whether the file is empty or not
		if (new_file.length() == 0) {
			return "Database is empty!";
		}

		boolean isBooked = false;
		try {
			BufferedReader br = new BufferedReader(new FileReader(new_file));
//			FileWriter fileWriter = new FileWriter(new_file, true);
			String line = br.readLine();

			// traversing the entire file to find the user details who wants
			// to cancel the booking
			while (line != null) {
				// removes every space and gets the first element
				String uID = line.split("\t\t\t")[0];

				// checking whether the uID matches with given id
				if (uID.equals(data)) {
					isBooked = true;
				} else {
					// lines from the file are added to the array list
					// if the above condition is failed
					lines.add(line);
				}
				line = br.readLine();
			}

			// overwriting the Ticket.txt file with the new data
			FileWriter newFileWriter = new FileWriter(new_file);
			BufferedWriter newBR = new BufferedWriter(newFileWriter);
			for (int i = 0; i < lines.size(); i++) {
				newBR.write(lines.get(i) + "\n");
			}

			newBR.close();
			br.close();
			newFileWriter.close();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		if (isBooked && file.contains("Ticket.txt")) {
			message = "Ticket is cancelled successfully!";
		} else if (isBooked && file.contains("Customer.txt")) {
			message = "Customer's data is removed successfully!";
		} else {
			if (file.contains("Ticket.txt")) {
				message = "You've not booked a ticket with this data...!";
			} else {
				message = "Customer data does not exist...!";
			}
		}

		return message;
	}
}
