package cms;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class ClientGUI extends JFrame implements ActionListener{

	// required buttons
	JButton moviesMenu;
	JButton customersMenu;
	JButton ticketsMenu;
	JButton close;

	JButton b1;
	JButton b2;
	JButton b3;
	JButton b4;
	JButton b5;

	JButton submitData;
	JButton backToMenu;

	// required text fields
	JTextField input1;
	JTextField input2;
	JTextField input3;
	JTextField input4;
	JTextField input5;

	// required labels
	JLabel label1;
	JLabel label2;
	JLabel label3;
	JLabel label4;
	JLabel label5;
	
	JFrame movies_menu;
	JFrame customers_menu;
	JFrame tickets_menu;
	
	// MAIN MENU
	public JFrame createMainMenu(String title) {

		moviesMenu = new JButton("MOVIE'S MENU");
		customersMenu = new JButton("CUSTOMER'S MENU");
		ticketsMenu = new JButton("TICKET'S MENU");
		close = new JButton("EXIT");

		// setting the foreground and background color
		moviesMenu.setBackground(new Color(0x0A81AB));
		customersMenu.setBackground(new Color(0x0A81AB));
		ticketsMenu.setBackground(new Color(0x0A81AB));
		close.setBackground(new Color(0x0A81AB));

		// setting the custom fonts
		Font myFont = null;
		try {
			myFont = Font.createFont(Font.TRUETYPE_FONT, new File("E:\\Eclipse\\Socket Programming\\CMS_1\\Fonts\\f1.ttf"))
					.deriveFont(40f);
		} catch (FontFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		GraphicsEnvironment graphics = GraphicsEnvironment.getLocalGraphicsEnvironment();
		graphics.registerFont(myFont);

		// setting the font and its color
		moviesMenu.setFont(myFont);
		moviesMenu.setForeground(new Color(0xFFC947));
		customersMenu.setFont(myFont);
		customersMenu.setForeground(new Color(0xFFC947));
		ticketsMenu.setFont(myFont);
		ticketsMenu.setForeground(new Color(0xFFC947));
		close.setFont(myFont);
		close.setForeground(new Color(0xFFC947));

		// setting the border
		moviesMenu.setBorder(null);
		customersMenu.setBorder(null);
		ticketsMenu.setBorder(null);
		close.setBorder(null);

		moviesMenu.setFocusable(false);
		customersMenu.setFocusable(false);
		ticketsMenu.setFocusable(false);
		close.setFocusable(false);

		// setting the bounds of buttons
		moviesMenu.setBounds(300, 90, 350, 60);
		customersMenu.setBounds(300, 170, 350, 60);
		ticketsMenu.setBounds(300, 250, 350, 60);
		close.setBounds(300, 330, 350, 60);

		// adding the action listeners
		moviesMenu.addActionListener(this);
		customersMenu.addActionListener(this);
		ticketsMenu.addActionListener(this);
		close.addActionListener(this);

		ImageIcon Logo = new ImageIcon("logo.jpg");
		this.setIconImage(Logo.getImage()); // changes the icon of the frame

		ImageIcon bgImage = new ImageIcon(
				new ImageIcon("cinema.jpg").getImage().getScaledInstance(1000, 850, Image.SCALE_DEFAULT));

		// setting the background image
		this.setContentPane(new JLabel(bgImage));

		this.setTitle(title);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setSize(1000, 700);

		this.add(moviesMenu);
		this.add(customersMenu);
		this.add(ticketsMenu);
		this.add(close);

		this.setLayout(null);
		this.setVisible(true);

		// action listeners using lambda expressions
		moviesMenu.addActionListener(e -> {
			this.dispose();
			movies_menu = createNewWindow(1);
			movies_menu.setTitle("MOVIES MENU");
		});

		customersMenu.addActionListener(e -> {
			this.dispose();
			customers_menu = createNewWindow(2);
			customers_menu.setTitle("CUSTOMER'S MENU");
		});

		ticketsMenu.addActionListener(e -> {
			this.dispose();
			tickets_menu = createNewWindow(3);
			tickets_menu.setTitle("TICKET'S MENU");
		});

		close.addActionListener(e -> {
			this.dispose();
		});

		return this;
	}
	

	// creates a new window based on the option chosen by the user from the main menu
	public JFrame createNewWindow(int option) {
		
		Font myFont = null;
		try {
			myFont = Font.createFont(Font.TRUETYPE_FONT, new File("E:\\Eclipse\\Socket Programming\\CMS_1\\Fonts\\f1.ttf"))
					.deriveFont(40f);
		} catch (FontFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		GraphicsEnvironment graphics = GraphicsEnvironment.getLocalGraphicsEnvironment();
		graphics.registerFont(myFont);
		
		JFrame frame = new JFrame();
		ImageIcon Logo = new ImageIcon("logo.jpg");
		frame.setIconImage(Logo.getImage()); 

		if (option == 1) {
			b1 = new JButton("ADD MOVIES");
			b2 = new JButton("VIEW MOVIES");
			b3 = new JButton("SEARCH MOVIES");
			b4 = new JButton("GO BACK");
			ImageIcon bgImage = new ImageIcon(
					new ImageIcon("cinema3_08.jpg").getImage().getScaledInstance(1000, 850, Image.SCALE_DEFAULT));
			frame.setContentPane(new JLabel(bgImage));
			frame = setFrame(frame, b1, b2, b3, b4, 1);
			frame.setVisible(true);

		} else if (option == 2) {
			b1 = new JButton("ADD CUSTOMERS DATA");
			b2 = new JButton("VIEW CUSTOMERS DATA");
			b3 = new JButton("DELETE CUSTOMERS DATA");
			b4 = new JButton("GO BACK");
			ImageIcon bgImage = new ImageIcon(
					new ImageIcon("bg-4.jpg").getImage().getScaledInstance(1000, 800, Image.SCALE_DEFAULT));
			frame.setContentPane(new JLabel(bgImage));
			frame = setFrame(frame, b1, b2, b3, b4, 2);
			frame.setVisible(true);

		} else if (option == 3) {
			b1 = new JButton("BOOK A TICKET");
			b2 = new JButton("SEARCH BOOKED TICKETS");
			b3 = new JButton("MAKE PAYMENT");
			b4 = new JButton("CANCEL BOOKING");
			b5 = new JButton("GO BACK");
			ImageIcon bgImage = new ImageIcon(
					new ImageIcon("tm.jpg").getImage().getScaledInstance(1000, 800, Image.SCALE_DEFAULT));
			frame.setContentPane(new JLabel(bgImage));
			frame = setFrame(frame, b1, b2, b3, b4, 3);

			b5.setFocusable(false);
			b5.addActionListener(this);
			b5.setBorder(null);
			b5.setBounds(280, 490, 450, 60);
			b5.setBackground(new Color(0x053742));
			b5.setFont(myFont);
			b5.setForeground(new Color(0xFFC947));

			frame.add(b5);
			frame.setVisible(true);
		}

		return frame;
	}

	// creates the buttons and adds them to the frame
	public JFrame setFrame(JFrame frame, JButton button1, JButton button2, JButton button3, JButton button4, int option) {
		
		// setting the border
		b1.setBorder(null);
		b2.setBorder(null);
		b3.setBorder(null);
		b4.setBorder(null);

		b1.setFocusable(false);
		b2.setFocusable(false);
		b3.setFocusable(false);
		b4.setFocusable(false);
		
		b1.addActionListener(this);
		b2.addActionListener(this);
		b3.addActionListener(this);
		b4.addActionListener(this);
		
		// setting the foreground and background color
		b1.setBackground(new Color(0x053742));
		b2.setBackground(new Color(0x053742));
		b3.setBackground(new Color(0x053742));
		b4.setBackground(new Color(0x053742));

		// setting the custom fonts
		Font myFont = null;
		try {
			myFont = Font.createFont(Font.TRUETYPE_FONT, new File("E:\\Eclipse\\Socket Programming\\CMS_1\\Fonts\\f1.ttf"))
					.deriveFont(40f);
		} catch (FontFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		GraphicsEnvironment graphics = GraphicsEnvironment.getLocalGraphicsEnvironment();
		graphics.registerFont(myFont);
		
		// setting the font and its color
		b1.setFont(myFont);
		b1.setForeground(new Color(0xFFC947));
		b2.setFont(myFont);
		b2.setForeground(new Color(0xFFC947));
		b3.setFont(myFont);
		b3.setForeground(new Color(0xFFC947));
		b4.setFont(myFont);
		b4.setForeground(new Color(0xFFC947));
		
		if(option == 1) {
			b1.setBounds(550, 20, 350, 60);
			b2.setBounds(550, 100, 350, 60);
			b3.setBounds(550, 180, 350, 60);
			b4.setBounds(550, 260, 350, 60);
			
		} else if(option == 2 || option == 3) {
			b1.setBounds(280, 170, 450, 60);
			b2.setBounds(280, 250, 450, 60);
			b3.setBounds(280, 330, 450, 60);
			b4.setBounds(280, 410, 450, 60);
		}

		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setSize(1000, 820);

		frame.setLayout(null);
		frame.setResizable(false);

		frame.add(b1);
		frame.add(b2);
		frame.add(b3);
		frame.add(b4);

		return frame;
	}
	
	public void dataViewer(ArrayList<String> data, int option) {

		JFrame dataViewer = new JFrame();
		dataViewer.setSize(1000, 700);
		dataViewer.setVisible(true);
	
		String[] customer_table_column_names = { "ID", "FIRST NAME", "LAST NAME" };
		String[] movie_table_column_names = { "ID", "MOVIE NAME", "LANGUAGE", "GENRE" };
		
		DefaultTableModel tableModel = new DefaultTableModel();
		JTable dataTable = new JTable();
		JScrollPane tableScroll = null;
		
		if(option == 1) {
			dataViewer.setTitle("MOVIES DATABASE");
			tableModel.setColumnIdentifiers(movie_table_column_names);
			dataTable.setModel(tableModel);
			// this will resize all the columns automatically
			dataTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
			dataTable.setFillsViewportHeight(true);
			tableScroll = new JScrollPane(dataTable);
			tableScroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
			tableScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
			
			String[] rawData = data.get(0).split(",");
			for (int i = 0; i < rawData.length; i++) {
				StringTokenizer str = new StringTokenizer(rawData[i]);
				String id = str.nextToken();
				String name = str.nextToken();
				String language = str.nextToken();
				String genre = str.nextToken();
				String[] columnValues = { id, name, language, genre };
				tableModel.addRow(columnValues);
			}
			
			dataViewer.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
			JFrame frame = createNewWindow(1);
			
		}
		
		if(option == 2) {
			dataViewer.setTitle("CUSTOMER'S DATABASE");
			tableModel.setColumnIdentifiers(customer_table_column_names);
			dataTable.setModel(tableModel);
			// this will resize all the columns automatically
			dataTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
			dataTable.setFillsViewportHeight(true);
			tableScroll = new JScrollPane(dataTable);
			tableScroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
			tableScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
			
			String[] rawData = data.get(0).split(",");
			for (int i = 0; i < rawData.length; i++) {
				StringTokenizer str = new StringTokenizer(rawData[i]);
				String customer_id = str.nextToken();
				String first_name = str.nextToken();
				String last_name = str.nextToken();
				String[] columnValues = { customer_id, first_name, last_name };
				tableModel.addRow(columnValues);
			}
			
			dataViewer.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
			JFrame frame = createNewWindow(2);
		}

		// setting the custom fonts
		Font myFont = null;
		Font font = null;
		try {
			myFont = Font.createFont(Font.TRUETYPE_FONT, new File("E:\\Eclipse\\Socket Programming\\CMS_1\\Fonts\\f3.ttf"))
					.deriveFont(25f);
			font = Font.createFont(Font.TRUETYPE_FONT, new File("E:\\Eclipse\\Socket Programming\\CMS_1\\Fonts\\f4.ttf"))
					.deriveFont(20f);
		} catch (FontFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		GraphicsEnvironment graphics = GraphicsEnvironment.getLocalGraphicsEnvironment();
		graphics.registerFont(myFont);

		dataViewer.add(tableScroll);
		dataTable.getTableHeader().setFont(myFont);
		dataTable.setFont(font);
		dataTable.getTableHeader().setBackground(new Color(0xC2B8A3));
		dataTable.setBackground(new Color(0xFEF7DC));
		dataTable.setRowHeight(22);

	}

	public JPanel createDialogueBox(String response) throws FontFormatException, IOException {
		Font myFont = null;
		
		JLabel icon;
		JLabel text = new JLabel();
		JPanel dialogueBox = new JPanel();
		
		myFont = Font.createFont(Font.TRUETYPE_FONT, new File("E:\\Eclipse\\Socket Programming\\CMS_1\\Fonts\\f1.ttf"))
				.deriveFont(20f);
		GraphicsEnvironment graphics = GraphicsEnvironment.getLocalGraphicsEnvironment();
		graphics.registerFont(myFont);
		text.setText(response);
		text.setFont(myFont);
		dialogueBox.setLayout(new BorderLayout());
		dialogueBox.add(text, BorderLayout.NORTH);
		
		if(response.contains("not") || response.contains("EMPTY") || response.contains("empty")
				|| response.contains("available")) {
			ImageIcon logo = new ImageIcon(new ImageIcon("robot.png").getImage().getScaledInstance(150, 100, Image.SCALE_DEFAULT));
			icon = new JLabel(logo);			
		} else {
			ImageIcon logo = new ImageIcon(new ImageIcon("a.png").getImage().getScaledInstance(100, 75, Image.SCALE_DEFAULT));
			icon = new JLabel(logo);
		}
		
		dialogueBox.add(icon, BorderLayout.EAST);
		JOptionPane.showMessageDialog(null, dialogueBox, "MESSAGE", JOptionPane.INFORMATION_MESSAGE);
		
		return dialogueBox;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
	}

}
