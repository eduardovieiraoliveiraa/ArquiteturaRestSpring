package br.com.eduardo.spring.arquitetura.arquiteturaspring.exception;

import java.io.Serializable;
import java.util.Date;

public class ResponseException implements Serializable {

	private static final long serialVersionUID = 1L;

	private String message;

	private Date data;

	private String details;

	public ResponseException(String message, Date data, String details) {
		super();
		this.message = message;
		this.data = data;
		this.details = details;
	}

	public String getMessage() {
		return message;
	}

	public Date getData() {
		return data;
	}

	public String getDetails() {
		return details;
	}
}