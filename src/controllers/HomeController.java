package controllers;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import views.MainWindow;

public class HomeController {

	private MainWindow view;
	private UserController userController;
	
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
		
		view.btnUsers.addActionListener(e -> {
			showUsers();
		});
		
		view.btnHome.addActionListener(e -> {
			view.showView(MainWindow.HOME);
			updateMenuState(MainWindow.HOME);
		});
		
	}
	
	private void showUsers() {
		if(userController == null) {
			userController = new UserController(view.usersPanel);
		}
			
		userController.loadUsers();
		
		view.showView(MainWindow.USERS);
		updateMenuState(MainWindow.USERS);
		
	}
	
	private void handleClose() {
		/*int option = view.confirmExit();
		System.out.println(option);

		if (option == JOptionPane.YES_OPTION) {
			new LoginController(new LoginWindow().getLoginView());*/
			view.dispose();
		//}
	}
	
	private void updateMenuState(String viewName) {
		view.btnUsers.setEnabled(!viewName.equals(MainWindow.USERS));
		view.btnHome.setEnabled(!viewName.equals(MainWindow.HOME));
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
