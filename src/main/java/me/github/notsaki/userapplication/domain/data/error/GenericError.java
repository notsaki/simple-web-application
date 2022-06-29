package me.github.notsaki.userapplication.domain.data.error;

public interface GenericError<T> {
	T getContext();

	String getMessage();
}
