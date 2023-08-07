package com.sunx.mysprmdbapp1.errors;

import org.springframework.http.HttpStatus;

/**
 * REST API Error types enumerate.
 */
public enum APIErrorType {

	IMGRECORD_NOT_FOUND("Image records not found: %s", HttpStatus.NOT_FOUND),
	IMPUT_VALIDATION_ERRORS("Imput data validation errors", HttpStatus.BAD_REQUEST),
	JWT_VALIDATION_ERRORS("Invalid JWT token", HttpStatus.BAD_REQUEST),
	JWT_EXPIRED_ERRORS("JWT token expired", HttpStatus.BAD_REQUEST),
	JWT_UNSUPPORT_ERRORS("JWT token unsupported", HttpStatus.BAD_REQUEST),
	JWT_CLAIMS_EMPTY_ERRORS("JWT claims string being empty!", HttpStatus.BAD_REQUEST),
	USERNAMEOREMAIL_CONFLICT_ERRORS("Username or email already exists", HttpStatus.BAD_REQUEST);

	private String reason;
	private HttpStatus httpStatusCode;

	APIErrorType(String reason, HttpStatus status) {
		this.reason = reason;
		this.httpStatusCode = status;
	}

	public String getReason() {
		return reason;
	}

	public HttpStatus getHttpStatusCode() {
		return httpStatusCode;
	}

	@Override
	public String toString() {
		return "ErrorType{" + "reason='" + reason + '\'' + ", status=" + httpStatusCode + "} " + super.toString();
	}

}
 