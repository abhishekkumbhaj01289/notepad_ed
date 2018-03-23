package note;

import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class Help extends JFrame{
    // labels
	JLabel label;
	public Help(){
		setVisible(true);
		setSize(500,200);
		setLayout(null);  // abs lay
		// setting color using getcontenpane. setback
		getContentPane().setBackground(Color.YELLOW);
		setDefaultCloseOperation(EXIT_ON_CLOSE); 
		this.setLocation(160,160);  // location of frame
		String text="We are always here to help You";
		label=new JLabel(text);
		label.setBounds(10, 10, 250,45);
		add(label);
		
	}
}
