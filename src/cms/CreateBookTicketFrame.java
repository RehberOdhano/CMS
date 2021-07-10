package cms;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class CreateBookTicketFrame extends ClientGUI implements ActionListener {

	// required buttons
	JButton bookTicket;

	// required text fields
	JTextField input1;
	JTextField input2;
	JTextField input3;
	JTextField input4;

	// required labels
	JLabel label1;
	JLabel label2;
	JLabel label3;
	JLabel label4;

	JFrame newFrame;

	public CreateBookTicketFrame() {
		
		newFrame = new JFrame("BOOK A TICKET");
		newFrame.setSize(1000, 700);
		newFrame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		newFrame.setResizable(false);

		label1 = new JLabel("ID");
		label2 = new JLabel("MOVIE NAME");
		label3 = new JLabel("SEAT NUMBER");
		label4 = new JLabel("TIMING");

		input1 = new JTextField();
		input2 = new JTextField();
		input3 = new JTextField();
		input4 = new JTextField();

		label1.setBounds(280, 150, 150, 50);
		label2.setBounds(280, 200, 150, 50);
		label3.setBounds(280, 250, 150, 50);
		label4.setBounds(280, 300, 150, 50);

		input1.setBounds(530, 150, 150, 30);
		input2.setBounds(530, 200, 150, 30);
		input3.setBounds(530, 250, 150, 30);
		input4.setBounds(530, 300, 150, 30);

		// setting the width and height
		input1.setSize(200, 35);
		input2.setSize(200, 35);
		input3.setSize(200, 35);
		input4.setSize(200, 35);

		// setting the custom fonts
		Font myFont = null;
		try {
			myFont = Font
					.createFont(Font.TRUETYPE_FONT, new File("E:\\Eclipse\\Socket Programming\\CMS_1\\Fonts\\f3.ttf"))
					.deriveFont(32f);
		} catch (FontFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		GraphicsEnvironment graphics = GraphicsEnvironment.getLocalGraphicsEnvironment();
		graphics.registerFont(myFont);

		input1.setFont(myFont);
		input2.setFont(myFont);
		input3.setFont(myFont);
		input4.setFont(myFont);

		label1.setFont(myFont);
		label2.setFont(myFont);
		label3.setFont(myFont);
		label4.setFont(myFont);
		label1.setForeground(new Color(0x402218));
		label2.setForeground(new Color(0x402218));
		label3.setForeground(new Color(0x402218));
		label4.setForeground(new Color(0x402218));

		bookTicket = new JButton("BOOK");
		bookTicket.addActionListener(this);
		ImageIcon logo2 = new ImageIcon(
				new ImageIcon("book.png").getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT));
		bookTicket.setIcon(logo2);
		bookTicket.setBounds(750, 550, 150, 50);
		bookTicket.setFont(myFont);
		bookTicket.setForeground(new Color(0xFFC947));
		bookTicket.setBackground(new Color(0x185ADB));

		ImageIcon bgImage = new ImageIcon(
				new ImageIcon("t2.jpg").getImage().getScaledInstance(1000, 700, Image.SCALE_DEFAULT));
		newFrame.setContentPane(new JLabel(bgImage));

		newFrame.add(label1);
		newFrame.add(label2);
		newFrame.add(label3);
		newFrame.add(label4);

		newFrame.add(input1);
		newFrame.add(input2);
		newFrame.add(input3);
		newFrame.add(input4);

		newFrame.add(bookTicket);

		newFrame.setLayout(null);
		newFrame.setVisible(true);
	}
	
	@Override
	public void actionPerformed(ActionEvent event) {
//		if(event.getSource() == goBack) {
//			System.out.println("In client: " + goBack.getText());
//			newFrame.dispose();
//			JFrame back_to_menu = createNewWindow(3);
//		}		
	}
}
