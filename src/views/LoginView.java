package views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.SwingConstants;

import exceptions.InvalidPasswordException;
import exceptions.InvalidUserException;
import lib.SpringUtilities;
import views.components.TextPrompt;

public class LoginView extends JPanel{
	
	LoginWindow window;
	Font font;
	Image backgroundImage;
	JTextField emailField;
	JPasswordField passwordField;
	JLabel lblEmailRequired;
	JLabel lblPasswordRequired;
	Color defaultButtonColor;

	public LoginView(LoginWindow window) {
		this.window = window;
		font = new Font("Arial", Font.PLAIN, 14);
		setLayout(new BorderLayout());
		
		loadImage();
		initializeComponents();
	}
	
	private void initializeComponents() {
		createButtons();
		createLogo();
		createForm();
	}
	
	private void createButtons() {
		
		JPanel buttonsPanel = new JPanel();
		buttonsPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
		buttonsPanel.setOpaque(false);
		buttonsPanel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
		
		JLabel lblRegister = new JLabel("¿No tienes cuenta? Regístrate aquí");
		lblRegister.setCursor(new Cursor(Cursor.HAND_CURSOR));
		lblRegister.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				handleRegistration();
			}
			
			public void mouseEntered(MouseEvent e) {
				lblRegister.setForeground(Color.GREEN);
			}
			
			public void mouseExited(MouseEvent e) {
				lblRegister.setForeground(Color.BLACK);
			}
		});
		buttonsPanel.add(lblRegister);
		
		JButton btnLogin = new JButton("Login");
		defaultButtonColor = btnLogin.getBackground();
		btnLogin.setToolTipText("Haz click aquí para iniciar sesión");
		btnLogin.setFont(font);
		buttonsPanel.add(btnLogin);
		
		btnLogin.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent e) {
				changeBackground(btnLogin);
				//btnLogin.setIcon(new ImageIcon(getClass().getResource("/img/icono.png")));
			}
			
			public void mouseExited(MouseEvent e) {
				resetBackground(btnLogin);
			}
		});
		
		btnLogin.addActionListener(e-> handleLogin());
		
		/*JButton btnRegister = new JButton("Regístrate");
		btnRegister.setToolTipText("¿No tienes cuenta? Créala aquí");
		btnRegister.setFont(font);
		buttonsPanel.add(btnRegister);
		
		btnRegister.addActionListener(e-> handleRegistration());*/
		
		add(buttonsPanel, BorderLayout.SOUTH);
		
	}
	
	private void changeBackground(JComponent c) {
		c.setBackground(Color.BLACK);
		c.setForeground(Color.WHITE);
	}
	
	private void resetBackground(JComponent c) {
		c.setBackground(defaultButtonColor);
		c.setForeground(Color.BLACK);
	}
	
	private void createLogo() {
		JPanel panelLogo = new JPanel(new FlowLayout(FlowLayout.CENTER));
		panelLogo.setBorder(BorderFactory.createEmptyBorder(30,0,0,0));
		panelLogo.setOpaque(false);
		JLabel lblLogo = new JLabel();
		lblLogo.setIcon(loadIcon("../img/icono.png", 100, 100));
		panelLogo.add(lblLogo);
		add(panelLogo, BorderLayout.NORTH);
	}
	
	private void createForm() {
		JPanel formPanel = new JPanel();
		formPanel.setOpaque(false);
		formPanel.setLayout(new SpringLayout());
		formPanel.setBorder(BorderFactory.createEmptyBorder(50,20,10,20));
		
		JLabel lblEmail = new JLabel("Email: ");
		lblEmail.setFont(font);
		lblEmail.setMaximumSize(new Dimension(150, lblEmail.getPreferredSize().height));
		lblEmail.setHorizontalAlignment(SwingConstants.RIGHT);
		formPanel.add(lblEmail);
		
		emailField = new JTextField();
		new TextPrompt("Ingresa tu usuario", emailField);
		emailField.setFont(font);
		emailField.setMaximumSize(new Dimension(Integer.MAX_VALUE, emailField.getPreferredSize().height));
		formPanel.add(emailField);
		emailField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				
				if(e.getKeyCode() == KeyEvent.VK_ENTER) {
					handleLogin();
				}
			}
		});
		
		formPanel.add(new JLabel());
		
		lblEmailRequired = new JLabel("El email es requerido.");
		lblEmailRequired.setFont(new Font("Arial", Font.BOLD, 10));
		lblEmailRequired.setForeground(Color.RED);
		lblEmailRequired.setVisible(false);
		formPanel.add(lblEmailRequired);
		
		
		JLabel lblPasswordLabel = new JLabel("Contraseña: ");
		lblPasswordLabel.setFont(font);
		lblPasswordLabel.setMaximumSize(new Dimension(150, lblPasswordLabel.getPreferredSize().height));
		formPanel.add(lblPasswordLabel);
		
		passwordField = new JPasswordField();
		new TextPrompt("Ingresa tu contraseña", passwordField);
		passwordField.setFont(font);
		passwordField.setMaximumSize(new Dimension(Integer.MAX_VALUE, passwordField.getPreferredSize().height));
		formPanel.add(passwordField);
		
		passwordField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				
				if(e.getKeyCode() == KeyEvent.VK_ENTER) {
					handleLogin();
				}
			}
		});
		
		formPanel.add(new JLabel());
		
		lblPasswordRequired = new JLabel("");
		lblPasswordRequired.setFont(new Font("Arial", Font.BOLD, 10));
		lblPasswordRequired.setForeground(Color.RED);
		formPanel.add(lblPasswordRequired);
		
		SpringUtilities.makeCompactGrid(formPanel, 4, 2, 0, 0, 10, 10);
		
		add(formPanel);
		
		
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
		
		try {
			if(validateCredentials(emailField.getText(), String.valueOf(passwordField.getPassword()))) {
				JOptionPane.showMessageDialog(
					this,
	 				"Se inició la sesión", 
	 				"Sesión iniciada", 
	 				JOptionPane.INFORMATION_MESSAGE
	 			);
				
				new MainWindow();
				window.dispose();
			}
		}catch(InvalidUserException ex) {
			showPasswordError("Credenciales Incorrectas");
		}catch(InvalidPasswordException ex) {
			showPasswordError("Credenciales Incorrectas");
		}
	}
	
	private void handleRegistration() {
		new RegistrationWindow();
		window.dispose();
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
	
	private boolean validateCredentials(String email, String password) 
			throws InvalidUserException, InvalidPasswordException {
		
		resetErrorMessages();
		
		boolean valid = true;
		
		if(email.trim().isEmpty()) {
			showEmailError("El correo es obligatorio");
			valid = false;
		}
				
		if(password.trim().isEmpty()) {
			showPasswordError("La contraseña es obligatoria");
			valid = false;
		};
		
		
		
		if(!email.trim().isEmpty() && !email.trim().equals("b.lara@uabcs.mx")) {
			throw new InvalidUserException("El correo no coincide.");
		}
		
		if(!password.trim().isEmpty() && !password.trim().equals("1234")) {
			throw new InvalidPasswordException("La contraseña no coincide");
		}
		
		return valid;
	}
	
	
	
	
	
	
	
}