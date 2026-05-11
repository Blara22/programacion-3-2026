package controllers;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JOptionPane;

import exceptions.InvalidPasswordException;
import exceptions.InvalidUserException;
import models.User;
import repository.LoginRepository;
import views.LoginView;
import views.MainWindow;
import views.RegistrationWindow;

public class LoginController {

	private LoginView view;
	private LoginRepository repository;

	public LoginController(LoginView view) {
		repository = new LoginRepository();
		this.view = view;
		addListeners();
	}

	private boolean validateCredentials(User user) {

		view.resetErrorMessages();

		boolean valid = true;

		if (user.getEmail().trim().isEmpty()) {
			view.showEmailError("El correo es obligatorio");
			valid = false;
		}

		if (user.getPassword().trim().isEmpty()) {
			view.showPasswordError("La contraseña es obligatoria");
			valid = false;
		}

		return valid;
	}

	private void handleRegistration() {
		new RegistrationController(new RegistrationWindow());
		view.getWindow().dispose();
	}

	private void handleLogin() {
		
		if(!validateCredentials(new User(view.getEmail(), view.getPassword()))){
			return;
		}
		
		User user = repository.login(view.getEmail(), view.getPassword());
		
		if(user == null) {
			view.showPasswordError("Credenciales incorrectas");
			return;
		}
		
		JOptionPane.showMessageDialog(view.getWindow(),  "Se inició la sesión", "Sesión iniciada", JOptionPane.INFORMATION_MESSAGE);
		new HomeController(new MainWindow());
		
		view.getWindow().dispose();
		
	}
	
	private void addListeners() {
				
		KeyAdapter enterListener = new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				
				if(e.getKeyCode() == KeyEvent.VK_ENTER) {
					handleLogin();
				}
			}
		};
		
		view.getPasswordField().addKeyListener(enterListener);
		view.getEmailField().addKeyListener(enterListener);
		
		view.getBtnLogin().addActionListener(e-> handleLogin());
		
		view.getLblRegister().addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				handleRegistration();
			}
		});
	}

}
