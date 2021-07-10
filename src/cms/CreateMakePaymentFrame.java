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

public class CreateMakePaymentFrame extends ClientGUI implements ActionListener{

	// required buttons
	JButton pay;

	// required text fields
	JTextField input1;
	JTextField input2;

	// required labels
	JLabel label1;
	JLabel label2;

	JFrame newFrame;

	public CreateMakePaymentFrame() {

		newFrame = new JFrame("MAKE PAYMENT");
		newFrame.setSize(1000, 700);
		newFrame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		newFrame.setResizable(false);

		label1 = new JLabel("TICKET ID");
		label2 = new JLabel("SEAT NUMBER");

		input1 = new JTextField();
		input2 = new JTextField();

		label1.setBounds(300, 200, 150, 50);
		label2.setBounds(300, 270, 150, 50);

		input1.setBounds(500, 200, 150, 30);
		input2.setBounds(500, 270, 150, 30);

		// setting the width and height
		input1.setSize(200, 35);
		input2.setSize(200, 35);

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

		label1.setFont(myFont);
		label2.setFont(myFont);

		label1.setForeground(new Color(0xFFC947));
		label2.setForeground(new Color(0xFFC947));

		label1.setForeground(new Color(0x402218));
		label2.setForeground(new Color(0x402218));

		pay = new JButton("PAY");
		pay.addActionListener(this);
		ImageIcon logo2 = new ImageIcon(
				new ImageIcon("money.png").getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT));
		pay.setIcon(logo2);
		pay.setBounds(750, 550, 150, 50);
		pay.setFont(myFont);
		pay.setForeground(new Color(0xFFC947));
		pay.setBackground(new Color(0x0A81AB));

		ImageIcon bgImage = new ImageIcon(
				new ImageIcon("p3.jpg").getImage().getScaledInstance(1000, 700, Image.SCALE_DEFAULT));
		newFrame.setContentPane(new JLabel(bgImage));

		newFrame.add(label1);
		newFrame.add(label2);
		newFrame.add(input1);
		newFrame.add(input2);

		newFrame.add(pay);

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
