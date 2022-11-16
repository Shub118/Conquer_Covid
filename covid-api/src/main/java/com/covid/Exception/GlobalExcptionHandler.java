package com.covid.Exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import com.covid.Model.MyErrorDetails;

@ControllerAdvice
public class GlobalExcptionHandler {
	
	@ExceptionHandler(UserException.class)
	public ResponseEntity<MyErrorDetails> userExcHandler(UserException ue, WebRequest req){
		
		return new ResponseEntity<>(new MyErrorDetails(ue.getMessage(),req.getDescription(false),LocalDateTime.now()),HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(CenterException.class)
	public ResponseEntity<MyErrorDetails> CenterExcHandler(CenterException ue, WebRequest req){
		
		return new ResponseEntity<>(new MyErrorDetails(ue.getMessage(),req.getDescription(false),LocalDateTime.now()),HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(VaccineInventoryException.class)
	public ResponseEntity<MyErrorDetails> VaccineInventoryException(VaccineInventoryException ue, WebRequest req){
		
		return new ResponseEntity<>(new MyErrorDetails(ue.getMessage(),req.getDescription(false),LocalDateTime.now()),HttpStatus.BAD_REQUEST);
	}
}
