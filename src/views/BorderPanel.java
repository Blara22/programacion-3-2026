package views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.Border;

public class BorderPanel extends JPanel {
	
	public BorderPanel() {
		setLayout(new BorderLayout());
		//setBackground(Color.BLUE);
		Border emptyBorder = BorderFactory.createEmptyBorder(20,10,20,10);
		setBorder(emptyBorder);
		
		JPanel panelSuperior = new JPanel();
		panelSuperior.setBackground(Color.GREEN);
		add(panelSuperior, BorderLayout.NORTH);
		
		JButton b = new JButton("Hola");
		panelSuperior.add(b);
		
		JButton b2 = new JButton("Botón 2");
		panelSuperior.add(b2);
		
		crearPanelCentro();
		
		JButton btnSur = new JButton("SUR");
		add(btnSur, BorderLayout.SOUTH);
	}
	
	public void crearPanelCentro() {
		JPanel panelCentro = new JPanel(new BorderLayout());
		panelCentro.setBackground(Color.RED);
		
		JPanel panelCentroSur = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		panelCentro.add(panelCentroSur, BorderLayout.SOUTH);
		panelCentroSur.setBackground(Color.ORANGE);
		
		JButton btnInicio = new JButton("Iniciar sesión");
		panelCentroSur.add(btnInicio);
		
		JButton btnCancelar = new JButton("Cancelar");
		panelCentroSur.add(btnCancelar);
		
		add(panelCentro, BorderLayout.CENTER);
	
	}
}











