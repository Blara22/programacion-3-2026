package repository;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import config.DatabaseConnection;
import models.User;

public class UserRepository {

	public void save(User user) throws IOException {
		//TODO: Cambiar a conexión con base de datos
		List<User> users = getUsers();
		users.add(user);
		
	}
	
	public List<User> getUsers() throws IOException {
		
		List<User> users = new ArrayList<User>();
		
		try(
			Connection connection = DatabaseConnection.getConnection();
			Statement st = connection.createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM users"); 
		) {
			
			while(rs.next()) {
				
				User user = new User(
					rs.getInt("id"), 
					rs.getString("name"), 
					rs.getString("email"),
					rs.getString("country"),
					rs.getString("gender").charAt(0),
					rs.getString("description"),
					Arrays.asList(rs.getString("languages").split("\\|")),
					rs.getString("image_path"),
					rs.getString("role")
				);
				users.add(user);
			}
			
		}catch(SQLException ex ) {
			ex.printStackTrace();
		}
		
		return users;		
	}
	
	public boolean delete(int id) {
		
		String sql = "DELETE FROM users WHERE id = ?";
		
		try(Connection connection = DatabaseConnection.getConnection();
			PreparedStatement pst = connection.prepareStatement(sql)) {
			
			pst.setInt(1, id);
			int affectedRows = pst.executeUpdate();
			if(affectedRows > 0) {
				System.out.println("Se eliminó");
				return true;
			}
			
		}catch(SQLException ex) {
			ex.printStackTrace();
		}
		
		return false;
		
	}
	
	public void update(int index, User updatedUser) throws IOException {
		List<User> users = getUsers();
		//Actualiza para la tabla
		users.set(index, updatedUser);
		
		String sql = "UPDATE users SET name = ?, email = ?, country = ?,"
				+ " description = ?, languages = ?, gender = ?, role = ? "
				+ "WHERE id = ?";
		
		try (Connection connection = DatabaseConnection.getConnection();
				PreparedStatement pst = connection.prepareStatement(sql)) {
			
			pst.setString(1, updatedUser.getName());
			pst.setString(2, updatedUser.getEmail());
			pst.setString(3, updatedUser.getCountry());
			pst.setString(4, updatedUser.getDescription());
			pst.setString(5, String.join("|", updatedUser.getLanguages()));
			pst.setString(6, String.valueOf(updatedUser.getGender()));
			pst.setString(7, updatedUser.getRole());
			pst.setInt(8, updatedUser.getId());
			
			int affectedRows = pst.executeUpdate();
			
			if(affectedRows > 0) {
				System.out.println("Cambios guardados");
			}else {
				System.out.println("No se hicieron cambios");
			}
			
			
		}catch(SQLException ex) {
			ex.printStackTrace();
		}
	}
	
			
}











