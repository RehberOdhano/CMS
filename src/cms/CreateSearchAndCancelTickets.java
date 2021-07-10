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

public class CreateSearchAndCancelTickets extends ClientGUI implements ActionListener{

	// required buttons
	JButton search;
	JButton cancel;

	// required text fields
	JTextField input1;

	// required labels
	JLabel label1;

	JFrame frame;

	public CreateSearchAndCancelTickets(int option) {

		// setting the custom fonts
		Font myFont = null;
		try {
			myFont = Font
					.createFont(Font.TRUETYPE_FONT, new File("E:\\Eclipse\\Socket Programming\\CMS_1\\Fonts\\f3.ttf"))
					.deriveFont(45f);
		} catch (FontFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		GraphicsEnvironment graphics = GraphicsEnvironment.getLocalGraphicsEnvironment();
		graphics.registerFont(myFont);

		input1 = new JTextField();
		input1.setBounds(500, 280, 150, 30);
		input1.setSize(200, 40);
		input1.setFont(myFont);

		label1 = new JLabel("TICKET ID");
		label1.setBounds(330, 280, 150, 50);
		label1.setFont(myFont);
		label1.setForeground(new Color(0xFFC947));
		
		if(option == 1) {
			frame = new JFrame("SEARCH TICKETS HERE...");
			frame.setSize(1000, 700);
			frame.setResizable(false);
			frame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
			search = new JButton("SEARCH");
			search.addActionListener(this);
			ImageIcon logo2 = new ImageIcon(
					new ImageIcon("sl.png").getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT));
			search.setIcon(logo2);
			search.setBounds(680, 495, 190, 50);
			search.setFont(myFont);
			search.setForeground(new Color(0xFFC947));
			search.setBackground(new Color(0x0A81AB));
			ImageIcon bgImage = new ImageIcon(
					new ImageIcon("s1.jpg").getImage().getScaledInstance(1000, 700, Image.SCALE_DEFAULT));
			frame.setContentPane(new JLabel(bgImage));
			frame.add(search);
		}
		
		if(option == 2) {
			frame = new JFrame("CANCEL TICKET");
			frame.setSize(1000, 700);
			frame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
			frame.setResizable(false);
			cancel = new JButton("CANCEL");
			cancel.addActionListener(this);
			ImageIcon logo2 = new ImageIcon(
					new ImageIcon("d1.png").getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT));
			cancel.setIcon(logo2);
			cancel.setBounds(680, 495, 190, 50);
			cancel.setFont(myFont);
			cancel.setForeground(new Color(0xFFC947));
			cancel.setBackground(new Color(0x0A81AB));
			ImageIcon bgImage = new ImageIcon(
					new ImageIcon("bg-6.jpg").getImage().getScaledInstance(1000, 700, Image.SCALE_DEFAULT));
			frame.setContentPane(new JLabel(bgImage));
			frame.add(cancel);
		}

		frame.add(label1);
		frame.add(input1);

		frame.setLayout(null);
		frame.setVisible(true);
	}
	
	@Override
	public void actionPerformed(ActionEvent event) {
//		if(event.getSource() == goBack) {
//			System.out.println("In client: " + goBack.getText());
//			frame.dispose();
//			JFrame back_to_menu = createNewWindow(3);
//		}		
	}
}
