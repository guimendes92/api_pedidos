package com.guilherme.api.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Clientes {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int idCliente;
	private String nome;
	private String email;
	private String senha;
	private String rua;
	private String cidade;
	private String bairro;
	private String cep;
	private String estado;

	public Clientes() {
	}

	public Clientes(String nome, String email, String senha, String rua, String cidade, String bairro, String cep,
			String estado) {
		this.nome = nome;
		this.email = email;
		this.senha = senha;
		this.rua = rua;
		this.cidade = cidade;
		this.bairro = bairro;
		this.cep = cep;
		this.estado = estado;
	}

	public int getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(int idCliente) {
		this.idCliente = idCliente;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getRua() {
		return rua;
	}

	public void setRua(String rua) {
		this.rua = rua;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}
}