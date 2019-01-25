package com.user.notesapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.user.notesapi.response.Response;

@ControllerAdvice
public class NoteServiceExceptionHandler {

	@ExceptionHandler(NoteException.class)
	public ResponseEntity<?> noteExceptionHandle(NoteException e)
	{
		Response response=new Response();
		response.setStatusCode(e.getErrorCode());
		response.setStatusMessage(e.getErrorMsg());
		return new ResponseEntity<Response>(response,HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<?> allExceptionHandle(Exception e)
	{
		Response response=new Response();
		response.setStatusCode(100);
		response.setStatusMessage(e.getMessage());
		return new ResponseEntity<Response>(response,HttpStatus.BAD_REQUEST);
	}
}
