package views;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import views.components.TextPrompt;

public class LoginView extends JPanel{
	
	Font font;
	Image backgroundImage;
	JTextField emailField;
	JPasswordField passwordField;
	JLabel lblEmailRequired;
	JLabel lblPasswordRequired;
	
	
	public LoginView() {
		
		font = new Font("Arial", Font.PLAIN, 14);
		setLayout(null);
		
		loadImage();
		initializeComponents();
	}
	
	private void initializeComponents() {
		createButtons();
		createLogo();
		createForm();
	}
	
	private void createButtons() {
		
		JButton loginButton = new JButton("Login");
		loginButton.setBounds(250,320,100,30);
		loginButton.setToolTipText("Haz click aquí");
		loginButton.setFont(font);
		add(loginButton);
		
		/*loginButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				login();
			}
			
		});*/
		
		loginButton.addActionListener(e -> handleLogin());
	}
	
	private void createLogo() {
		JLabel lblLogo = new JLabel();
		lblLogo.setBounds(145, 50, 100, 100);
		lblLogo.setIcon(loadIcon("../img/icono.png", 100, 100));
		add(lblLogo);
	}
	
	private void createForm() {
		JLabel lblGreeting = new JLabel("Bienvenido!");
		lblGreeting.setFont(font);
		lblGreeting.setBounds(10,0,200,40);
		add(lblGreeting);
		
		int labelX = 10, positionY = 170, textX = 150;
		
		JLabel lblEmail = new JLabel("Email: ");
		lblEmail.setFont(font);
		lblEmail.setBounds(labelX,positionY,200,40);
		add(lblEmail);
		
		emailField = new JTextField();
		new TextPrompt("Ingresa tu usuario", emailField);
		emailField.setFont(font);
		emailField.setBounds(textX,positionY,200,40);
		add(emailField);
		
		lblEmailRequired = new JLabel("El email es requerido.");
		lblEmailRequired.setBounds(textX, positionY+35, 200, 30);
		lblEmailRequired.setFont(new Font("Arial", Font.BOLD, 10));
		lblEmailRequired.setForeground(Color.RED);
		lblEmailRequired.setVisible(false);
		add(lblEmailRequired);
		
		positionY += 70;
		
		JLabel lblPasswordLabel = new JLabel("Contraseña: ");
		lblPasswordLabel.setFont(font);
		lblPasswordLabel.setBounds(labelX,positionY,200,40);
		add(lblPasswordLabel);
		
		passwordField = new JPasswordField();
		new TextPrompt("Ingresa tu contraseña", passwordField);
		passwordField.setFont(font);
		passwordField.setBounds(textX,positionY,200,40);
		add(passwordField);
		
		lblPasswordRequired = new JLabel("");
		lblPasswordRequired.setBounds(textX, positionY+35, 200, 30);
		lblPasswordRequired.setFont(new Font("Arial", Font.BOLD, 10));
		lblPasswordRequired.setForeground(Color.RED);
		add(lblPasswordRequired);
	}
	
	private ImageIcon loadIcon(String path, int w, int h) {

		try {
			Image icon = ImageIO.read(getClass().getResource(path));
			icon = icon.getScaledInstance(w, h, Image.SCALE_SMOOTH);
			return new ImageIcon(icon);
		}catch(Exception ex) {
			System.out.println("No está la imagen del ícono");
		}
		
		return null;
	}
	
	public void paintComponent(Graphics g) {
		
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		
		g2.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), null);
				
	}
	
	/*
	 * En el ejemplo anterior la imagen se cargaba dentro del paintComponent, esto es una mala prática
	 * ya que cada que sea actualiza la ventana se vuelve a cargar la imagen. Es mejor tenerla como atributo
	 * y que se cargue una sola vez en el constructor.
	 */
	public void loadImage() {
		try {
			backgroundImage = ImageIO.read(new File("src/img/fondo.jpg"));
		} catch (IOException ex) {
			System.out.println("La imagen no existe");
		}
	}
	
	private void handleLogin() {
		
		if(validateCredentials(emailField.getText(), String.valueOf(passwordField.getPassword()))) {
			JOptionPane.showMessageDialog(
				this,
				"Se inició la sesión", 
				"Sesión iniciada", 
				JOptionPane.INFORMATION_MESSAGE
			);
		}
	}
	
	private void showEmailError(String message) {
		lblEmailRequired.setText(message);
		lblEmailRequired.setVisible(true);
	}
	
	private void showPasswordError(String message) {
		lblPasswordRequired.setText(message);
	}
	
	private void resetErrorMessages() {
		lblEmailRequired.setText("");
		lblPasswordRequired.setText("");
	}
	
	private boolean validateCredentials(String email, String password) {
		
		resetErrorMessages();
		
		if(email.trim().isEmpty()) {
			showEmailError("El correo es obligatorio");
			return false;
		}
				
		if(password.trim().isEmpty()) {
			showPasswordError("La contraseña es obligatoria");
			return false;
		};
		
		return true;
	}
}