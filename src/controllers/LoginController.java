package controllers;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JOptionPane;

import exceptions.InvalidPasswordException;
import exceptions.InvalidUserException;
import models.User;
import views.LoginView;
import views.MainWindow;
import views.RegistrationWindow;

public class LoginController {

	private LoginView view;

	public LoginController(LoginView view) {
		this.view = view;
		addListeners();
	}

	private boolean validateCredentials(User user)
			throws InvalidUserException, InvalidPasswordException {

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
		;

		if (!user.getEmail().trim().isEmpty() && !user.getEmail().trim().equals("b.lara@uabcs.mx")) {
			throw new InvalidUserException("El correo no coincide.");
		}

		if (!user.getPassword().trim().isEmpty() && !user.getPassword().trim().equals("1234")) {
			throw new InvalidPasswordException("La contraseña no coincide");
		}

		return valid;
	}

	private void handleRegistration() {
		new RegistrationController(new RegistrationWindow());
		view.getWindow().dispose();
	}

	private void handleLogin() {
		
		User user = new User(
			view.getEmail(),
			view.getPassword()
		); 
		
		System.out.println(user.getName());
		
		try {
			if (validateCredentials(user)) {
				JOptionPane.showMessageDialog(view.getWindow(), "Se inició la sesión", "Sesión iniciada",
						JOptionPane.INFORMATION_MESSAGE);

				new HomeController(new MainWindow());
				view.getWindow().dispose();
			}
		} catch (InvalidUserException | InvalidPasswordException ex) {
			view.showPasswordError("Credenciales Incorrectas");
		}
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
