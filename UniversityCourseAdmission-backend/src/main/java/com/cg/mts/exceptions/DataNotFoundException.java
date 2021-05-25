package com.cg.mts.exceptions;

@SuppressWarnings("serial")
public class DataNotFoundException extends RuntimeException {

	private String operation;

	public DataNotFoundException(String op, String msg) {
		super(msg);
		this.operation = op;

	}

	public String getOperation() {
		return operation;
	}

}
