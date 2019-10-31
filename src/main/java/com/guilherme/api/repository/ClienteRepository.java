package com.guilherme.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;

import com.guilherme.api.model.Clientes;

public interface ClienteRepository extends JpaRepository<Clientes, Integer>, QueryByExampleExecutor<Clientes> {

	List<Clientes> findByNome(String nome);

	List<Clientes> findByEmail(String email);
}