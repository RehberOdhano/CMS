package cms;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class DialogueBox {
	
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
		
		if(!response.contains("not") || !response.contains("empty")) {
			ImageIcon logo = new ImageIcon(new ImageIcon("a.png").getImage().getScaledInstance(100, 75, Image.SCALE_DEFAULT));
			icon = new JLabel(logo);			
		} else {
			ImageIcon logo = new ImageIcon(new ImageIcon("error.png").getImage().getScaledInstance(100, 75, Image.SCALE_DEFAULT));
			icon = new JLabel(logo);
		}
		
		dialogueBox.add(icon, BorderLayout.EAST);
		JOptionPane.showMessageDialog(null, dialogueBox, "MESSAGE", JOptionPane.INFORMATION_MESSAGE);
		
		return dialogueBox;
	}
}
