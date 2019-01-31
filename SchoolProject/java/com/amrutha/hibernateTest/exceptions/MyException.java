package com.amrutha.hibernateTest.exceptions;

import com.amrutha.hibernateTest.utils.StatusCodes;

public class MyException extends Exception{
	private String errorMessage;
	private int code;
	private StatusCodes statusCodes;
	
	public MyException(int code,String message){
		this.code=code;
		this.errorMessage=message;
	}
	public MyException(StatusCodes statusCode) {
		this.statusCodes=statusCode;
		this.code=statusCode.getHttpStatus();
		this.errorMessage=statusCode.getMessage();
	}
	public String getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(String message) {
		this.errorMessage = message;
	}
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
}
