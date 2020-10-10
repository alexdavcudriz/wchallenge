package com.wolox.wchallenge.commons.exceptions;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.context.request.async.AsyncRequestTimeoutException;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.wolox.wchallenge.commons.dtos.ResponseDTO;
import com.wolox.wchallenge.commons.dtos.ResultDTO;

@ControllerAdvice
public class GenericExceptionHandler extends ResponseEntityExceptionHandler {

	private static final Logger log = LoggerFactory.getLogger(GenericExceptionHandler.class);

	@ExceptionHandler(Exception.class)
	public ResponseEntity<Object> handleAnyException(Exception ex, WebRequest request) {
		HttpHeaders headers = new HttpHeaders();
		ResultDTO result = buildDefaultExceptionBody("C0010", ex);
		result.getDetails().add("Opps! something went wrong. "
				+ "It seems there is a problem with the resource you're looking for, and it cannot be desplayed.");
		ResponseDTO response = new ResponseDTO(result);

		return new ResponseEntity<>(response, headers, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(BusinessException.class)
	public ResponseEntity<Object> handleBusinessException(BusinessException ex, WebRequest request) {
		HttpHeaders headers = new HttpHeaders();
		ResultDTO result = buildDefaultExceptionBody("C0011", ex);
		ResponseDTO response = new ResponseDTO(result, ex.getMessage());

		return new ResponseEntity<>(response, headers, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(NotFoundException.class)
	public ResponseEntity<Object> handleNotFoundException(NotFoundException ex, WebRequest request) {
		HttpHeaders headers = new HttpHeaders();
		ResultDTO result = buildDefaultExceptionBody("C0011", ex);
		ResponseDTO response = new ResponseDTO(result, ex.getMessage());

		return new ResponseEntity<>(response, headers, HttpStatus.NOT_FOUND);
	}

	@Override
	public ResponseEntity<Object> handleHttpMediaTypeNotSupported(HttpMediaTypeNotSupportedException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		ResultDTO result = buildDefaultExceptionBody("C0001", ex);
		result.getDetails().add("We only consume application/json;charset=utf-8 media type.");
		ResponseDTO response = new ResponseDTO(result);

		return new ResponseEntity<>(response, headers, status);
	}

	@Override
	public ResponseEntity<Object> handleHttpMediaTypeNotAcceptable(HttpMediaTypeNotAcceptableException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		ResultDTO result = buildDefaultExceptionBody("C0002", ex);
		result.getDetails().add("We only produce application/json;charset=utf-8 media type.");
		ResponseDTO response = new ResponseDTO(result);

		return new ResponseEntity<>(response, headers, status);
	}

	@Override
	public ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		ResultDTO result = buildDefaultExceptionBody("C0003", ex);
		result.getDetails().add("Opps! Are you lost? Check the method you're trying use.");
		ResponseDTO response = new ResponseDTO(result);

		return new ResponseEntity<>(response, headers, status);
	}

	@Override
	public ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException ex, HttpHeaders headers,
			HttpStatus status, WebRequest request) {
		ResultDTO result = buildDefaultExceptionBody("C0004", ex);
		result.getDetails().add("Opps! Are you lost? Check the resource you're trying to access.");
		ResponseDTO response = new ResponseDTO(result);

		return new ResponseEntity<>(response, headers, status);
	}

	@Override
	public ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers,
			HttpStatus status, WebRequest request) {
		ResultDTO result = buildDefaultExceptionBody("C0005", ex);
		result.getDetails().add("One or more arguments are missing. Please check the request.");
		List<ObjectError> allErrors = ex.getBindingResult().getAllErrors();
		for (ObjectError objectError : allErrors) {
			result.getDetails().add(objectError.getDefaultMessage());
		}
		ResponseDTO response = new ResponseDTO(result);

		return new ResponseEntity<>(response, headers, status);
	}

	@Override
	public ResponseEntity<Object> handleAsyncRequestTimeoutException(AsyncRequestTimeoutException ex,
			HttpHeaders headers, HttpStatus status, WebRequest webRequest) {
		ResultDTO result = buildDefaultExceptionBody("C0006", ex);
		result.getDetails().add("Timeout reached. Please try again in a moment.");
		ResponseDTO response = new ResponseDTO(result);

		return new ResponseEntity<>(response, headers, status);
	}

	private static ResultDTO buildDefaultExceptionBody(String code, Exception ex) {
		log.error(ex.getLocalizedMessage());
		List<String> details = new ArrayList<>();
		details.add(ex.getLocalizedMessage());

		return new ResultDTO(LocalDateTime.now(), code, details);
	}
}
