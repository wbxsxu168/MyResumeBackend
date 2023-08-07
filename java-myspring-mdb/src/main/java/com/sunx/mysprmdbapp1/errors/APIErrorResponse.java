package com.sunx.mysprmdbapp1.errors;

import org.springframework.http.HttpStatus;
import java.util.Arrays;
import java.util.List;

/**
 * REST API Error response.
 */
public class APIErrorResponse {

	private HttpStatus status;
	private String message;
	private List<String> errors;

	public APIErrorResponse(HttpStatus status, String message, List<String> errors) {
		this.status = status;
		this.message = message;
		this.errors = errors;
	}

	public APIErrorResponse(HttpStatus status, String message, String error) {
		this.status = status;
		this.message = message;
		this.errors = Arrays.asList(error);
	}

	public HttpStatus getStatus() {
		return status;
	}

	public void setStatus(HttpStatus status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public List<String> getErrors() {
		return errors;
	}

	public void setErrors(List<String> errors) {
		this.errors = errors;
	}

	@Override
	public String toString() {
		return "ErrorResponse{" + "status='" + status + '\'' + ", message='" + message + '\'' + ", errors=" + errors
				+ '}';
	}
}