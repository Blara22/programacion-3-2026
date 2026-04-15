package views;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.FlowLayout;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class MainWindow extends JFrame {

	public static final String HOME = "HOME";
	public static final String USERS = "USERS";
	
	public JMenuItem mItemExit;
	public JButton btnUsers;
	public JButton btnHome;
	public UsersView usersPanel;
	
	private CardLayout cardLayout;
	private JPanel container;
	
	public MainWindow() {

		setSize(500, 500);
		setTitle("Mi aplicación");
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

		setMenu();
		
		createNavbar();
		createViews();

		setVisible(true);
	}
	
	public void createNavbar() {
		JPanel navbar = new JPanel(new FlowLayout(FlowLayout.LEFT));
		
		btnHome = new JButton("Inicio");
		btnUsers = new JButton("Usuarios");
		
		navbar.add(btnHome);
		navbar.add(btnUsers);
		
		add(navbar, BorderLayout.NORTH);
	}
	
	private void createViews() {
		cardLayout = new CardLayout();
		container = new JPanel(cardLayout);
		
		JPanel homePanel = new JPanel();
		homePanel.add(new JLabel("Bienvenido al Sistema"));
		
		usersPanel = new UsersView();
		
		container.add(homePanel, HOME);
		container.add(usersPanel, USERS);
		
		add(container, BorderLayout.CENTER);
		
	}
	
	public void showView(String view) {
		cardLayout.show(container, view);
	}

	public void setMenu() {

	    JMenuBar mb = new JMenuBar();
	    setJMenuBar(mb);

	    JMenu menuFile = new JMenu("File");
	    menuFile.setMnemonic(KeyEvent.VK_F);
	    mb.add(menuFile);

	    JMenuItem mItemOpen = new JMenuItem("Open");
	    mItemOpen.setMnemonic(KeyEvent.VK_O);
	    menuFile.add(mItemOpen);

	    JMenuItem mItemSave = new JMenuItem("Save");
	    mItemSave.setMnemonic(KeyEvent.VK_S);
	    menuFile.add(mItemSave);

	    menuFile.addSeparator();

	    mItemExit = new JMenuItem("Exit");
	    mItemExit.setMnemonic(KeyEvent.VK_E);
	    menuFile.add(mItemExit);

	    JMenu menuOtherOption = new JMenu("Other Option");
	    menuOtherOption.setMnemonic(KeyEvent.VK_O);
	    mb.add(menuOtherOption);

	    JMenu menuOption1 = new JMenu("Option 1");
	    menuOtherOption.add(menuOption1);

	    JMenuItem mItemOption3 = new JMenuItem("Option 3");
	    menuOption1.add(mItemOption3);

	    JMenuItem mItemOption2 = new JMenuItem("Option 2");
	    menuOtherOption.add(mItemOption2);

	}
	
	public int confirmExit() {
	    return JOptionPane.showConfirmDialog(
	        this,
	        "¿Seguro que deseas regresar? Se perderán todos los datos",
	        "¿Seguro?",
	        JOptionPane.YES_NO_OPTION
	    );
	}

	// Este método no se usa en el proyecto, pero queda de ejemplo para listeners de ventana, mouse, etc
	private void ejemplo() {
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
		
		/*panel.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (e.getButton() == MouseEvent.BUTTON1 && e.isControlDown()) {
					System.out.println("Clicks: " + e.getClickCount());
					System.out.println("X: " + e.getX());
					System.out.println("Y: " + e.getY());
					// System.out.println(e.getPoint().x);
					// System.out.println(e.getPoint().y);
					System.out.println("Clic izquierdo");
				}
			}
		});

		panel.addMouseMotionListener(new MouseMotionAdapter() {
			public void mouseDragged(MouseEvent e) {
				System.out.println("Arrastrando " + e.getX() + ", " + e.getY());
			}
		});*/

	}
}
