package me.github.notsaki.userapplication.domain.entity.error;

public enum ValidationType {
	LENGHT("lenght"),
	FORMAT("format");

	private String value;

	ValidationType(String value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return this.value;
	}
}
