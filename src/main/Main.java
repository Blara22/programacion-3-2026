package main;

import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;

import javax.swing.JFrame;
import javax.swing.UIManager;

import com.formdev.flatlaf.FlatLightLaf;

import utils.AppFont;
import views.RegistrationView;
import views.LoginWindow;
import views.MainView;

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
		
		LoginWindow ventanita = new LoginWindow();
		//RegistrationView formulario = new RegistrationView();
		//MainView vista = new MainView();
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
