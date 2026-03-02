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

import components.TextPrompt;

public class LoginView extends JPanel{
	
	Font fuente;
	JTextField txtEmail;
	JPasswordField contrasena;
	JLabel lblEmailRequerido;
	JLabel lblContrasenaRequerida;
	
	public LoginView() {
		
		fuente = new Font("Arial", Font.PLAIN, 14);
		setLayout(null);
		
		inicializarComponentes();
	}
	
	private void inicializarComponentes() {
		crearBotones();
		crearLogo();
		crearFormulario();
	}
	
	private void crearBotones() {
		
		JButton boton = new JButton("Login");
		boton.setBounds(250,320,100,30);
		//boton.setBackground(Color.GREEN);
		boton.setToolTipText("Haz click aquí");
		boton.setFont(fuente);
		add(boton);
		
		/*boton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				login();
			}
			
		});*/
		
		boton.addActionListener(e -> login());
	}
	
	private void crearLogo() {
		JLabel lblLogo = new JLabel();
		lblLogo.setBounds(145, 50, 100, 100);
		lblLogo.setIcon(cargarIcono("../img/icono.png", 100, 100));
		add(lblLogo);
	}
	
	private void crearFormulario() {
		JLabel lblSaludo = new JLabel("Bienvenido!");
		lblSaludo.setFont(fuente);
		lblSaludo.setBounds(10,0,200,40);
		add(lblSaludo);
		
		int lblX = 10, y = 170, txtX = 150;
		
		JLabel lblEmail = new JLabel("Email: ");
		lblEmail.setFont(fuente);
		lblEmail.setBounds(lblX,y,200,40);
		add(lblEmail);
		
		txtEmail = new JTextField();
		new TextPrompt("Ingresa tu usuario", txtEmail);
		txtEmail.setFont(fuente);
		txtEmail.setBounds(txtX,y,200,40);
		add(txtEmail);
		
		lblEmailRequerido = new JLabel("El email es requerido.");
		lblEmailRequerido.setBounds(txtX, y+35, 200, 30);
		lblEmailRequerido.setFont(new Font("Arial", Font.BOLD, 10));
		lblEmailRequerido.setForeground(Color.RED);
		lblEmailRequerido.setVisible(false);
		add(lblEmailRequerido);
		
		y += 70;
		
		JLabel lblContrasena = new JLabel("Contraseña: ");
		lblContrasena.setFont(fuente);
		lblContrasena.setBounds(lblX,y,200,40);
		add(lblContrasena);
		
		contrasena = new JPasswordField();
		new TextPrompt("Ingresa tu contraseña", contrasena);
		contrasena.setFont(fuente);
		contrasena.setBounds(txtX,y,200,40);
		add(contrasena);
		
		lblContrasenaRequerida = new JLabel("");
		lblContrasenaRequerida.setBounds(txtX, y+35, 200, 30);
		lblContrasenaRequerida.setFont(new Font("Arial", Font.BOLD, 10));
		lblContrasenaRequerida.setForeground(Color.RED);
		add(lblContrasenaRequerida);
	}
	
	private ImageIcon cargarIcono(String ruta, int w, int h) {

		try {
			Image icono = ImageIO.read(getClass().getResource(ruta));
			icono = icono.getScaledInstance(w, h, Image.SCALE_SMOOTH);
			return new ImageIcon(icono);
		}catch(Exception ex) {
			System.out.println("No está la imagen del ícono");
		}
		
		return null;
	}
	
	public void paintComponent(Graphics g) {
		
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		
		Image fondo = null;
		
		try {
			fondo = ImageIO.read(new File("src/img/fondo.jpg"));
			//g2.drawImage(fondo, 0, 0, getWidth(), getHeight(), null);
		} catch (IOException ex) {
			System.out.println("La imagen no existe");
		}
		
	}
	
	private void login() {
		
		if(validateLogin(txtEmail.getText(), String.valueOf(contrasena.getPassword()))) {
			JOptionPane.showMessageDialog(
				this,
				"Se inició la sesión", 
				"Sesión iniciada", 
				JOptionPane.INFORMATION_MESSAGE
			);
		}
	}
	
	private void mostrarErrorCorreo(String message) {
		lblEmailRequerido.setText(message);
		lblEmailRequerido.setVisible(true);
	}
	
	private void mostrarErrorContrasena(String message) {
		lblContrasenaRequerida.setText(message);
	}
	
	private void resetMensajeError() {
		lblEmailRequerido.setText("");
		lblContrasenaRequerida.setText("");
	}
	
	private boolean validateLogin(String email, String password) {
		
		resetMensajeError();
		
		if(email.trim().isEmpty()) {
			mostrarErrorCorreo("El correo es obligatorio");
			return false;
		}
				
		if(password.trim().isEmpty()) {
			mostrarErrorContrasena("La contraseña es obligatoria");
			return false;
		};
		
		return true;
	}
}










