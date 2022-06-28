package me.github.notsaki.userapplication.domain.entity.error;

import java.util.Objects;

public class ValidationInfo {
	private String targetLocation;
	private String instructions;

	public ValidationInfo() {
	}

	public ValidationInfo(String targetLocation, String instructions) {
		this.targetLocation = targetLocation;
		this.instructions = instructions;
	}

	public String getTargetLocation() {
		return targetLocation;
	}

	public void setTargetLocation(String targetLocation) {
		this.targetLocation = targetLocation;
	}

	public String getInstructions() {
		return instructions;
	}

	public void setInstructions(String instructions) {
		this.instructions = instructions;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof ValidationInfo)) return false;
		ValidationInfo that = (ValidationInfo) o;
		return targetLocation.equals(that.targetLocation) && instructions.equals(that.instructions);
	}

	@Override
	public int hashCode() {
		return Objects.hash(targetLocation, instructions);
	}
}
