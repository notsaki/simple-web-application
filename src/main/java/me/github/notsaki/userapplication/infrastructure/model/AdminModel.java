package me.github.notsaki.userapplication.infrastructure.model;

import me.github.notsaki.userapplication.domain.model.Admin;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "admins")
public class AdminModel implements Admin {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private int id;

	@Column(unique = true)
	private String username;

	@Column
	private String password;

	public AdminModel() {
	}

	public AdminModel(int id, String username, String password) {
		this.id = id;
		this.username = username;
		this.password = password;
	}

	public AdminModel(String username, String password) {
		this.username = username;
		this.password = password;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof AdminModel)) return false;
		AdminModel admin = (AdminModel) o;
		return id == admin.id && username.equals(admin.username) && password.equals(admin.password);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, username, password);
	}
}
