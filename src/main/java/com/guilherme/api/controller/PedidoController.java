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

import com.guilherme.api.model.Pedidos;
import com.guilherme.api.repository.PedidoRepository;

@RestController
@RequestMapping("/pedido")
public class PedidoController {

	@Autowired
	private PedidoRepository repository;

	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getPedidos(@RequestParam(value = "status", required = false) String status) {
		List<Pedidos> pedidos = new ArrayList<Pedidos>();

		if (status != null) {
			pedidos = repository.findByStatus(status);
		} else {
			pedidos = repository.findAll();
		}

		return pedidos.isEmpty() ? new ResponseEntity<String>("", HttpStatus.NOT_FOUND)
				: new ResponseEntity<List<Pedidos>>(pedidos, HttpStatus.OK);
	}

	@GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getPedido(@PathVariable("id") int id) {
		Optional<Pedidos> optional = repository.findById(id);

		return optional.isPresent() ? new ResponseEntity<Pedidos>(optional.get(), HttpStatus.OK)
				: new ResponseEntity<String>("", HttpStatus.NOT_FOUND);
	}

	@PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> createPedido(@RequestBody Pedidos pedido) {
		Pedidos p = repository.save(pedido);

		return p != null ? new ResponseEntity<Pedidos>(p, HttpStatus.CREATED)
				: new ResponseEntity<String>("", HttpStatus.BAD_REQUEST);
	}

	@PatchMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> patchPedido(@RequestBody Pedidos pedido) {
		Optional<Pedidos> optional = repository.findById(pedido.getIdPedido());

		if (!optional.isPresent()) {
			return new ResponseEntity<String>("", HttpStatus.NOT_FOUND);
		}

		Pedidos p = optional.get();
		p = repository.save(pedido);

		return p != null ? new ResponseEntity<Pedidos>(p, HttpStatus.ACCEPTED)
				: new ResponseEntity<String>("", HttpStatus.BAD_REQUEST);
	}

	@PutMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> updatePedido(@RequestBody Pedidos pedido) {
		Optional<Pedidos> optional = repository.findById(pedido.getIdPedido());

		if (!optional.isPresent()) {
			return new ResponseEntity<String>("", HttpStatus.NOT_FOUND);
		}

		Pedidos p = repository.save(pedido);

		return p != null ? new ResponseEntity<Pedidos>(p, HttpStatus.ACCEPTED)
				: new ResponseEntity<String>("", HttpStatus.BAD_REQUEST);
	}

	@DeleteMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> deletePedido(@RequestBody Pedidos pedido) {
		repository.delete(pedido);

		return new ResponseEntity<String>("", HttpStatus.OK);
	}
}