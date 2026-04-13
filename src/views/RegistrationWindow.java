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
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.List;

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

import utils.AppFont;
import views.components.ErrorLabel;

public class RegistrationWindow extends JFrame {

	private JButton btnValidate;
	private JButton btnReturn;

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
		Image iconImage = toolkit.getImage("src/assets/img/icono.png");
		setIconImage(iconImage);

		add(createTitlePanel(), BorderLayout.NORTH);
		add(createFormPanel());
		add(createButtonPanel(), BorderLayout.SOUTH);
		
		registerListeners();

		pack();
		setVisible(true);
	}
	
	public void registerListeners() {
		txtName.addFocusListener(new FocusAdapter(){

            @Override
            public void focusGained(FocusEvent e){
                txtName.selectAll();
            }

        });
		
		txtName.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				txtName.setForeground(
                        new Color(
                                (int)(Math.random()*255),
                                (int)(Math.random()*255),
                                (int)(Math.random()*255)
                        )
                );
			}
		});
		
		txtEmail.addFocusListener(new FocusAdapter(){

            @Override
            public void focusGained(FocusEvent e){
                txtEmail.setBorder(
                        BorderFactory.createLineBorder(Color.BLUE,2));
            }

            @Override
            public void focusLost(FocusEvent e){
                txtEmail.setBorder(
                        BorderFactory.createLineBorder(Color.GRAY,1));
            }

        });
		
	}
	
	public int confirmReturn() {
	    return JOptionPane.showConfirmDialog(
	        this,
	        "¿Seguro que deseas regresar? Se perderán todos los datos",
	        "¿Seguro?",
	        JOptionPane.YES_NO_OPTION
	    );
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
		rbtnMale.setActionCommand("M");

		rbtnFemale = new JRadioButton("Femenino");
		rbtnFemale.setActionCommand("F");

		genderGroup = new ButtonGroup();
		genderGroup.add(rbtnMale);
		genderGroup.add(rbtnFemale);

		chkTerms = new JCheckBox("Aceptar términos");

		txtDescription = new JTextArea(4, 20);

		lstLanguages = new JList<>(new String[] { "Java", "C++", "Python", "JavaScript" });

		lblErrorName = new ErrorLabel();
		lblErrorEmail = createErrorLabel();
		lblErrorCombo = createErrorLabel();
		lblErrorGender = createErrorLabel();
		lblErrorTerms = createErrorLabel();
		lblErrorList = createErrorLabel();
		lblErrorDescription = createErrorLabel();

		panel.add(createField("Nombre:", txtName, lblErrorName));
		panel.add(createField("Email:", txtEmail, lblErrorEmail));
		panel.add(createField("País:", cboCountry, lblErrorCombo));

		JPanel genderPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		genderPanel.add(rbtnMale);
		genderPanel.add(rbtnFemale);

		panel.add(createField("Género:", genderPanel, lblErrorGender));

		panel.add(createField("Descripción:", new JScrollPane(txtDescription), lblErrorDescription));
		panel.add(createField("Lenguajes:", new JScrollPane(lstLanguages), lblErrorList));

		JPanel termsPanel = new JPanel(new FlowLayout());
		termsPanel.add(chkTerms);

		panel.add(createField("", termsPanel, lblErrorTerms));

		return scroll;
	}

	private JPanel createButtonPanel() {

		JPanel panel = new JPanel();

		btnValidate = new JButton("Validar");
		btnReturn = new JButton("Regresar");

		panel.add(btnValidate);
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

	public JButton getBtnValidate() {
		return btnValidate;
	}

	public JButton getBtnReturn() {
		return btnReturn;
	}

	public JTextField getTxtName() {
		return txtName;
	}

	public JTextField getTxtEmail() {
		return txtEmail;
	}

	public JComboBox<String> getCboCountry() {
		return cboCountry;
	}

	public ButtonGroup getGenderGroup() {
		return genderGroup;
	}

	public JCheckBox getChkTerms() {
		return chkTerms;
	}

	public JList<String> getLstLanguages() {
		return lstLanguages;
	}

	public String getUserName() {
		return txtName.getText();
	}

	public String getEmail() {
		return txtEmail.getText();
	}

	public String getCountry() {
		return String.valueOf(cboCountry.getSelectedItem());
	}

	public char getGender() {
		return genderGroup.getSelection().getActionCommand().charAt(0);
	}

	public String getDescription() {
		return txtDescription.getText();
	}

	public List<String> getLanguages() {
		return lstLanguages.getSelectedValuesList();
	}

	public int getCountryIndex() {
		return cboCountry.getSelectedIndex();
	}

	public boolean isTermsAccepted() {
		return chkTerms.isSelected();
	}

	public void resetErrors() {
		lblErrorName.setText("");
		lblErrorEmail.setText("");
		lblErrorCombo.setText("");
		lblErrorGender.setText("");
		lblErrorTerms.setText("");
		lblErrorList.setText("");
		lblErrorDescription.setText("");
	}

	public void setErrorName(String m) {
		lblErrorName.setText(m);
	}

	public void setErrorEmail(String m) {
		lblErrorEmail.setText(m);
	}

	public void setErrorCombo(String m) {
		lblErrorCombo.setText(m);
	}

	public void setErrorGender(String m) {
		lblErrorGender.setText(m);
	}

	public void setErrorTerms(String m) {
		lblErrorTerms.setText(m);
	}

	public void setErrorList(String m) {
		lblErrorList.setText(m);
	}

	public void setErrorDescription(String m) {
		lblErrorDescription.setText(m);
	}

}