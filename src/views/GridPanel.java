package views;

import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JPanel;

public class GridPanel extends JPanel {

	public GridPanel() {
		setLayout(new GridLayout(3,3,10,10));
		
		for(int i = 0; i < 10; i++) {
			JButton b = new JButton(i + "");
			add(b);
		}
		
		
		
		
		
		
		
		
		
		
		
		
	}
}
