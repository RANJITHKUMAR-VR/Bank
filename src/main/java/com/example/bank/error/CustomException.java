package com.example.bank.error;
import com.example.bank.model.ErrorResponse;
public class CustomException extends Exception {
	private ErrorResponse errorResponse;
	public CustomException(ErrorResponse errorReponse){
		super(errorReponse.getMessage());
		this.errorResponse=errorReponse;
	}
	public ErrorResponse getErrorResponse() {
		return errorResponse;
	}
	public void setErrorResponse(ErrorResponse errorResponse) {
		this.errorResponse = errorResponse;
	}
	
}
