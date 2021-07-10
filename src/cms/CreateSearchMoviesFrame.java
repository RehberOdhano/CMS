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

public class CreateSearchMoviesFrame extends ClientGUI implements ActionListener {

	// required buttons
	JButton search;

	// required text fields
	JTextField input1;

	// required labels
	JLabel label1;
	
	// required frames
	JFrame frame;

	public CreateSearchMoviesFrame() {
		
		frame = new JFrame("SEARCH MOVIES HERE...");
		frame.setSize(1000, 700);
		frame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		frame.setResizable(false);
		
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

		label1 = new JLabel("MOVIE");
		label1.setBounds(350, 275, 150, 50);
		label1.setFont(myFont);
		label1.setForeground(new Color(0xFFC947));

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

		frame.add(label1);
		frame.add(input1);
		frame.add(search);

		frame.setLayout(null);
		frame.setVisible(true);
	}
	
	@Override
	public void actionPerformed(ActionEvent event) {
//		if(event.getSource() == goBack) {
//			System.out.println("In client: " + goBack.getText());
//			frame.dispose();
//			JFrame back_to_menu = createNewWindow(1);
//		}		
	}
}
