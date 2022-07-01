package me.github.notsaki.userapplication.domain.exception;

import me.github.notsaki.userapplication.domain.data.error.ValidationInfo;

import java.util.List;

public class ValidationException extends Exception {
	private final List<ValidationInfo> validationInfo;

	public ValidationException(List<ValidationInfo> validationInfo) {
		this.validationInfo = validationInfo;
	}

	public List<ValidationInfo> getValidationInfo() {
		return validationInfo;
	}
}
