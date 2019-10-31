package com.guilherme.api.model;

public class ErrorMessage {

	private String message;
	private String classe;

	public ErrorMessage(String message, String classe) {
		super();
		this.message = message;
		this.classe = classe;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getClasse() {
		return classe;
	}

	public void setClasse(String classe) {
		this.classe = classe;
	}
}