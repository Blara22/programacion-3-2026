package main;

import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JFrame;

public class Ventana extends JFrame { 
	
	public Ventana() {
		setSize(500, 500);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocation(100,100);
		//setBounds(100,100,500,500);
		setResizable(false);
		setTitle("Mi Aplicación V2.0");
		setLocationRelativeTo(null);
		
		Toolkit tk = Toolkit.getDefaultToolkit();
		Image icono = tk.getImage("src/img/icono.png");
		setIconImage(icono);
		
		MiPanel panelito = new MiPanel();
		add(panelito);
		
		setVisible(true);
		
		//validate();
		//repaint();
	}
	
}









