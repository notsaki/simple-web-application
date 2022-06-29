package me.github.notsaki.userapplication.model;

import me.github.notsaki.userapplication.domain.entity.response.ResponseUserDto;
import me.github.notsaki.userapplication.entity.response.ResponseUserDtoEntity;
import me.github.notsaki.userapplication.entity.response.UserListItemDtoEntity;
import me.github.notsaki.userapplication.domain.model.Gender;
import me.github.notsaki.userapplication.domain.model.User;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;
import java.util.Optional;

@Entity
@Table(name = "users")
public class UserModel implements User {
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
	private WorkAddressModel workAddress = null;

	@OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "home_address_id", referencedColumnName = "id")
	@Nullable
	private HomeAddressModel homeAddress = null;

	protected UserModel() {
	}

	public UserModel(int id, String name, String surname, Gender gender, LocalDate birthdate, @Nullable WorkAddressModel workAddress, @Nullable HomeAddressModel homeAddress) {
		this.id = id;
		this.name = name;
		this.surname = surname;
		this.gender = gender;
		this.birthdate = birthdate;
		this.workAddress = workAddress;
		this.homeAddress = homeAddress;
	}

	public UserModel(int id, String name, String surname, Gender gender, LocalDate birthdate) {
		this.id = id;
		this.name = name;
		this.surname = surname;
		this.gender = gender;
		this.birthdate = birthdate;
	}

	public UserModel(String name, String surname, Gender gender, LocalDate birthdate, @Nullable WorkAddressModel workAddress, @Nullable HomeAddressModel homeAddress) {
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

	public Optional<WorkAddressModel> getWorkAddress() {
		return Optional.ofNullable(workAddress);
	}

	public void setWorkAddress(@Nullable WorkAddressModel workAddress) {
		this.workAddress = workAddress;
	}

	public Optional<HomeAddressModel> getHomeAddress() {
		return Optional.ofNullable(homeAddress);
	}

	public void setHomeAddress(HomeAddressModel homeAddress) {
		this.homeAddress = homeAddress;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof UserModel)) return false;
		UserModel user = (UserModel) o;
		return id == user.id && name.equals(user.name) && surname.equals(user.surname) && gender == user.gender && birthdate.equals(user.birthdate) && Objects.equals(workAddress, user.workAddress) && Objects.equals(homeAddress, user.homeAddress);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, name, surname, gender, birthdate, workAddress, homeAddress);
	}

	public ResponseUserDto toResponse() {
		return new ResponseUserDtoEntity(
				this.getId(),
				this.getName(),
				this.getSurname(),
				this.getGender(),
				this.getBirthdate(),
				this.getWorkAddress().map(WorkAddressModel::getAddress).orElse(null),
				this.getHomeAddress().map(HomeAddressModel::getAddress).orElse(null)
		);
	}

	public UserListItemDtoEntity toFullName() {
		return new UserListItemDtoEntity(
				this.getId(),
				this.getName(),
				this.getSurname()
		);
	}
}
