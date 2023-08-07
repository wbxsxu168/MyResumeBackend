package com.sunx.mysprmdbapp1.errors;

/**
 * REST API exception.
 */
public class ApiException extends RuntimeException {

	private static final long serialVersionUID = 2202200754956912554L;
	private APIErrorType ErrorType;
    private String messageParam;

    public ApiException(APIErrorType ErrorType, String messageParam) {
        super(getMessageFromParam(ErrorType, messageParam));
        this.ErrorType = ErrorType;
        this.messageParam = messageParam;
    }

    public APIErrorType getAPIErrorType() {
        return ErrorType;
    }

    public String getMessageParam() {
        return messageParam;
    }

    private static String getMessageFromParam(APIErrorType APIErrorType, String messageParam) {
        return String.format(APIErrorType.getReason(), messageParam);
    }

    @Override
    public String toString() {
        return "RestApiException{" +
                "APIErrorType=" + ErrorType +
                ", messageParam='" + messageParam + '\'' +
                "} " + super.toString();
    }

}