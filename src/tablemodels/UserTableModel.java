package tablemodels;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import models.User;

public class UserTableModel extends AbstractTableModel{

	private List<User> users;
	
	private final String[] columns = {
		"Nombre",
		"Email",
		"País",
		"Género",
		"Lenguajes"
	};
	
	public UserTableModel(List<User> users) {
		this.users = users;
	}
	
	@Override
	public int getRowCount() {
		return users.size();
	}

	@Override
	public int getColumnCount() {
		return columns.length;
	}
	
	@Override
	public String getColumnName(int column) {
		return columns[column];
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		
		User user = users.get(rowIndex);
		
		switch(columnIndex) {
		case 0:
			return user.getName();
		case 1:
			return user.getEmail();
		case 2:
			return user.getCountry();
		case 3:
			return user.getGender();
		case 4:
			return String.join(", ", user.getLanguages());
		}
		
		return null;
	}
	
	public User getUserAt(int row) {
		return users.get(row);
	}
	
	public void setUsers(List<User> users) {
		this.users = users;
		fireTableDataChanged();
	}
	
	/* Estos métodos permiten modificar una sola fila al momento de añadir, editar o eliminar un usuario.
	 * Deberán llamarlos en el constructor cuando hacen cada operación. Ya les puse el ejemplo con editar y
	 * eliminar.
	 */
	public void removeRow(int row) {
		users.remove(row);
		fireTableRowsDeleted(row, row);
	}
	
	public void addRow(User user) {
		int row = users.size();
		users.add(user);
		fireTableRowsInserted(row, row);
	}
	
	public void updateRow(int row, User user) {
		users.set(row, user);
		fireTableRowsUpdated(row, row);
	}
	
}
