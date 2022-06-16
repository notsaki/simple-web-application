package me.github.notsaki.userapplication.domain.model;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;
import java.util.Optional;

@Entity
@Table(name = "user")
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
	private Date birthdate;

	@Column(name = "work_address")
	private String workAddress = null;

	@Column(name = "home_address")
	private String homeAddress = null;

	protected User() {
	}

	public User(int id, String name, String surname, Gender gender, Date birthdate, Optional<String> workAddress, Optional<String> homeAddress) {
		this.id = id;
		this.name = name;
		this.surname = surname;
		this.gender = gender;
		this.birthdate = birthdate;
		this.workAddress = workAddress.orElse(null);
		this.homeAddress = homeAddress.orElse(null);
	}

	public User(int id, String name, String surname, Gender gender, Date birthdate) {
		this.id = id;
		this.name = name;
		this.surname = surname;
		this.gender = gender;
		this.birthdate = birthdate;
	}

	public User(String name, String surname, Gender gender, Date birthdate) {
		this.name = name;
		this.surname = surname;
		this.gender = gender;
		this.birthdate = birthdate;
	}

	public User(String name, String surname, Gender gender, Date birthdate, Optional<String> workAddress, Optional<String> homeAddress) {
		this.name = name;
		this.surname = surname;
		this.gender = gender;
		this.birthdate = birthdate;
	}

	public Optional<Integer> getId() {
		return Optional.ofNullable(id);
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

	public Date getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(Date birthdate) {
		this.birthdate = birthdate;
	}

	public Optional<String> getWorkAddress() {
		return Optional.ofNullable(workAddress);
	}

	public void setWorkAddress(Optional<String> workAddress) {
		this.workAddress = workAddress.orElse(null);
	}

	public void setWorkAddress(String workAddress) {
		this.workAddress = workAddress;
	}

	public Optional<String> getHomeAddress() {
		return Optional.ofNullable(homeAddress);
	}

	public void setHomeAddress(Optional<String> homeAddress) {
		this.homeAddress = homeAddress.orElse(null);
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
