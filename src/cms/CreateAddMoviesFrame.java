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

public class CreateAddMoviesFrame extends ClientGUI implements ActionListener{

	// required buttons
	JButton add;

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
	
	public CreateAddMoviesFrame() {
		
		newFrame = new JFrame("ADD MOVIES TO THE DATABASE");
		newFrame.setSize(1000, 700);
		newFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		newFrame.setResizable(false);

		label1 = new JLabel("ID");
		label2 = new JLabel("MOVIE");
		label3 = new JLabel("LANGUAGE");
		label4 = new JLabel("GENRE");

		input1 = new JTextField();
		input2 = new JTextField();
		input3 = new JTextField();
		input4 = new JTextField();

		label1.setBounds(270, 150, 150, 50);
		label2.setBounds(270, 200, 150, 50);
		label3.setBounds(270, 250, 150, 50);
		label4.setBounds(270, 300, 150, 50);

		input1.setBounds(470, 150, 150, 30);
		input2.setBounds(470, 200, 150, 30);
		input3.setBounds(470, 250, 150, 30);
		input4.setBounds(470, 300, 150, 30);

		// setting the width and height
		input1.setSize(200, 35);
		input2.setSize(200, 35);
		input3.setSize(200, 35);
		input4.setSize(200, 35);

		// setting the custom fonts
		Font myFont = null;
		try {
			myFont = Font.createFont(Font.TRUETYPE_FONT, new File("E:\\Eclipse\\Socket Programming\\CMS_1\\Fonts\\f3.ttf"))
					.deriveFont(35f);
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
		label1.setForeground(new Color(0xFFC947));
		label2.setForeground(new Color(0xFFC947));
		label3.setForeground(new Color(0xFFC947));
		label4.setForeground(new Color(0xFFC947));
		
		add = new JButton("ADD");
		add.addActionListener(this);
		
		ImageIcon logo2 = new ImageIcon(
				new ImageIcon("add.png").getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT));
		add.setIcon(logo2);
		add.setBounds(750, 550, 150, 50);
		add.setFont(myFont);
		add.setForeground(new Color(0xFFC947));
		add.setBackground(new Color(0x0A81AB));

		ImageIcon bgImage = new ImageIcon(
				new ImageIcon("bg-1.jpg").getImage().getScaledInstance(1000, 700, Image.SCALE_DEFAULT));
		newFrame.setContentPane(new JLabel(bgImage));

		newFrame.add(label1);
		newFrame.add(label2);
		newFrame.add(label3);
		newFrame.add(label4);

		newFrame.add(input1);
		newFrame.add(input2);
		newFrame.add(input3);
		newFrame.add(input4);

		newFrame.add(add);

		newFrame.setLayout(null);
		newFrame.setVisible(true);
	
	}

	@Override
	public void actionPerformed(ActionEvent event) {
//		if(event.getSource() == goBack) {
//			System.out.println("Hello");
//			System.out.println("In client: " + goBack.getText());
//			newFrame.dispose();
//			JFrame back_to_menu = createNewWindow(1);
//		}		
	}
}
