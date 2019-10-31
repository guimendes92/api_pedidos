package com.guilherme.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;

import com.guilherme.api.model.Produtos;

public interface ProdutoRepository extends JpaRepository<Produtos, Integer>, QueryByExampleExecutor<Produtos> {

	List<Produtos> findByIdCategoria(int idCategoria);

	List<Produtos> findByProduto(String produto);
}