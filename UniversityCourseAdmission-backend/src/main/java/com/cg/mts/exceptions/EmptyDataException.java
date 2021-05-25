package com.cg.mts.exceptions;

@SuppressWarnings("serial")
public class EmptyDataException extends RuntimeException {

	public EmptyDataException(String msg) {
		super(msg);
	}
}
