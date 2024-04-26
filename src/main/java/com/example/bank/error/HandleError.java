package com.example.bank.error;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.support.WebExchangeBindException;
import org.springframework.web.reactive.resource.NoResourceFoundException;

@RestControllerAdvice
public class HandleError {
	  @ExceptionHandler(WebExchangeBindException.class)
	    public ResponseEntity<List<String>> handleException(WebExchangeBindException e) {
	        var errors = e.getBindingResult()
	                .getAllErrors()
	                .stream()
	                .map(DefaultMessageSourceResolvable::getDefaultMessage)
	                .collect(Collectors.toList());
	        return ResponseEntity.badRequest().body(errors);
	    }
	  @ExceptionHandler(CustomException.class)
	    public ResponseEntity<HashMap<String, String>> handleUserNotFoundException(CustomException ex) {
		  HashMap<String,String>error=new HashMap<>();
		  error.put("Message", ex.getMessage());
		  error.put("Status",HttpStatus.NOT_FOUND.toString() );
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
	    }
	  @ExceptionHandler(NoResourceFoundException.class)
	    public ResponseEntity<String> handleNoResourceFoundException(NoResourceFoundException ex) {
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
	    }
	  @ExceptionHandler(Exception.class)
	  public ResponseEntity<String> handleException(Exception ex) {
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
	    }
}
