package com.guilherme.api.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class PedidoItens {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int idItem;
	@JoinColumn(name = "idPedido")
	@OneToOne
	private Pedidos idPedido;
	@JoinColumn(name = "idProduto")
	@OneToOne
	private Produtos idProduto;
	private int quantidade;
	private double valor;
	private double subtotal;

	public PedidoItens() {
	}

	public PedidoItens(Pedidos idPedido, Produtos idProduto, int quantidade, double valor, double subtotal) {
		this.idPedido = idPedido;
		this.idProduto = idProduto;
		this.quantidade = quantidade;
		this.valor = valor;
		this.subtotal = subtotal;
	}

	public int getIdItem() {
		return idItem;
	}

	public void setIdItem(int idItem) {
		this.idItem = idItem;
	}

	public Pedidos getIdPedido() {
		return idPedido;
	}

	public void setIdPedido(Pedidos idPedido) {
		this.idPedido = idPedido;
	}

	public Produtos getIdProduto() {
		return idProduto;
	}

	public void setIdProduto(Produtos idProduto) {
		this.idProduto = idProduto;
	}

	public long getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

	public double getSubtotal() {
		return subtotal;
	}

	public void setSubtotal(double subtotal) {
		this.subtotal = subtotal;
	}
}
