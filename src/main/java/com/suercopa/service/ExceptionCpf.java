package com.suercopa.service;

public class ExceptionCpf extends Exception {

	private static final long serialVersionUID = 1L;
	
	public ExceptionCpf() {
        super("CPF informado já possui cadastro.");
    }
}
