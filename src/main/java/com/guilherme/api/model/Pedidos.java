package com.guilherme.api.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class Pedidos {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int idPedido;
	private Date data;
	@JoinColumn(name = "id_cliente")
	@OneToOne
	private Clientes idCliente;
	private String status;
	private String sessao;

	public Pedidos() {
	}

	public Pedidos(Date data, Clientes idCliente, String status, String sessao) {
		this.data = data;
		this.idCliente = idCliente;
		this.status = status;
		this.sessao = sessao;
	}

	public int getIdPedido() {
		return idPedido;
	}

	public void setIdPedido(int idPedido) {
		this.idPedido = idPedido;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public Clientes getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(Clientes idCliente) {
		this.idCliente = idCliente;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getSessao() {
		return sessao;
	}

	public void setSessao(String sessao) {
		this.sessao = sessao;
	}
}