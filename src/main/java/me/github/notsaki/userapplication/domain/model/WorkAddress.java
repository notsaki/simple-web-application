package me.github.notsaki.userapplication.domain.model;

import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "work_addresses")
public class WorkAddress {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private int id;

	@Column
	private String address;

	@OneToOne(mappedBy = "workAddress")
	private User user;

	protected WorkAddress() {
	}

	public WorkAddress(int id, String address, User user) {
		this.id = id;
		this.address = address;
		this.user = user;
	}

	public WorkAddress(@Nullable String address) {
		this.address = address;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}


	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof WorkAddress)) return false;
		WorkAddress that = (WorkAddress) o;
		return id == that.id && address.equals(that.address) && user.equals(that.user);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, address, user);
	}
}
