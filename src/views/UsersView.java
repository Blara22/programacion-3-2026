package views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;

import tablemodels.UserTableModel;
import utils.AppFont;

public class UsersView extends JPanel{

	private JTable table;
	private JButton btnEdit;
	private JButton btnAdd;
	private JButton btnDelete;
	private JButton btnPdf;
	
	public UsersView() {
		setLayout(new BorderLayout());
		table = new JTable();
		styleTable();
		
		add(new JScrollPane(table), BorderLayout.CENTER);
		
		JPanel panelButtons = new JPanel(new FlowLayout(FlowLayout.LEFT));

        btnAdd = new JButton("Agregar");
        btnEdit = new JButton("Editar");
        btnDelete = new JButton("Eliminar");
        btnPdf = new JButton("Exportar a PDF");

        panelButtons.add(btnAdd);
        panelButtons.add(btnEdit);
        panelButtons.add(btnDelete);
        panelButtons.add(btnPdf);
        
        add(panelButtons, BorderLayout.NORTH);

	}
	
	public void styleTable() {
		table.setRowHeight(35);
		table.setShowGrid(true);
		table.setGridColor(new Color(230, 230, 230));
		table.setBackground(Color.WHITE);
		table.setForeground(Color.BLACK);
		table.setFont(AppFont.normal());
		
		table.setSelectionBackground(new Color(52, 152, 219));
		table.setSelectionForeground(Color.WHITE);
		
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		JTableHeader header = table.getTableHeader();
		header.setBackground(new Color(44, 62, 80));
		header.setForeground(Color.WHITE);
		header.setFont(AppFont.bold());
		header.setPreferredSize(new Dimension(0, 40));
		header.setReorderingAllowed(false);
		
		table.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {

            @Override
            public Component getTableCellRendererComponent(
                    JTable table,
                    Object value,
                    boolean isSelected,
                    boolean hasFocus,
                    int row,
                    int column) {

                Component c = super.getTableCellRendererComponent(
                        table,
                        value,
                        isSelected,
                        hasFocus,
                        row,
                        column);
                
                if (!isSelected) {
                    if (row % 2 == 0) {
                        c.setBackground(Color.WHITE);
                    } else {
                        c.setBackground(new Color(245, 245, 245));
                    }

                    c.setForeground(Color.BLACK);
                }
				
				if(column == 1) {
					c.setFont(AppFont.bold());
					if(!isSelected) {
						c.setForeground(new Color(41, 128, 185));
					}
				} else {
					c.setFont(AppFont.normal());
				}
			
				
				return c;
				
			}
			
		});
		
		
	}
	
	public File selectPdfFile() {
		
		String path = System.getProperty("user.home");
		JFileChooser chooser = new JFileChooser(path);
		
		chooser.setSelectedFile(new File("reporte-usuarios.pdf"));
		
		chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		chooser.setAcceptAllFileFilterUsed(false);
		
		FileNameExtensionFilter filter = new FileNameExtensionFilter("Documentos PDF",  "pdf");
		chooser.addChoosableFileFilter(filter);
		chooser.setFileFilter(filter);
		
		int option = chooser.showDialog(this, "Exportar PDF de usuarios");
		
		if(option != JFileChooser.APPROVE_OPTION) {
			return null;
		}
		
		File file = chooser.getSelectedFile();
		
		if(!file.getName().toLowerCase().endsWith(".pdf")) {
			file = new File(file.getAbsolutePath() + ".pdf");
		}
		
		return file;
	}
	
	public void setTableModel(UserTableModel model) {
		table.setModel(model);
		
		if(table.getColumnCount() >= 1) {
			table.getColumnModel().getColumn(0).setPreferredWidth(80);
		}
		
		if(table.getColumnCount() >= 2) {
			table.getColumnModel().getColumn(1).setPreferredWidth(200);
		}
		
		if(table.getColumnCount() >= 3) {
			table.getColumnModel().getColumn(2).setPreferredWidth(50);
		}
		
		DefaultTableCellRenderer center = new DefaultTableCellRenderer();
		center.setHorizontalAlignment(SwingConstants.CENTER);
		
		if(table.getColumnCount() >= 1) {
			table.getColumnModel().getColumn(0).setCellRenderer(center);
		}
	}
	
	public JTable getTable() {
		return table;
	}
	
	public JButton getBtnAdd() {
        return btnAdd;
    }

    public JButton getBtnEdit() {
        return btnEdit;
    }

    public JButton getBtnDelete() {
        return btnDelete;
    }
    
    public JButton getBtnPdf() {
    	return btnPdf;
    }
	
    public int getSelectedRow() {
    	return table.getSelectedRow();
    }
}









