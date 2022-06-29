package me.github.notsaki.userapplication.model;

import me.github.notsaki.userapplication.domain.model.WorkAddress;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "work_addresses")
public class WorkAddressModel implements WorkAddress {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private int id;

	@Column
	private String address;

	protected WorkAddressModel() {
	}

	public WorkAddressModel(int id, String address) {
		this.id = id;
		this.address = address;
	}

	public WorkAddressModel(@Nullable String address) {
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
		if (!(o instanceof WorkAddressModel)) return false;
		WorkAddressModel that = (WorkAddressModel) o;
		return id == that.id && address.equals(that.address);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, address);
	}
}
