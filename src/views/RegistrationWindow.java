package views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import utils.AppFont;
import views.components.ErrorLabel;

public class RegistrationWindow extends JFrame {

	private JTextField txtName;
	private JTextField txtEmail;
	private JTextArea txtDescription;

	private JComboBox<String> cboCountry;

	private JRadioButton rbtnMale;
	private JRadioButton rbtnFemale;
	private ButtonGroup genderGroup;

	private JCheckBox chkTerms;

	private JList<String> lstLanguages;

	private ErrorLabel lblErrorName;
	private JLabel lblErrorEmail;
	private JLabel lblErrorCombo;
	private JLabel lblErrorGender;
	private JLabel lblErrorTerms;
	private JLabel lblErrorList;
	private JLabel lblErrorDescription;

	public RegistrationWindow() {

		setTitle("Formulario de Registro");
		setSize(400, 600);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Image iconImage = toolkit.getImage("src/img/icono.png");
		setIconImage(iconImage);

		add(createTitlePanel(), BorderLayout.NORTH);
		add(createFormPanel());
		add(createButtonPanel(), BorderLayout.SOUTH);

		pack();
		setVisible(true);
	}

	private JPanel createTitlePanel() {
		JPanel panel = new JPanel();

		JLabel title = new JLabel("Formulario de Registro");
		title.setFont(AppFont.title());

		panel.add(title);

		return panel;
	}

	private JScrollPane createFormPanel() {

		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		panel.setBorder(BorderFactory.createEmptyBorder(15, 20, 15, 20));
		
		JScrollPane scroll = new JScrollPane(panel);
		scroll.setBorder(null);
		scroll.setHorizontalScrollBar(null);
		scroll.getVerticalScrollBar().setUnitIncrement(14);

		txtName = new JTextField();
		txtEmail = new JTextField();

		cboCountry = new JComboBox<>(new String[] { "Seleccione", "México", "USA", "Canada" });

		rbtnMale = new JRadioButton("Masculino");
		rbtnFemale = new JRadioButton("Femenino");

		genderGroup = new ButtonGroup();
		genderGroup.add(rbtnMale);
		genderGroup.add(rbtnFemale);

		chkTerms = new JCheckBox("Aceptar términos");
		chkTerms.setAlignmentX(Component.LEFT_ALIGNMENT);

		txtDescription = new JTextArea(4, 20);

		lstLanguages = new JList<>(new String[] { "Java", "C++", "Python", "JavaScript" });

		lblErrorName = new ErrorLabel();
		lblErrorEmail = createErrorLabel();
		lblErrorCombo = createErrorLabel();
		lblErrorGender = createErrorLabel();
		lblErrorTerms = createErrorLabel();
		lblErrorList = createErrorLabel();
		lblErrorDescription = createErrorLabel();
		
		assignListeners();

		/* CREAR PANELES CON COMPONENTES */

		// Nombre
		panel.add(createField("Nombre: ", txtName, lblErrorName));
		// Email
		panel.add(createField("Email: ", txtEmail, lblErrorEmail));

		panel.add(createField("País:", cboCountry, lblErrorCombo));

		JPanel genderPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		genderPanel.add(rbtnMale);
		genderPanel.add(rbtnFemale);

		panel.add(createField("Género:", genderPanel, lblErrorGender));

		panel.add(createField("Descripción:", new JScrollPane(txtDescription), lblErrorDescription));
		panel.add(createField("Lenguajes:", new JScrollPane(lstLanguages), lblErrorList));

		JPanel termsPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
		termsPanel.add(chkTerms);
		termsPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

		panel.add(createField("", termsPanel, lblErrorTerms));

		return scroll;
	}

	private JPanel createButtonPanel() {

		JPanel panel = new JPanel();

		JButton btnValidate = new JButton("Validar");
		btnValidate.addActionListener(e -> validateForm());

		panel.add(btnValidate);
		
		JButton btnReturn = new JButton("Regresar");
		btnReturn.addActionListener(e -> {
			
			int option = JOptionPane.showConfirmDialog(this, "¿Seguro que deseas regresar? Se perderán todos los datos");
			
			if(option == JOptionPane.YES_OPTION) {
				new LoginWindow();
				dispose();
			}
			
		});
		
		panel.add(btnReturn);

		return panel;
	}

	private JPanel createField(String labelText, Component field, JLabel errorLabel) {

		JPanel panel = new JPanel();
		panel.setBorder(BorderFactory.createEmptyBorder(5, 0, 5, 0));
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		panel.setAlignmentX(Component.CENTER_ALIGNMENT);

		JLabel label = new JLabel(labelText);
		label.setMaximumSize(new Dimension(Integer.MAX_VALUE, label.getPreferredSize().height));
		label.setHorizontalAlignment(SwingConstants.LEFT);
		label.setAlignmentX(Component.CENTER_ALIGNMENT);

		errorLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

		panel.add(label);
		panel.add(field);
		panel.add(errorLabel);

		return panel;

	}

