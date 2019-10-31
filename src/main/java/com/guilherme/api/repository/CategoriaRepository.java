package com.guilherme.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.guilherme.api.model.Categorias;

public interface CategoriaRepository extends JpaRepository<Categorias, Integer> {

}