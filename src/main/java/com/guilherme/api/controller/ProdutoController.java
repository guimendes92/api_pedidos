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

import com.guilherme.api.model.Produtos;
import com.guilherme.api.repository.ProdutoRepository;

@RestController
@RequestMapping("/produto")
public class ProdutoController {

	@Autowired
	private ProdutoRepository repository;

	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getProdutos(
			@RequestParam(value = "idCategoria", required = false, defaultValue = "0") int idCategoria,
			@RequestParam(value = "produto", required = false) String produto) {
		List<Produtos> produtos = new ArrayList<Produtos>();

		if (idCategoria > 0) {
			produtos = repository.findByIdCategoria(idCategoria);
		} else if (produto != null) {
			produtos = repository.findByProduto(produto);
		} else {
			produtos = repository.findAll();
		}

		return produtos.isEmpty() ? new ResponseEntity<String>("", HttpStatus.NOT_FOUND)
				: new ResponseEntity<List<Produtos>>(produtos, HttpStatus.OK);
	}

	@GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getProduto(@PathVariable("id") int id) {
		Optional<Produtos> optional = repository.findById(id);

		return optional.isPresent() ? new ResponseEntity<Produtos>(optional.get(), HttpStatus.OK)
				: new ResponseEntity<String>("", HttpStatus.NOT_FOUND);
	}

	@PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> createPedido(@RequestBody Produtos produto) {
		Produtos p = repository.save(produto);

		return p != null ? new ResponseEntity<Produtos>(p, HttpStatus.CREATED)
				: new ResponseEntity<String>("", HttpStatus.BAD_REQUEST);
	}

	@PatchMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> patchPedidoItem(@RequestBody Produtos produto) {
		Optional<Produtos> optional = repository.findById(produto.getIdProduto());

		if (!optional.isPresent()) {
			return new ResponseEntity<String>("", HttpStatus.NOT_FOUND);
		}

		Produtos p = optional.get();
		p = repository.save(produto);

		return p != null ? new ResponseEntity<Produtos>(p, HttpStatus.ACCEPTED)
				: new ResponseEntity<String>("", HttpStatus.BAD_REQUEST);
	}

	@PutMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> updatePedidoItem(@RequestBody Produtos produto) {
		Optional<Produtos> optional = repository.findById(produto.getIdProduto());

		if (!optional.isPresent()) {
			return new ResponseEntity<String>("", HttpStatus.NOT_FOUND);
		}

		Produtos p = repository.save(produto);

		return p != null ? new ResponseEntity<Produtos>(p, HttpStatus.ACCEPTED)
				: new ResponseEntity<String>("", HttpStatus.BAD_REQUEST);
	}

	@DeleteMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> deletePedidoItem(@RequestBody Produtos produto) {
		repository.delete(produto);

		return new ResponseEntity<String>("", HttpStatus.OK);
	}
}