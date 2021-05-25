package com.cg.mts.exceptions.handlers;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
//import com.cg.mts.entities.AdmissionCommiteeMember;
//import com.cg.mts.exceptions.AdmissionCommiteeMemberNotFoundException;

import com.cg.mts.entities.AdmissionCommiteeMember;
import com.cg.mts.exceptions.DataNotFoundException;

import com.cg.mts.exceptions.DuplicateDataException;
import com.cg.mts.exceptions.EmptyDataException;

@ControllerAdvice
public class ApplicationErrorHandler extends ResponseEntityExceptionHandler {

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {

		List<String> errorList = ex.getBindingResult().getFieldErrors().stream().map(fe -> fe.getDefaultMessage())
				.collect(Collectors.toList());

		Map<String, Object> errorBody = new LinkedHashMap<>();
		errorBody.put("errors", errorList);

		return new ResponseEntity<>(errorBody, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(DuplicateDataException.class)
	public ResponseEntity<?> handleDuplicateData(DuplicateDataException ex) {
		Map<String, Object> errorBody = new LinkedHashMap<>();

		errorBody.put("error", "DUPLICATE DATA EXISTS");
		errorBody.put("timestamp", LocalDateTime.now());
		errorBody.put("details", ex.getMessage());

		return new ResponseEntity<>(errorBody, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(EmptyDataException.class)
	public ResponseEntity<?> handleEmptyData(EmptyDataException ex) {
		Map<String, Object> errorBody = new LinkedHashMap<>();

		errorBody.put("error", "Empty database");
		errorBody.put("timestamp", LocalDateTime.now());
		errorBody.put("details", ex.getMessage());

		return new ResponseEntity<>(errorBody, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(DataNotFoundException.class)
	public ResponseEntity<?> handleDataNotFound(DataNotFoundException ex) {
		Map<String, Object> errorBody = new LinkedHashMap<>();

		errorBody.put("error", ex.getOperation() + " failed");
		errorBody.put("timestamp", LocalDateTime.now());
		errorBody.put("details", ex.getMessage());

		return new ResponseEntity<>(errorBody, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(BadCredentialsException.class)
	public ResponseEntity<?> handleBadCredentials(BadCredentialsException ex) {
		Map<String, Object> errorBody = new LinkedHashMap<>();

		errorBody.put("error", "Login failed");
		errorBody.put("timestamp", LocalDateTime.now());
		errorBody.put("details", ex.getMessage());

		return new ResponseEntity<>(errorBody, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity<?> handleConstraintViolationException(ConstraintViolationException ex,
			ServletWebRequest webRequest) throws IOException {
		Map<String, Object> errorBody = new LinkedHashMap<>();
		errorBody.put("error", "DUPLICATE DATA EXISTS");
		errorBody.put("timestamp", LocalDateTime.now());
		errorBody.put("details", "Staff Id exists with role STAFF");

		return new ResponseEntity<>(errorBody, HttpStatus.BAD_REQUEST);
	}

}