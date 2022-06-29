package me.github.notsaki.userapplication.domain.entity.error;

public interface GenericError<T> {
	T getContext();

	String getMessage();
}
