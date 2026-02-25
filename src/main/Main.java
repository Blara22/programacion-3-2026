package main;

import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;

import javax.swing.JFrame;
import javax.swing.UIManager;

import com.formdev.flatlaf.FlatLightLaf;

import utils.AppFont;
import views.FormularioRegistro;
import views.LoginWindow;
import views.MainView;

public class Main {

	public static void main(String[] args) {
		
		try {
	        UIManager.setLookAndFeel(
	            UIManager.getSystemLookAndFeelClassName()
	        );
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
		
		//FlatLightLaf.setup();
		
		UIManager.put("Label.font", AppFont.normal());
		UIManager.put("Button.font", AppFont.title());
		UIManager.put("TextField.font", AppFont.small());	
		
		LoginWindow ventanita = new LoginWindow();
		//FormularioRegistro formulario = new FormularioRegistro();
		//MainView vista = new MainView();
		showOnScreen(1, ventanita);
	}
	
	public static void showOnScreen(int screen, JFrame frame ) {
	    GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
	    GraphicsDevice[] gd = ge.getScreenDevices();
	    int width = 0, height = 0;
	    
	    if( screen > -1 && screen < gd.length ) {
	        width = gd[screen].getDefaultConfiguration().getBounds().width;
	        height = gd[screen].getDefaultConfiguration().getBounds().height;
	        frame.setLocation(
	            ((width / 2) - (frame.getSize().width / 2)) + gd[screen].getDefaultConfiguration().getBounds().x, 
	            ((height / 2) - (frame.getSize().height / 2)) + gd[screen].getDefaultConfiguration().getBounds().y
	        );
	    } else {
	        throw new RuntimeException( "No se encontró la pantalla" );
	    }
	}

}
