package me.github.notsaki.userapplication.domain.data.error;

public interface ValidationInfo {

	String getTargetLocation();

	void setTargetLocation(String targetLocation);

	String getInstructions();

	void setInstructions(String instructions);

	boolean equals(Object o);

	int hashCode();
}