	private JLabel createErrorLabel() {
		JLabel label = new JLabel();
		label.setFont(AppFont.small());
		label.setForeground(Color.RED);
		label.setHorizontalAlignment(SwingConstants.LEFT);
		label.setMaximumSize(new Dimension(Integer.MAX_VALUE, label.getPreferredSize().height));

		return label;
	}
	
	private void assignListeners() {
		cboCountry.addActionListener(e -> {
			validateComboBox();
		});
		
		chkTerms.addActionListener(e -> validateTerms());
		
		txtName.getDocument().addDocumentListener(new DocumentListener() {
			
			@Override
			public void removeUpdate(DocumentEvent e) {
				validateName();
			}
			
			@Override
			public void insertUpdate(DocumentEvent e) {
				validateName();
			}
			
			@Override
			public void changedUpdate(DocumentEvent e) {
				validateName();
			}
		});
		
		lstLanguages.addListSelectionListener(e -> validateList());
		
		txtName.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				
				if(Character.isDigit(e.getKeyChar()) || !Character.isAlphabetic(e.getKeyChar())) {
					System.out.println("Es número o especial");
					e.consume();
				}
				
				if(txtName.getText().length() >= 10) {
					e.consume();
				}
				
				txtName.setForeground(new Color((int)(Math.random() * 255), (int)(Math.random() * 255), (int)(Math.random() * 255) ));
				
				char c = e.getKeyChar();
				
				if(Character.isLowerCase(c)) {
					e.setKeyChar(Character.toUpperCase(c));
				}
				
				/*if(!Character.isDigit(e.getKeyChar())) {
					e.consume();
				}*/
				
				
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
				System.out.println(e.getKeyCode());
				if(e.getKeyCode() == KeyEvent.VK_ENTER) {
					txtEmail.requestFocusInWindow();
					System.out.println("Enter");
				}
			}
		});
		
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {
				cboCountry.requestFocusInWindow();
			}
		});
		
		txtName.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				txtName.selectAll();
			}
		});
		
		txtEmail.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				txtEmail.setBorder(BorderFactory.createLineBorder(Color.BLUE, 2));
			}
			
			@Override
			public void focusLost(FocusEvent e) {
				txtEmail.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
			}
			
			
		});
	}

	private void validateForm() {
		resetErrorLabels();

		boolean valid = true;

		if (!validateName())
			valid = false;

		if (!validateEmail())
			valid = false;

		if (!validateComboBox())
			valid = false;

		if (!validateGender())
			valid = false;

		if (!validateTerms())
			valid = false;

		if (!validateDescription())
			valid = false;

		if (!validateList())
			valid = false;

		if (valid) {
			JOptionPane.showMessageDialog(this, "Registro exitoso");
		}

	}

	private void resetErrorLabels() {
		lblErrorEmail.setText("");
		lblErrorGender.setText("");
		lblErrorDescription.setText("");
	}

	private boolean validateName() {

		if (txtName.getText().trim().isEmpty()) {
			lblErrorName.setText("El nombre es obligatorio");
			return false;
		}
		
		if (txtName.getText().trim().length() <= 3) {
			lblErrorName.setText("Mínimo 4 caracteres");
			return false;
		}

		lblErrorName.setText("");
		return true;
	}

	private boolean validateEmail() {

		if (txtEmail.getText().trim().isEmpty()) {
			lblErrorEmail.setText("El email es obligatorio");
			return false;
		}

		if (!txtEmail.getText().contains("@")) {
			lblErrorEmail.setText("Email inválido");
			return false;
		}

		return true;
	}

	private boolean validateComboBox() {
		
		if (cboCountry.getSelectedIndex() == 0) {
			lblErrorCombo.setText("Seleccione un país");
			return false;
		}

		lblErrorCombo.setText("");
		return true;
	}

	private boolean validateGender() {

		if (!rbtnMale.isSelected() && !rbtnFemale.isSelected()) {
			lblErrorGender.setText("Seleccione un género");
			return false;
		}

		return true;
	}

	private boolean validateTerms() {

		if (!chkTerms.isSelected()) {
			lblErrorTerms.setText("Debe aceptar los términos");
			return false;
		}

		lblErrorTerms.setText("");
		return true;
	}

	private boolean validateDescription() {

		if (txtDescription.getText().trim().length() < 10) {
			lblErrorDescription.setText("Descripción mínima 10 caracteres");
			return false;
		}

		return true;
	}

	private boolean validateList() {

		if (lstLanguages.getSelectedValuesList().isEmpty()) {
			lblErrorList.setText("Seleccione al menos un lenguaje");
			return false;
		}

		lblErrorList.setText("");
		return true;
	}

}







