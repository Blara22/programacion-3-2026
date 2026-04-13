package views;

import java.awt.Cursor;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class LoginWindow extends JFrame { 
	
	private LoginView loginView;
	
	public LoginWindow() {
		
		setSize(400, 400);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(true);
		setTitle("Mi Aplicación V2.0");
		setLocationRelativeTo(null);
		
		Toolkit tk = Toolkit.getDefaultToolkit();
		Image icon = tk.getImage("src/assets/img/icono.png");
		setIconImage(icon);
		
		/*ImageIcon cursorIcon = new ImageIcon("src/img/icono.png");
		Cursor myCursor = tk.createCustomCursor(cursorIcon.getImage(), 
				new Point(0,0), "Mi Cursor");
		setCursor(miCursor);*/
		
		loginView = new LoginView(this);
		add(loginView);
		
		setVisible(true);
	}
	
	public LoginView getLoginView() {
		return loginView;
	}
	
}









