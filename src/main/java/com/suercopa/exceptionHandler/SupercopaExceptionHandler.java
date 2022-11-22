package com.suercopa.exceptionHandler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.suercopa.service.ExceptionCpf;

@ControllerAdvice
public class SupercopaExceptionHandler extends ResponseEntityExceptionHandler{

	
	@Autowired
	private MessageSource messageSource;
	
	
	
	
	@Override
	protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
			org.springframework.http.HttpHeaders headers, HttpStatus status, WebRequest request) {
		
		String mensagemUsuario = messageSource.getMessage("mensagem.invalida", null, LocaleContextHolder.getLocale());	
		String mensagemDesenvolvedor = ex.getCause().toString();
		List<Erros> erros = Arrays.asList(new Erros(mensagemUsuario, mensagemDesenvolvedor));
		
		
		return handleExceptionInternal(ex, erros, headers, HttpStatus.BAD_REQUEST, request);
	}
	
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			org.springframework.http.HttpHeaders headers, HttpStatus status, WebRequest request) {
		
		
		
		List<Erros> erros = criarListaErro(ex.getBindingResult());
		
		return handleExceptionInternal(ex, erros, headers, HttpStatus.BAD_REQUEST, request);
	}
	
	
	@ExceptionHandler(EmptyResultDataAccessException.class)
	public ResponseEntity<Object> handleEmptyResultDataAccessException(EmptyResultDataAccessException ex, WebRequest resquest) {
		
		String mensagemUsuario = messageSource.getMessage("recurso.nao-encontrado", null, LocaleContextHolder.getLocale());	
		String mensagemDesenvolvedor = ex.toString();
		List<Erros> erros = Arrays.asList(new Erros(mensagemUsuario, mensagemDesenvolvedor));
		
	return handleExceptionInternal(ex, erros, new HttpHeaders(), HttpStatus.NOT_FOUND, resquest);
	}
	
	
	
	
	@ExceptionHandler(DataIntegrityViolationException.class)
	public ResponseEntity<Object> handleDataIntegrityViolationException(DataIntegrityViolationException ex, WebRequest request) {
		
		
		String mensagemUsuario = messageSource.getMessage("recurso.operacao-nao-permitida", null, LocaleContextHolder.getLocale());	
		String mensagemDesenvolvedor = ex.toString();
		List<Erros> erros = Arrays.asList(new Erros(mensagemUsuario, mensagemDesenvolvedor));
		
		return handleExceptionInternal(ex, erros, new HttpHeaders(), HttpStatus.NOT_FOUND, request);
	}
	
	@ExceptionHandler({ExceptionCpf.class})
	public ResponseEntity<Erro> handleUnicidadeCpfException(ExceptionCpf e) {
		return new ResponseEntity<>(new Erro(e.getMessage()), HttpStatus.BAD_REQUEST);
	}
	
	
	
	public List<Erros> criarListaErro(BindingResult bindingResult) {
		List<Erros> erros = new ArrayList<>();
		
		
		for(FieldError fieldError : bindingResult.getFieldErrors()) {
		String mensagemUsuario = messageSource.getMessage(fieldError, LocaleContextHolder.getLocale());
		String mensagemDesenvolvedor = fieldError.toString();
		erros.add(new Erros(mensagemUsuario, mensagemDesenvolvedor));
		}
		
		return erros;
	}
	
	
	
	class Erro {
		private final String erro;

		public Erro(String erro) {
			this.erro = erro;
		}

		public String getErro() {
			return erro;
		}
	
	}
	
public static class Erros {
		
		private String mensagemUsuario;
		private String mensagemDesenvolvedor;
		
		public Erros(String mensagemUsuario, String mensagemDesenvolvedor) {
			super();
			this.mensagemUsuario = mensagemUsuario;
			this.mensagemDesenvolvedor = mensagemDesenvolvedor;
		}

		public String getMensagemUsuario() {
			return mensagemUsuario;
		}

		public String getMensagemDesenvolvedor() {
			return mensagemDesenvolvedor;
		}
		
		
		
		
	}
	
}
