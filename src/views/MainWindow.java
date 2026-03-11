package views;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseMotionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class MainWindow extends JFrame {

	JMenuItem salir;
	
	public MainWindow() {
		
		setSize(500,500);
		setTitle("Mi aplicación");
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		
		setMenu();
		assignMenuListeners();
		setVisible(true);
		
		JPanel panel = new JPanel();
		add(panel);
		panel.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if(e.getButton() == MouseEvent.BUTTON1 && e.isControlDown()) {
					System.out.println("Clicks: " + e.getClickCount());
					System.out.println("X: " + e.getX());
					System.out.println("Y: " + e.getY());
					//System.out.println(e.getPoint().x);
					//System.out.println(e.getPoint().y);
					System.out.println("Clic izquierdo");
				}
			}
		});
		
		panel.addMouseMotionListener(new MouseMotionAdapter() {
			public void mouseDragged(MouseEvent e) {
				System.out.println("Arrastrando " + e.getX() + ", " + e.getY());
			}
		});
		
		/*panel.addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {
				System.out.println("Se escribió una tecla");
				System.out.println("Code: " + e.getKeyCode());
				System.out.println("Char: " + e.getKeyChar());
				
			}
			
			@Override
			public void keyReleased(KeyEvent e) {
				System.out.println("Se soltó una tecla");
				System.out.println("Code: " + e.getKeyCode());
				System.out.println("Char: " + e.getKeyChar());
				
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
				System.out.println("Se presionó una tecla");
				System.out.println("Code: " + e.getKeyCode());
				System.out.println("Char: " + e.getKeyChar());
				
			}
		});
		
		panel.requestFocus();*/;
		
		addWindowListener(new WindowListener() {
			
			@Override
			public void windowOpened(WindowEvent e) {
				System.out.println("Se abrió la ventana");
				
			}
			
			@Override
			public void windowIconified(WindowEvent e) {
				System.out.println("Se minimizó");
				
			}
			
			@Override
			public void windowDeiconified(WindowEvent e) {
				System.out.println("Se volvió a abrir");
				
			}
			
			@Override
			public void windowDeactivated(WindowEvent e) {
				System.out.println("Perdió el focus");
				
			}
			
			@Override
			public void windowClosing(WindowEvent e) {
				handleClose();
				
			}
			
			@Override
			public void windowClosed(WindowEvent e) {
				System.out.println("Se cerró");
				
			}
			
			@Override
			public void windowActivated(WindowEvent e) {
				System.out.println("Obtuvo el focus");
				
			}
		});
	}
	
	public void setMenu() {
		
		JMenuBar mb = new JMenuBar();
		setJMenuBar(mb);
		
		JMenu archivo = new JMenu("Archivo");
		archivo.setMnemonic(KeyEvent.VK_A);
		mb.add(archivo);
		
		JMenuItem abrir = new JMenuItem("Abrir");
		abrir.setMnemonic(KeyEvent.VK_B);
		archivo.add(abrir);
		
		JMenuItem guardar = new JMenuItem("Guardar");
		guardar.setMnemonic(KeyEvent.VK_G);
		archivo.add(guardar);
		
		archivo.addSeparator();
		
		salir = new JMenuItem("Salir");
		salir.setMnemonic(KeyEvent.VK_S);
		archivo.add(salir);
		
		JMenu otraOpcion = new JMenu("Otra opción");
		otraOpcion.setMnemonic(KeyEvent.VK_O);
		mb.add(otraOpcion);
		
		JMenu opcion1 = new JMenu("Opción 1");
		otraOpcion.add(opcion1);
		
		JMenuItem opcion3 = new JMenuItem("Opción 3");
		opcion1.add(opcion3);
		
		JMenuItem opcion2 = new JMenuItem("Opción 2");
		otraOpcion.add(opcion2);
		
	}
	
	public void assignMenuListeners() {
		salir.addActionListener(e -> {
			int option = JOptionPane.showConfirmDialog(this, "¿Seguro que deseas regresar? Se perderán todos los datos", "¿Seguro?", JOptionPane.YES_NO_OPTION);
			
			if(option == JOptionPane.YES_OPTION) {
				new LoginWindow();
				dispose();
			}
		});
	}
	
	private void handleClose() {
		int option = JOptionPane.showConfirmDialog(this, "¿Seguro que deseas regresar? Se perderán todos los datos");
		
		if(option == JOptionPane.YES_OPTION) {
			System.exit(0);
			/*new LoginWindow();
			dispose();*/
		}
	}
	
}










