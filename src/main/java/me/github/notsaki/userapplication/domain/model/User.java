package me.github.notsaki.userapplication.domain.model;

import me.github.notsaki.userapplication.domain.entity.response.ResponseUserDto;
import me.github.notsaki.userapplication.domain.entity.response.UserListItemDto;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;
import java.util.Optional;

@Entity
@Table(name = "users")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private int id;

	@Column(nullable = false)
	private String name;

	@Column(nullable = false)
	private String surname;

	@Enumerated
	@Column(nullable = false)
	private Gender gender;

	@Column(nullable = false)
	private LocalDate birthdate;

	@OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "work_address_id", referencedColumnName = "id")
	@Nullable
	private WorkAddress workAddress = null;

	@OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "home_address_id", referencedColumnName = "id")
	@Nullable
	private HomeAddress homeAddress = null;

	protected User() {
	}

	public User(int id, String name, String surname, Gender gender, LocalDate birthdate, @Nullable WorkAddress workAddress, @Nullable HomeAddress homeAddress) {
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

	public User(String name, String surname, Gender gender, LocalDate birthdate, @Nullable WorkAddress workAddress, @Nullable HomeAddress homeAddress) {
		this.name = name;
		this.surname = surname;
		this.gender = gender;
		this.birthdate = birthdate;
		this.workAddress = workAddress;
		this.homeAddress = homeAddress;
	}

	public int getId() {
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

	public Optional<WorkAddress> getWorkAddress() {
		return Optional.ofNullable(workAddress);
	}

	public void setWorkAddress(@Nullable WorkAddress workAddress) {
		this.workAddress = workAddress;
	}

	public Optional<HomeAddress> getHomeAddress() {
		return Optional.ofNullable(homeAddress);
	}

	public void setHomeAddress(HomeAddress homeAddress) {
		this.homeAddress = homeAddress;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof User)) return false;
		User user = (User) o;
		return id == user.id && name.equals(user.name) && surname.equals(user.surname) && gender == user.gender && birthdate.equals(user.birthdate) && Objects.equals(workAddress, user.workAddress) && Objects.equals(homeAddress, user.homeAddress);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, name, surname, gender, birthdate, workAddress, homeAddress);
	}

	public ResponseUserDto toResponse() {
		return new ResponseUserDto(
				this.getId(),
				this.getName(),
				this.getSurname(),
				this.getGender(),
				this.getBirthdate(),
				this.getWorkAddress().map(WorkAddress::getAddress).orElse(null),
				this.getHomeAddress().map(HomeAddress::getAddress).orElse(null)
		);
	}

	public UserListItemDto toFullName() {
		return new UserListItemDto(
				this.getId(),
				this.getName(),
				this.getSurname()
		);
	}
}
