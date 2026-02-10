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

public class LoginView extends JPanel{
	
	Font fuente;
	
	public LoginView() {
		
		fuente = new Font("Arial", Font.ITALIC, 20);
		setBackground(Color.BLUE);
		setLayout(null);
		
		inicializarComponentes();
		//setBackground(new Color(210,165,35));
	}
	
	private void inicializarComponentes() {
		crearBotones();
		crearFormulario();
	}
	
	private void crearBotones() {
		
		JButton boton = new JButton("Mi botón");
		boton.setBounds(215,315,160,40);
		boton.setBackground(Color.RED);
		boton.setForeground(Color.WHITE);
		boton.setToolTipText("Haz click aquí");
		boton.setFont(fuente);
		
		colocarIcono(boton, "../img/icono.png");
		
		add(boton);
		
	}
	
	private void crearFormulario() {
		JLabel lblSaludo = new JLabel("Bienvenido!");
		lblSaludo.setForeground(Color.WHITE);
		lblSaludo.setFont(fuente);
		lblSaludo.setBounds(10,0,200,40);
		add(lblSaludo);
		
		JLabel lblEmail = new JLabel("Email: ");
		lblEmail.setForeground(Color.WHITE);
		lblEmail.setFont(fuente);
		lblEmail.setBounds(10,100,200,40);
		add(lblEmail);
		
		JTextField texto = new JTextField();
		texto.setForeground(Color.BLUE);
		texto.setFont(fuente);
		texto.setBounds(150,100,200,40);
		add(texto);
		
		JLabel lblContrasena = new JLabel("Contraseña: ");
		lblContrasena.setForeground(Color.WHITE);
		lblContrasena.setFont(fuente);
		lblContrasena.setBounds(10,150,200,40);
		add(lblContrasena);
		
		JPasswordField contrasena = new JPasswordField();
		contrasena.setForeground(Color.BLUE);
		contrasena.setFont(fuente);
		contrasena.setBounds(150,150,200,40);
		add(contrasena);
	}
	
	private void colocarIcono(JButton boton, String ruta) {
		try {
			Image icono = ImageIO.read(getClass().getResource(ruta));
			icono = icono.getScaledInstance(30, 30, Image.SCALE_SMOOTH);
			boton.setIcon(new ImageIcon(icono));			
		}catch(Exception ex) {
			System.out.println("No está la imagen del ícono");
		}
	}
}











