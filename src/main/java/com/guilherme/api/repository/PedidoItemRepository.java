package com.guilherme.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;

import com.guilherme.api.model.PedidoItens;

public interface PedidoItemRepository extends JpaRepository<PedidoItens, Integer>, QueryByExampleExecutor<PedidoItens> {

	List<PedidoItens> findByIdPedido(int idPedido);
}