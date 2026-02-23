package views;

import java.awt.FlowLayout;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class FlowPanel extends JPanel{

	public FlowPanel() {
		setLayout(new FlowLayout(FlowLayout.RIGHT, 20, 20));
		
		JButton buttons[] = new JButton[10];
		
		for(int i = 0; i < 10; i++) {
			 buttons[i] = new JButton(i + "");
			 //buttons[i].setEnabled(false);
			 add(buttons[i]);
		}
		
		JLabel lbl = new JLabel("Hola mundo");
		add(lbl);
		
		JTextField txt = new JTextField("fgjhdfkjgdfg", 30);
		txt.setEditable(false);
		add(txt);
		
		JCheckBox chkAceptoCondiciones = new JCheckBox("Acepto condiciones", true);
		add(chkAceptoCondiciones);
		
		String meses[] = {"Enero", "Febrero", "Marzo", "Abril", "Enero", "Febrero", "Marzo", "Abril", "Enero", "Febrero", "Marzo", "Abril","Enero", "Febrero", "Marzo", "Abril"};
		JComboBox<String> comboMeses = new JComboBox<String>(meses);
		comboMeses.setSelectedIndex(0);
		add(comboMeses);
		
		JRadioButton rbMujer = new JRadioButton("Mujer");
		add(rbMujer);
		JRadioButton rbHombre = new JRadioButton("Hombre");
		add(rbHombre);
		
		ButtonGroup bgSexo = new ButtonGroup();
		bgSexo.add(rbHombre);
		bgSexo.add(rbMujer);
		
		JSlider sliderVolumen = new JSlider(0,100,27);
		add(sliderVolumen);
		sliderVolumen.setMajorTickSpacing(25);
		sliderVolumen.setMinorTickSpacing(5);
		sliderVolumen.setPaintTicks(true);
		sliderVolumen.setPaintLabels(true);
		
		JTextArea areaDescripcion = new JTextArea(5, 30);
		areaDescripcion.setText("Hola mundo");
		areaDescripcion.setEditable(false);
		JScrollPane scroll = new JScrollPane(areaDescripcion);
		scroll.setHorizontalScrollBar(null);
		
		add(scroll);
		
		JList<String> lista = new JList<String>(meses);
		JScrollPane scrollLista = new JScrollPane(lista);
		add(scrollLista);
		
		JTabbedPane tabs = new JTabbedPane();
		tabs.add("Grid", new GridPanel());
		tabs.add("Border", new BorderPanel());
		tabs.add("Vacío", new JPanel());
		add(tabs);
		
	}
	
	
	
	
	
	
	
	
	
	
	
}
