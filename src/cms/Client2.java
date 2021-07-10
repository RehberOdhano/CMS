package cms;

import java.awt.FontFormatException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Client2 extends ClientGUI implements ActionListener {

	// creating an instance of the Random class
	Random random = new Random();
	// any random number between 10 and 100 is assigned to the cost
	int lower_bound = 10;
	int upper_bound = 100;

	int cost = 0;
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
	JFrame main_menu;
	
	// client side
	public Client2() throws Exception {

		// receiving the message from the server and storing it in a string
		String message = in_socket.readLine();
		
		main_menu = createMainMenu(message);

	}

	@Override
	public void actionPerformed(ActionEvent event) {
		
		if (event.getSource() == b1 && b1.getText().contains("ADD MOVIES")) {
			out_socket.println("1.1");
			try {
				System.out.println("In client: " + in_socket.readLine());
			} catch (IOException e2) {
				e2.printStackTrace();
			}
			
			//movies_menu.dispose();
			CreateAddMoviesFrame frame_1 = new CreateAddMoviesFrame();
			
			frame_1.add.addActionListener(e -> {
				System.out.println("In client: " + frame_1.add.getText());
				String ID = frame_1.input1.getText();
				String movie_name = frame_1.input2.getText();
				String language = frame_1.input3.getText();
				String genre = frame_1.input4.getText();

				Movie movie = new Movie(ID, movie_name, language, genre);
				try {
					objectOutputStream.writeObject(movie);
					String response = in_socket.readLine();
					System.out.println(response);
					JPanel panel = createDialogueBox(response);
					JOptionPane.showMessageDialog(null, panel, "MESSAGE", JOptionPane.INFORMATION_MESSAGE);
				} catch (IOException | FontFormatException e1) {
					e1.printStackTrace();
				}	
			});	
		}
		else if (event.getSource() == b2 && b2.getText().contains("VIEW MOVIES")) {
			out_socket.println("1.2");

			try {
				System.out.println("In client: " + in_socket.readLine());
				out_socket.println("");
				
				//movies_menu.dispose();

				// getting the file data from the server with the help of an array list
				ArrayList<String> fileContent = new ArrayList<String>(Arrays.asList(in_socket.readLine()));

				String[] rawData = fileContent.get(0).split(",");
				
				if(!rawData[0].contains("null")) {
					dataViewer(fileContent, 1);
				} else {
					JPanel panel = createDialogueBox("DATABASE IS EMPTY!");
					JOptionPane.showMessageDialog(null, panel, "MESSAGE", JOptionPane.INFORMATION_MESSAGE);
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		else if (event.getSource() == b3 && b3.getText().contains("SEARCH")) {
			out_socket.println("1.3");
			
			try {
				System.out.println("In client: " + in_socket.readLine());
				//movies_menu.dispose();
				CreateSearchMoviesFrame frame_2 = new CreateSearchMoviesFrame();
				
				frame_2.search.addActionListener(e -> {
					System.out.println("In client: " + frame_2.search.getText());
					String movie_name = frame_2.input1.getText();
					out_socket.println(movie_name);
					try {
						String response = in_socket.readLine();
						JPanel panel = createDialogueBox(response);
						JOptionPane.showMessageDialog(null, panel, "MESSAGE", JOptionPane.INFORMATION_MESSAGE);
					} catch (IOException | FontFormatException e1) {
						e1.printStackTrace();
					}
				});
			} catch (IOException e1) {
				e1.printStackTrace();
			}

		} else if (event.getSource() == b1 && b1.getText().contains("BOOK")) {
			out_socket.println("2.1");
			try {
				System.out.println("In client: " + in_socket.readLine());
				
				//tickets_menu.dispose();
				CreateBookTicketFrame frame_3 = new CreateBookTicketFrame();
				
				frame_3.bookTicket.addActionListener(e -> {
					System.out.println("In client: " + frame_3.bookTicket.getText());
					String ID = frame_3.input1.getText();
					String movie_name = frame_3.input2.getText();
					String seat_number = frame_3.input3.getText();
					String timing = frame_3.input4.getText();

					Ticket ticket = new Ticket(ID, movie_name, timing, seat_number, timing);

					try {
						objectOutputStream.writeObject(ticket);
						String response = in_socket.readLine();
						JOptionPane.showMessageDialog(null, response, "MESSAGE", JOptionPane.INFORMATION_MESSAGE);
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				});
			} catch (IOException e) {
				e.printStackTrace();
			}

		} else if (event.getSource() == b2 && b2.getText().contains("BOOKED")) {
			out_socket.println("2.2");
			try {
				System.out.println("In client: " + in_socket.readLine());

				CreateSearchAndCancelTickets frame_4 = new CreateSearchAndCancelTickets(1);
				//tickets_menu.dispose();
				frame_4.search.addActionListener(e -> {
					String ID = frame_4.input1.getText();
					out_socket.println(ID);
					try {
						String response = in_socket.readLine();
						JPanel panel = createDialogueBox(response);
						JOptionPane.showMessageDialog(null, panel, "MESSAGE", JOptionPane.INFORMATION_MESSAGE);
					} catch (IOException | FontFormatException e1) {
						e1.printStackTrace();
					}
				});
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else if (event.getSource() == b3 && b3.getText().contains("PAYMENT")) {
			out_socket.println("2.3");
			try {
				System.out.println("In client: " + in_socket.readLine());
				
				//tickets_menu.dispose();
				CreateMakePaymentFrame frame_5 = new CreateMakePaymentFrame();
				
				frame_5.pay.addActionListener(e -> {
					String ID = frame_5.input1.getText();
					String seatNumbers = frame_5.input2.getText();
					int num_of_seats = seatNumbers.split(",").length;

					cost = (random.nextInt(upper_bound - lower_bound) + lower_bound) * num_of_seats;
					Payment payment = new Payment(ID, seatNumbers, cost);

					try {
						objectOutputStream.writeObject(payment);
						String response = in_socket.readLine();
						JPanel panel = createDialogueBox(response);
						JOptionPane.showMessageDialog(null, panel, "MESSAGE", JOptionPane.INFORMATION_MESSAGE);
					} catch (IOException | FontFormatException e1) {
						e1.printStackTrace();
					}
				});
			} catch (IOException e) {
				e.printStackTrace();
			}

		} else if (event.getSource() == b4 && b4.getText().contains("CANCEL")) {
			out_socket.println("2.4");
			try {
				System.out.println("In client: " + in_socket.readLine());
				
				CreateSearchAndCancelTickets newframe = new CreateSearchAndCancelTickets(2);
				//tickets_menu.dispose();
				newframe.cancel.addActionListener(e -> {
					String ID = newframe.input1.getText();
					out_socket.println(ID);
					try {
						String response = in_socket.readLine();
						JPanel panel = createDialogueBox(response);
						JOptionPane.showMessageDialog(null, panel, "MESSAGE", JOptionPane.INFORMATION_MESSAGE);
					} catch (IOException | FontFormatException e1) {
						e1.printStackTrace();
					}
				});
			} catch (IOException e) {
				e.printStackTrace();
			}

		} else if (event.getSource() == b1 && b1.getText().contains("ADD CUSTOMERS")) {
			out_socket.println("3.1");
			try {
				System.out.println("In client: " + in_socket.readLine());
				
				//customers_menu.dispose();
				CreateAddCustomersDataFrame frame_6 = new CreateAddCustomersDataFrame();

				frame_6.add.addActionListener(e -> {
					String ID = frame_6.input1.getText();
					String firstName = frame_6.input2.getText();
					String lastName = frame_6.input3.getText();

					Customer customer = new Customer(ID, firstName, lastName);

					try {
						objectOutputStream.writeObject(customer);
						String response = in_socket.readLine();
						JPanel panel = createDialogueBox(response);
						JOptionPane.showMessageDialog(null, panel, "MESSAGE", JOptionPane.INFORMATION_MESSAGE);
					} catch (IOException | FontFormatException e1) {
						e1.printStackTrace();
					}
				});
			} catch (IOException e) {
				e.printStackTrace();
			}

		} else if (event.getSource() == b2 && b2.getText().contains("VIEW CUSTOMERS")) {
			out_socket.println("3.2");

			try {
				System.out.println("In client: " + in_socket.readLine());

				out_socket.println("");
				
				//customers_menu.dispose();

				// getting the file data from the server with the help of an array list
				ArrayList<String> fileContent = new ArrayList<String>(Arrays.asList(in_socket.readLine()));

				String[] rawData = fileContent.get(0).split(",");
				
				if(!rawData[0].contains("null")) {
					dataViewer(fileContent, 2);
				} else {
					JPanel panel = createDialogueBox("DATABASE IS EMPTY!");
					JOptionPane.showMessageDialog(null, panel, "MESSAGE", JOptionPane.INFORMATION_MESSAGE);
				}

			} catch (Exception e) {
				e.printStackTrace();
			}

		} else if (event.getSource() == b3 && b3.getText().contains("DELETE CUSTOMERS")) {
			out_socket.println("3.3");

			try {
				System.out.println("In client: " + in_socket.readLine());
				//customers_menu.dispose();
				CreateDeleteCustomersFrame frame_7 = new CreateDeleteCustomersFrame();

				frame_7.delete.addActionListener(e -> {
					String ID = frame_7.input1.getText();
					out_socket.println(ID);

					try {
						String response = in_socket.readLine();
						JPanel panel = createDialogueBox(response);
						JOptionPane.showMessageDialog(null, panel, "MESSAGE", JOptionPane.INFORMATION_MESSAGE);
					} catch (IOException | FontFormatException e1) {
						e1.printStackTrace();
					}
				});
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		else {
			
			if (event.getSource() == close && close.getText().contains("EXIT")) {
				close.addActionListener(e -> {
					this.dispose();
					try {
						socket.close();
						System.out.println("Client Socket is CLOSED!");
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				});				
			} else if((event.getSource() == b4)) {
				System.out.println(b4.getText());
				JFrame new_frame = createMainMenu("WELCOME TO THE CINEMA MANAGEMENT SYSTEM");
			} else if((event.getSource() == b5)) {
				tickets_menu.dispose();
				JFrame new_frame = createMainMenu("WELCOME TO THE CINEMA MANAGEMENT SYSTEM");
			}
		}
	}
	
	public String verifyData(String data, String file) throws IOException {
		String response = FileDBHandling.SearchFile(data, file);
		return response;
	}

	public static void main(String[] args) {

		// taking care of the exception
		try {
			new Client2();
		} catch (Exception e) {
			// displaying the exception
			e.printStackTrace();
		}
	}
}
