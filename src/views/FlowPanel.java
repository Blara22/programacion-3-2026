package views;

import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class FlowPanel extends JPanel{

	public FlowPanel() {
		setLayout(new FlowLayout(FlowLayout.RIGHT, 20, 20));
		
		JButton buttons[] = new JButton[10];
		
		for(int i = 0; i < 10; i++) {
			 buttons[i] = new JButton(i + "");
			 add(buttons[i]);
		}
		
		JLabel lbl = new JLabel("Hola mundo");
		add(lbl);
		
		JTextField txt = new JTextField("fgjhdfkjgdfg", 30);
		add(txt);
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
}
