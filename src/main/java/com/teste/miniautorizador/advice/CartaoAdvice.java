package com.teste.miniautorizador.advice;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.teste.miniautorizador.exception.CartaoJaExisteException;
import com.teste.miniautorizador.exception.CartaoSemSaldoException;
import com.teste.miniautorizador.exception.TransacaoAutorizacaoBarradaException;

@ControllerAdvice
public class CartaoAdvice extends ResponseEntityExceptionHandler {

	@ResponseBody
	@ExceptionHandler(CartaoJaExisteException.class)
	@ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
	String cartaoJaExisteHandler(CartaoJaExisteException ex) {
		return ex.getMessage();
	}

	@ExceptionHandler(CartaoSemSaldoException.class)
	protected ResponseEntity<Object> cartaoSemSaldoHandle(RuntimeException ex, WebRequest request) {
		String bodyOfResponse = "";
		return handleExceptionInternal(ex, bodyOfResponse, new HttpHeaders(), HttpStatus.NOT_FOUND, request);
	}
	
	@ResponseBody
	@ExceptionHandler(TransacaoAutorizacaoBarradaException.class)
	@ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
	String transacaoAutorizacaoBarradaHandler(TransacaoAutorizacaoBarradaException ex) {
		return ex.getMessage();
	}

}
