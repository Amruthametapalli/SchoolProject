package com.amrutha.hibernateTest.utils;

public enum StatusCodes {
	
	CREATED_DATABASE(1000,201,"Created"),
	INVALID_HEADERS(1001,400,"BadRequest"),
	DB_CONNECTION_FAILED(1002,500,"iNTERNAL SERVER ERROR"),
	DB_CONNECTION_LOST(1003,500,"INTERNAL SERVER ERROR");
	
	private String message;
	private int code;
	private int httpStatus;
	
	StatusCodes(int code,int httpStatus,String message){
		this.code=code;
		this.message=message;
		this.httpStatus=httpStatus;
	}
	
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public int getHttpStatus() {
		return httpStatus;
	}
	public void setHttpStatus(int httpStatus) {
		this.httpStatus = httpStatus;
	}
}
