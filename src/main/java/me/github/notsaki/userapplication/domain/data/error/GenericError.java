package me.github.notsaki.userapplication.domain.data.error;

public interface GenericError<T> {
	T getContext();

	String getMessage();

	void setContext(T context);
	void setMessage(String message);
}
