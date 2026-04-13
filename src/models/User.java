package models;

import java.util.List;

public class User {
	
	private String name;
	private String email;
	private String country;
	private char gender;
	private String description;
	private List<String> languages;
	private String password;
	
	public User() {
		
	}
	
	public User(String email, String password) {
		this.email = email;
		this.password = password;
	}
	
	public User(String name, String email, String country, char gender, String description, List<String> languages) {
		super();
		this.name = name;
		this.email = email;
		this.country = country;
		this.gender = gender;
		this.description = description;
		this.languages = languages;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public char getGender() {
		return gender;
	}

	public void setGender(char gender) {
		this.gender = gender;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<String> getLanguages() {
		return languages;
	}

	public void setLanguages(List<String> languages) {
		this.languages = languages;
	}
	
	public String getPassword() {
		return password;
	}
	
	public String toString() {
		return "Nombre: " + name +
		           "\nEmail: " + email +
		           "\nPaís: " + country +
		           "\nGénero: " + gender +
		           "\nDescripción: " + description +
		           "\nLenguajes:\n" +
		           String.join("\n", languages);
	}
	
	
}
