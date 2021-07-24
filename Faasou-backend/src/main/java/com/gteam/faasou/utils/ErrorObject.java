package com.gteam.faasou.utils;

/**
 * 
 * @author JAGHO Brel
 *
 */
public class ErrorObject {

	private int errorCode;
	private String errorText;

	public ErrorObject() {

	}

	public ErrorObject(int errorCode, String errorText) {
		super();
		this.errorCode = errorCode;
		this.errorText = errorText;
	}

	public int getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorText() {
		return errorText;
	}

	public void setErrorText(String errorText) {
		this.errorText = errorText;
	}
}
