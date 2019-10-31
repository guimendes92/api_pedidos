package com.guilherme.api.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class Produtos {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int idProduto;
	@JoinColumn(name = "id_categoria")
	@OneToOne
	private Categorias idCategoria;
	private String produto;
	private double preco;
	private int quantidade;
	private String descricao;
	private String foto;

	public Produtos() {
	}

	public Produtos(Categorias idCategoria, String produto, double preco, int quantidade, String descricao,
			String foto) {
		this.idCategoria = idCategoria;
		this.produto = produto;
		this.preco = preco;
		this.quantidade = quantidade;
		this.descricao = descricao;
		this.foto = foto;
	}

	public int getIdProduto() {
		return idProduto;
	}

	public void setIdProduto(int idProduto) {
		this.idProduto = idProduto;
	}

	public Categorias getIdCategoria() {
		return idCategoria;
	}

	public void setIdCategoria(Categorias idCategoria) {
		this.idCategoria = idCategoria;
	}

	public String getProduto() {
		return produto;
	}

	public void setProduto(String produto) {
		this.produto = produto;
	}

	public double getPreco() {
		return preco;
	}

	public void setPreco(double preco) {
		this.preco = preco;
	}

	public long getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getFoto() {
		return foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}
}