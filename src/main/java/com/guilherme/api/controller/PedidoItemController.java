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

import com.guilherme.api.model.PedidoItens;
import com.guilherme.api.repository.PedidoItemRepository;

@RestController
@RequestMapping("/pedidoItem")
public class PedidoItemController {

	@Autowired
	private PedidoItemRepository repository;

	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getPedidoItens(
			@RequestParam(value = "idPedido", required = false, defaultValue = "0") int idPedido) {
		List<PedidoItens> pedidoItens = new ArrayList<PedidoItens>();

		if (idPedido > 0) {
			pedidoItens = repository.findByIdPedido(idPedido);
		} else {
			pedidoItens = repository.findAll();
		}

		return pedidoItens.isEmpty() ? new ResponseEntity<String>("", HttpStatus.NOT_FOUND)
				: new ResponseEntity<List<PedidoItens>>(pedidoItens, HttpStatus.OK);
	}

	@GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getPedidoItem(@PathVariable("id") int id) {
		Optional<PedidoItens> optional = repository.findById(id);

		return optional.isPresent() ? new ResponseEntity<PedidoItens>(optional.get(), HttpStatus.OK)
				: new ResponseEntity<String>("", HttpStatus.NOT_FOUND);
	}

	@PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> createPedidoItem(@RequestBody PedidoItens pedidoItem) {
		PedidoItens p = repository.save(pedidoItem);

		return p != null ? new ResponseEntity<PedidoItens>(p, HttpStatus.CREATED)
				: new ResponseEntity<String>("", HttpStatus.BAD_REQUEST);
	}

	@PatchMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> patchPedidoItem(@RequestBody PedidoItens pedido) {
		Optional<PedidoItens> optional = repository.findById(pedido.getIdItem());

		if (!optional.isPresent()) {
			return new ResponseEntity<String>("", HttpStatus.NOT_FOUND);
		}

		PedidoItens p = optional.get();
		p = repository.save(pedido);

		return p != null ? new ResponseEntity<PedidoItens>(p, HttpStatus.ACCEPTED)
				: new ResponseEntity<String>("", HttpStatus.BAD_REQUEST);
	}

	@PutMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> updatePedidoItem(@RequestBody PedidoItens pedido) {
		Optional<PedidoItens> optional = repository.findById(pedido.getIdItem());

		if (!optional.isPresent()) {
			return new ResponseEntity<String>("", HttpStatus.NOT_FOUND);
		}

		PedidoItens p = repository.save(pedido);

		return p != null ? new ResponseEntity<PedidoItens>(p, HttpStatus.ACCEPTED)
				: new ResponseEntity<String>("", HttpStatus.BAD_REQUEST);
	}

	@DeleteMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> deletePedidoItem(@RequestBody PedidoItens pedido) {
		repository.delete(pedido);

		return new ResponseEntity<String>("", HttpStatus.OK);
	}
}