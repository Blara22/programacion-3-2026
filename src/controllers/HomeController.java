package controllers;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.util.List;

import javax.swing.JOptionPane;

import models.User;
import repository.UserRepository;
import tablemodels.UserTableModel;
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
		
		view.btnUsers.addActionListener(e -> {
			showUsers();
		});
		
		view.btnHome.addActionListener(e -> view.showView(MainWindow.HOME));
		
	}
	
	private void showUsers() {
		
		UserController controller = new UserController(view.usersPanel);
		
		UserRepository repository = new UserRepository();
		
		try {
			List<User> users = repository.getUsers();
			
			UserTableModel model = new UserTableModel(users);
			
			view.usersPanel.setTableModel(model);
			
			view.showView(MainWindow.USERS);
			
		}catch (IOException ex) {
			JOptionPane.showMessageDialog(view, ex.getMessage());
		}
		
	}
	
	private void handleClose() {
		/*int option = view.confirmExit();
		System.out.println(option);

		if (option == JOptionPane.YES_OPTION) {
			new LoginController(new LoginWindow().getLoginView());*/
			view.dispose();
		//}
	}
	
}
