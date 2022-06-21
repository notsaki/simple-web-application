package me.github.notsaki.userapplication.domain.model;

import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "home_addresses")
public class HomeAddress {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private int id;

	@Column
	private String address;

	protected HomeAddress() {
	}

	public HomeAddress(int id, String address) {
		this.id = id;
		this.address = address;
	}

	public HomeAddress(@Nullable String address) {
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

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof HomeAddress)) return false;
		HomeAddress that = (HomeAddress) o;
		return id == that.id && address.equals(that.address);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, address);
	}
}
