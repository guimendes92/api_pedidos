package com.guilherme.api.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.guilherme.api.model.Clientes;
import com.guilherme.api.repository.ClienteRepository;

@RestController
@RequestMapping("/cliente")
public class ClienteController {

	@Autowired
	private ClienteRepository repository;

	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getClientes(@RequestParam(value = "nome", required = false) String nome,
			@RequestParam(value = "email", required = false) String email) {
		List<Clientes> clientes = new ArrayList<Clientes>();

		if (nome != null) {
			clientes = repository.findByNome(nome);
		} else if (email != null) {
			clientes = repository.findByEmail(email);
		} else {
			clientes = repository.findAll();
		}

		return clientes.isEmpty() ? new ResponseEntity<String>("", HttpStatus.NOT_FOUND)
				: new ResponseEntity<List<Clientes>>(clientes, HttpStatus.OK);
	}

	@GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getCliente(@PathVariable("id") int id) {
		Optional<Clientes> optional = repository.findById(id);

		return optional.isPresent() ? new ResponseEntity<Clientes>(optional.get(), HttpStatus.OK)
				: new ResponseEntity<String>("", HttpStatus.NOT_FOUND);
	}

	@PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> createCliente(@RequestBody Clientes cliente) {
		Clientes c = repository.save(cliente);

		return c != null ? new ResponseEntity<Clientes>(c, HttpStatus.CREATED)
				: new ResponseEntity<String>("", HttpStatus.BAD_REQUEST);
	}

	@PatchMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> patchCliente(@RequestBody Clientes cliente) {
		Optional<Clientes> optional = repository.findById(cliente.getIdCliente());

		if (!optional.isPresent()) {
			return new ResponseEntity<String>("", HttpStatus.NOT_FOUND);
		}

		Clientes c = optional.get();
		c = repository.save(cliente);

		return c != null ? new ResponseEntity<Clientes>(c, HttpStatus.ACCEPTED)
				: new ResponseEntity<String>("", HttpStatus.BAD_REQUEST);
	}

	@PutMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> updateCliente(@RequestBody Clientes cliente) {
		Optional<Clientes> optional = repository.findById(cliente.getIdCliente());

		if (!optional.isPresent()) {
			return new ResponseEntity<String>("", HttpStatus.NOT_FOUND);
		}

		Clientes c = repository.save(cliente);

		return c != null ? new ResponseEntity<Clientes>(c, HttpStatus.ACCEPTED)
				: new ResponseEntity<String>("", HttpStatus.BAD_REQUEST);
	}

	@DeleteMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> deleteCliente(@RequestBody Clientes cliente) {
		repository.delete(cliente);

		return new ResponseEntity<String>("", HttpStatus.OK);
	}
}
