package com.nagp.as.exception;

/**
 * Custom exception class for aggregator-service Contains an enum to define
 * error code, error message and http status.
 * 
 * @author santoshkumar02
 *
 */
public class ASException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	private ASErrEnum authErrEnum;

	public ASException(ASErrEnum authErrEnum) {
		super(authErrEnum.getErrorCode() + ":" + authErrEnum.getErrorMsg());
		this.authErrEnum = authErrEnum;
	}

	public ASException(ASErrEnum authErrEnum, Throwable cause) {
		super(authErrEnum.getErrorCode() + ":" + authErrEnum.getErrorMsg(), cause);
		this.authErrEnum = authErrEnum;
	}

	public ASErrEnum getAuthErrEnum() {
		return authErrEnum;
	}

	public void setAuthErrEnum(ASErrEnum authErrEnum) {
		this.authErrEnum = authErrEnum;
	}

}
