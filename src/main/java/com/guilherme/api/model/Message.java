package com.guilherme.api.model;

public enum Message {

	NOT_FOUND("nao encontrado"),
	UNAUTHORIZED("nao autorizado"),
	BAD_REQUEST("nao foi possivel retornar");

	private String message;
	
	Message(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}