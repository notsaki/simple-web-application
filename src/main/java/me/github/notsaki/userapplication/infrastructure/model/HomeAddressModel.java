package me.github.notsaki.userapplication.infrastructure.model;

import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "home_addresses")
public class HomeAddressModel {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private int id;

	@Column
	private String address;

	protected HomeAddressModel() {
	}

	public HomeAddressModel(int id, String address) {
		this.id = id;
		this.address = address;
	}

	public HomeAddressModel(@Nullable String address) {
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
		if (!(o instanceof HomeAddressModel)) return false;
		HomeAddressModel that = (HomeAddressModel) o;
		return id == that.id && address.equals(that.address);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, address);
	}
}
