package controllers;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JOptionPane;

import views.LoginWindow;
import views.MainWindow;

public class HomeController {

	private MainWindow view;
	
	public HomeController(MainWindow view) {
		
		this.view = view;
		registerListeners();
		
	}
	
	public void registerListeners( ) {
		
		view.mItemExit.addActionListener(e -> handleClose());
		
		view.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				handleClose();
			}
		});
	}
	
	private void handleClose() {
		int option = view.confirmExit();
		System.out.println(option);

		if (option == JOptionPane.YES_OPTION) {
			new LoginController(new LoginWindow().getLoginView());
			view.dispose();
		}
	}
	
}
