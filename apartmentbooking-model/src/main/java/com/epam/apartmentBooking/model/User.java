package com.epam.apartmentBooking.model;

public class User {

	private Long id;
	private String name;
	private String surname;
	private String email;
	private String password;

	public User() {
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	/*
	 * @Override public boolean equals(Object obj) { if (this == obj) { return
	 * true; } if (obj == null || getClass() != obj.getClass()) { return false;
	 * }
	 * 
	 * User user = (User) obj;
	 * 
	 * return this.id == user.id && this.name.equals(user.name) &&
	 * this.surname.equals(user.surname) && this.email.equals(user.email) &&
	 * this.password.equals(user.password);
	 * 
	 * }
	 * 
	 * @Override public int hashCode() { return
	 * Objects.hash(id,name,surname,email,password); }
	 */

	@Override
	public String toString() {
		return "User{" + "id=" + id + ", name='" + name + '\'' + ", surname='" + surname + '\'' + ", email='" + email + '\'' + ", password='" + password + '\'' + '}';
	}
}
