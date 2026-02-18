package views;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.JTextPane;

import components.RoundButton;
import components.TextPrompt;

public class LoginView extends JPanel{
	
	Font fuente;
	
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
		
		RoundButton boton = new RoundButton("Login");
		boton.setBounds(250,320,150,30);
		boton.setBackground(Color.GREEN);
		boton.setToolTipText("Haz click aquí");
		boton.setFont(fuente);
				
		add(boton);
		
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
		
		JTextField txtEmail = new JTextField();
		TextPrompt promptEmail = new TextPrompt("Ingresa tu usuario", txtEmail);
		txtEmail.setFont(fuente);
		txtEmail.setBounds(txtX,y,200,40);
		add(txtEmail);
		
		JLabel lblEmailRequerido = new JLabel("El email es requerido.");
		lblEmailRequerido.setBounds(txtX, y+35, 200, 30);
		lblEmailRequerido.setFont(new Font("Arial", Font.BOLD, 10));
		lblEmailRequerido.setForeground(Color.RED);
		add(lblEmailRequerido);
		
		y += 70;
		
		JLabel lblContrasena = new JLabel("Contraseña: ");
		lblContrasena.setFont(fuente);
		lblContrasena.setBounds(lblX,y,200,40);
		add(lblContrasena);
		
		JPasswordField contrasena = new JPasswordField();
		TextPrompt promptContrasena = new TextPrompt("Ingresa tu contraseña", contrasena);
		contrasena.setFont(fuente);
		contrasena.setBounds(txtX,y,200,40);
		add(contrasena);
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
}










