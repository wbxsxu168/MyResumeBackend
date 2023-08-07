package com.sunx.mysprmdbapp1.errors;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Global exceptions handler.
 */
@ControllerAdvice
public class ExceptionsHandler {

	@ExceptionHandler(ApiException.class)
	public ResponseEntity<APIErrorResponse> handleApiException(ApiException ex) {
		APIErrorType errorType = ex.getAPIErrorType();
		APIErrorResponse errorResponse = new APIErrorResponse(errorType.getHttpStatusCode(), ex.getMessage(),
				ex.getMessage());
		return ResponseEntity.status(errorResponse.getStatus()).body(errorResponse);
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<APIErrorResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
		List<String> errors = ex.getBindingResult().getAllErrors().stream()
				.map(objectError -> objectError.getDefaultMessage()).collect(Collectors.toList());
		APIErrorResponse errorResponse = new APIErrorResponse(APIErrorType.IMPUT_VALIDATION_ERRORS.getHttpStatusCode(),
				APIErrorType.IMPUT_VALIDATION_ERRORS.getReason(), errors);
		return ResponseEntity.status(errorResponse.getStatus()).body(errorResponse);
	}

}