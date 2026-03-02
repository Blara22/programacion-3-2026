package views;

import java.awt.Cursor;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

import views.examples.FlowPanel;

public class LoginWindow extends JFrame { 
	
	public LoginWindow() {
		
		setSize(400, 400);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//setLocation(100,100);
		//setBounds(100,100,500,500);
		setResizable(true);
		setTitle("Mi Aplicación V2.0");
		setLocationRelativeTo(null);
		
		Toolkit tk = Toolkit.getDefaultToolkit();
		Image icono = tk.getImage("src/img/icono.png");
		setIconImage(icono);
		
		ImageIcon iconoCursor = new ImageIcon("src/img/icono.png");
		Cursor miCursor = tk.createCustomCursor(iconoCursor.getImage(), 
				new Point(0,0), "Mi Cursor");
		//setCursor(miCursor);
		
		//FlowPanel panelito = new FlowPanel();
		LoginView panelito = new LoginView();
		add(panelito);
		
		setVisible(true);
		//pack();
		
		//validate();
		//repaint();
	}
	
}









