package com.cg.mts.exceptions;

@SuppressWarnings("serial")
public class DuplicateDataException extends RuntimeException {

	public DuplicateDataException(String msg) {
		super(msg);
	}
}
