package me.github.notsaki.userapplication.controlleradvice;

import me.github.notsaki.userapplication.domain.entity.error.ValidationInfo;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.List;

/**
 * Handler for request body validation error.
 */
@ControllerAdvice
public class ValidationHandler extends ResponseEntityExceptionHandler {

	/**
	 * @return A list of objects with the target properties that is invalid and instructions on how to fix them.
	 */
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(
			MethodArgumentNotValidException exception,
			HttpHeaders headers,
			HttpStatus status,
			WebRequest request
	) {
		List<ValidationInfo> errors = exception
				.getBindingResult()
				.getFieldErrors()
				.stream()
				.map(error -> {
					String target = error.getField();
					String message = error.getDefaultMessage();
					return new ValidationInfo(target, message);
				})
				.toList();

		return new ResponseEntity<Object>(errors, HttpStatus.UNPROCESSABLE_ENTITY);
	}
}
