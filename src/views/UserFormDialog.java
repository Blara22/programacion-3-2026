package views;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import models.User;

public class UserFormDialog extends JDialog{

	private JTextField txtName;
    private JTextField txtEmail;
    private JTextArea txtDescription;

    private JComboBox<String> cboCountry;

    private JRadioButton rbtnMale;
    private JRadioButton rbtnFemale;
    private ButtonGroup genderGroup;

    private JList<String> lstLanguages;

    private JButton btnSave;
    private JButton btnCancel;

    private User user;
    private boolean saved = false;
    		
    public UserFormDialog(JFrame parent, User user) {
    	super(parent, true);
    	
    	this.user = user;
    	
    	setTitle(user == null ? "Agregar usuario" : "Editar usuario");
    	
    	setSize(400, 500);
        setLocationRelativeTo(parent);
        setLayout(new BorderLayout());
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        
        add(createTitlePanel(), BorderLayout.NORTH);
        add(createFormPanel());
        add(createButtonPanel(), BorderLayout.SOUTH);
        
        loadData();
        
    }
    
    private JPanel createTitlePanel() {
        JPanel panel = new JPanel();
        panel.add(new JLabel("Formulario de Usuario"));
        return panel;
    }
    
    private JPanel createButtonPanel() {

        JPanel panel = new JPanel();

        btnSave = new JButton("Guardar");
        btnCancel = new JButton("Cancelar");

        panel.add(btnSave);
        panel.add(btnCancel);
        
        btnSave.addActionListener(e -> save());
        btnCancel.addActionListener(e -> dispose());
        
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

		txtDescription = new JTextArea(4, 20);

		lstLanguages = new JList<>(new String[] { "Java", "C++", "Python", "JavaScript" });

		panel.add(createField("Nombre:", txtName));
		panel.add(createField("Email:", txtEmail));
		panel.add(createField("País:", cboCountry));

		JPanel genderPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		genderPanel.add(rbtnMale);
		genderPanel.add(rbtnFemale);

		panel.add(createField("Género:", genderPanel));

		panel.add(createField("Descripción:", new JScrollPane(txtDescription)));
		panel.add(createField("Lenguajes:", new JScrollPane(lstLanguages)));
		
		return scroll;
    }
    		
    private JPanel createField(String labelText, Component field) {

		JPanel panel = new JPanel();
		panel.setBorder(BorderFactory.createEmptyBorder(5, 0, 5, 0));
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		panel.setAlignmentX(Component.CENTER_ALIGNMENT);

		JLabel label = new JLabel(labelText);
		label.setMaximumSize(new Dimension(Integer.MAX_VALUE, label.getPreferredSize().height));
		label.setHorizontalAlignment(SwingConstants.LEFT);
		label.setAlignmentX(Component.CENTER_ALIGNMENT);

		panel.add(label);
		panel.add(field);

		return panel;
	}
    
    private void loadData() {
    	if(user != null) {
    		txtName.setText(user.getName());
            txtEmail.setText(user.getEmail());
            cboCountry.setSelectedItem(user.getCountry());

            if (user.getGender() == 'M') {
                rbtnMale.setSelected(true);
            } else {
                rbtnFemale.setSelected(true);
            }

            txtDescription.setText(user.getDescription());

            List<String> langs = user.getLanguages();

            int[] indices = new int[langs.size()];
            int i = 0;

            for (String lang : langs) {
                if (lang.equals("Java")) indices[i++] = 0;
                else if (lang.equals("C++")) indices[i++] = 1;
                else if (lang.equals("Python")) indices[i++] = 2;
                else if (lang.equals("JavaScript")) indices[i++] = 3;
            }

            lstLanguages.setSelectedIndices(indices);
    	}
    }
    
    private void save() {
    	String name = txtName.getText();
    	String email = txtEmail.getText();
        String country = (String) cboCountry.getSelectedItem();

        char gender = rbtnMale.isSelected() ? 'M' : 'F';

        String description = txtDescription.getText();

        List<String> languages = new ArrayList<>();

        List<String> selected = lstLanguages.getSelectedValuesList();

        for (String lang : selected) {
            languages.add(lang);
        }
        
        if(user == null) {
        	user = new User(name, email, country, gender, description, languages);
        }else {
        	user.setName(name);
        	user.setEmail(email);
        	user.setCountry(country);
            user.setGender(gender);
            user.setDescription(description);
            user.setLanguages(languages);
        }
        
        saved = true;
        dispose();
    }
    		
    public boolean isSaved() {
    	return saved;
    }
    
    public User getUser() {
    	return user;
    }
    		
    		
    		
    		
    		
    		
    		;

}
