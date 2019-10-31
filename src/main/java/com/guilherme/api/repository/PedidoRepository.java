package com.guilherme.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;

import com.guilherme.api.model.Pedidos;

public interface PedidoRepository extends JpaRepository<Pedidos, Integer>, QueryByExampleExecutor<Pedidos> {

	List<Pedidos> findByStatus(String status);
}