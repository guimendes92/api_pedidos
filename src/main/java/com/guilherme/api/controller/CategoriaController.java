package com.guilherme.api.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.guilherme.api.model.Categorias;
import com.guilherme.api.model.ErrorMessage;
import com.guilherme.api.model.Message;
import com.guilherme.api.repository.CategoriaRepository;

@RestController
@RequestMapping("/categoria")
public class CategoriaController {

	@Autowired
	private CategoriaRepository repository;

	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getCategotias() {
		List<Categorias> categorias = repository.findAll();

		return categorias.isEmpty() ? new ResponseEntity<ErrorMessage>(
				new ErrorMessage(Categorias.class.getSimpleName(), Message.NOT_FOUND.getMessage()), HttpStatus.NOT_FOUND)
				: new ResponseEntity<List<Categorias>>(categorias, HttpStatus.OK);
	}

	@GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getCategoria(@PathVariable("id") int id) {
		Optional<Categorias> optional = repository.findById(id);

		return optional.isPresent() ? new ResponseEntity<Categorias>(optional.get(), HttpStatus.OK)
				: new ResponseEntity<ErrorMessage>(
						new ErrorMessage(Categorias.class.getSimpleName(), Message.NOT_FOUND.getMessage()),
						HttpStatus.NOT_FOUND);
	}
}