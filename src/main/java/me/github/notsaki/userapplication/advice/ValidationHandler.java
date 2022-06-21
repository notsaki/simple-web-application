package me.github.notsaki.userapplication.advice;

import me.github.notsaki.userapplication.domain.entity.ValidationInfo;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.List;

@ControllerAdvice
public class ValidationHandler extends ResponseEntityExceptionHandler {

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(
			MethodArgumentNotValidException exception,
			HttpHeaders headers,
			HttpStatus status,
			WebRequest request
	) {
		List<ValidationInfo> errors = exception
				.getBindingResult()
				.getAllErrors()
				.stream()
				.map(error -> {
					String target = ((FieldError) error).getField();
					String message = error.getDefaultMessage();
					return new ValidationInfo(target, message);
				})
				.toList();

		return new ResponseEntity<Object>(errors, HttpStatus.UNPROCESSABLE_ENTITY);
	}
}
