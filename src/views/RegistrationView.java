package views;

import java.awt.BorderLayout;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import utils.AppFont;

public class RegistrationView extends JFrame{
	
	public RegistrationView() {
		
		setSize(300, 500);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(true);
		setTitle("Registro");
		setLocationRelativeTo(null);
		
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Image iconImage = toolkit.getImage("src/img/icono.png");
		setIconImage(iconImage);
		
		initializeComponents();
		
		setVisible(true);		
	}
	
	public void initializeComponents() {
		
		JLabel lblTitle = new JLabel("Registro");
		lblTitle.setFont(AppFont.title());
		add(lblTitle, BorderLayout.NORTH);
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		
		JPanel componentsPanel = new JPanel();
		componentsPanel.setLayout(new BoxLayout(componentsPanel, BoxLayout.Y_AXIS));
		componentsPanel.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));
		
		for(int i = 0; i < 20; i++) {
			JLabel label = new JLabel("Campo " + i);
			componentsPanel.add(label);
			JTextField textField = new JTextField(20);
			componentsPanel.add(textField);
		}
		JScrollPane scrollPane = new JScrollPane(componentsPanel);
		scrollPane.setHorizontalScrollBar(null);
		
		add(scrollPane);
	}
}