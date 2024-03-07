package br.com.eduardo.spring.arquitetura.arquiteturaspring.handler;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import br.com.eduardo.spring.arquitetura.arquiteturaspring.exception.NotFoundException;
import br.com.eduardo.spring.arquitetura.arquiteturaspring.exception.ResponseException;


@RestController
@RestControllerAdvice
public class ExceptionHandler extends ResponseEntityExceptionHandler{
	
	public ResponseEntity<ResponseException> handlerAllExceptions(Exception exception, WebRequest webRequest){
		ResponseException responseException = new ResponseException(exception.getMessage(), new Date(), webRequest.getDescription(false));
		
		return new ResponseEntity<>(responseException, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	public ResponseEntity<ResponseException> handlerNotFoundException(NotFoundException exception, WebRequest webRequest){
		ResponseException responseException = new ResponseException(exception.getMessage(), new Date(), webRequest.getDescription(false));
		
		return new ResponseEntity<>(responseException, HttpStatus.NOT_FOUND);
	}
}
