package me.github.notsaki.userapplication.domain.model;

import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "users")
public class User {
	@Id
	@GeneratedValue
	@Column(name = "id")
	private int id;

	@Column(name = "name", nullable = false)
	private String name;

	@Column(name = "surname", nullable = false)
	private String surname;

	@Enumerated
	@Column(name = "gender", nullable = false)
	private Gender gender;

	@Column(name = "birthdate", nullable = false)
	private LocalDate birthdate;

	@Column(name = "work_address")
	@Nullable
	private String workAddress = null;

	@Column(name = "home_address")
	@Nullable
	private String homeAddress = null;

	protected User() {
	}

	public User(int id, String name, String surname, Gender gender, LocalDate birthdate, @Nullable String workAddress, @Nullable String homeAddress) {
		this.id = id;
		this.name = name;
		this.surname = surname;
		this.gender = gender;
		this.birthdate = birthdate;
		this.workAddress = workAddress;
		this.homeAddress = homeAddress;
	}

	public User(int id, String name, String surname, Gender gender, LocalDate birthdate) {
		this.id = id;
		this.name = name;
		this.surname = surname;
		this.gender = gender;
		this.birthdate = birthdate;
	}

	public User(String name, String surname, Gender gender, LocalDate birthdate) {
		this.name = name;
		this.surname = surname;
		this.gender = gender;
		this.birthdate = birthdate;
	}

	public User(String name, String surname, Gender gender, LocalDate birthdate, String workAddress, String homeAddress) {
		this.name = name;
		this.surname = surname;
		this.gender = gender;
		this.birthdate = birthdate;
		this.workAddress = workAddress;
		this.homeAddress = homeAddress;
	}

	public Integer getId() {
		return id;
	}

	public void setId(int id) {
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

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public LocalDate getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(LocalDate birthdate) {
		this.birthdate = birthdate;
	}

	public String getWorkAddress() {
		return workAddress;
	}

	public void setWorkAddress(String workAddress) {
		this.workAddress = workAddress;
	}

	public String getHomeAddress() {
		return homeAddress;
	}


	public void setHomeAddress(String homeAddress) {
		this.homeAddress = homeAddress;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		User user = (User) o;
		return id == user.id && name.equals(user.name) && surname.equals(user.surname) && gender == user.gender && birthdate.equals(user.birthdate) && Objects.equals(workAddress, user.workAddress) && Objects.equals(homeAddress, user.homeAddress);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, name, surname, gender, birthdate, workAddress, homeAddress);
	}

}
