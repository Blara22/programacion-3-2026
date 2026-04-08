package main;

import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;

import javax.swing.JFrame;
import javax.swing.UIManager;

import com.formdev.flatlaf.FlatLightLaf;

import controllers.LoginController;
import utils.AppFont;
import views.RegistrationWindow;
import views.LoginWindow;
import views.MainWindow;

public class Main {

	public static void main(String[] args) {
		
		/*try {
	        UIManager.setLookAndFeel(
	            UIManager.getSystemLookAndFeelClassName()
	        );
	    } catch (Exception e) {
	        e.printStackTrace();
	    }*/
		
		//FlatLightLaf.setup();
		
		/*
		 * Estas línean cambian de forma global la fuente para labels, buttons y textfields
		 * Si quitan el comentario verán la diferencia.
		 */
		
		//UIManager.put("Label.font", AppFont.normal());
		//UIManager.put("Button.font", AppFont.title());
		//UIManager.put("TextField.font", AppFont.small());	
		
		//MainWindow ventanita = new MainWindow();
		LoginWindow ventanita = new LoginWindow();
		new LoginController(ventanita.getLoginView());
		
		//RegistrationWindow ventanita = new RegistrationWindow();
		showOnScreen(1, ventanita);
	}
	
	public static void showOnScreen(int screen, JFrame frame) {
	    GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
	    GraphicsDevice[] gd = ge.getScreenDevices();

	    if (screen > -1 && screen < gd.length) {

	        Rectangle bounds = gd[screen].getDefaultConfiguration().getBounds();

	        int x = bounds.x + (bounds.width - frame.getWidth()) / 2;
	        int y = bounds.y + (bounds.height - frame.getHeight()) / 2;

	        frame.setLocation(x, y);

	    } else {
	        throw new RuntimeException("No se encontró la pantalla");
	    }
	}

}
