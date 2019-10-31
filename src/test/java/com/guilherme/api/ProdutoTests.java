package com.guilherme.api;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.guilherme.api.model.Categorias;
import com.guilherme.api.model.Produtos;
import com.guilherme.api.repository.ProdutoRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProdutoTests {

	@Autowired
	private ProdutoRepository repository;

	private TestRestTemplate template = new TestRestTemplate();

	@Test
	void saveProdutos() {
		Categorias categoria = new Categorias();
		categoria.setIdCategoria(1);
		Produtos produto = new Produtos(categoria, "Kinea", 78.6, 1000, "", "");

		Produtos p = repository.save(produto);

		assertNotNull(p);

		List<Produtos> pedidos = repository.findByIdCategoria(1);

		assertFalse(pedidos.isEmpty());
	}

	@Test
	void produtoListRepository() {
		List<Produtos> pedidos = repository.findAll();

		assertNotNull(pedidos);
	}

	@Test
	void produtoRepository() {
		Optional<Produtos> optional = repository.findById(3);

		assertThat(optional.isPresent());
	}

	@Test
	void responseOk() {
		ResponseEntity<String> response = template.getForEntity("http://localhost:8080/api/produto", String.class);

		assertTrue(response.getStatusCode().equals(HttpStatus.OK));
	}

	@Test
	void response200() {
		ResponseEntity<String> response = template.getForEntity("http://localhost:8080/api/produto", String.class);

		assertTrue(response.getStatusCodeValue() == 200);
	}

	@Test
	void notFound() {
		ResponseEntity<String> response = template.getForEntity("http://localhost:8080/api/produto/8", String.class);

		assertTrue(response.getStatusCode().equals(HttpStatus.NOT_FOUND));
	}

	@Test
	void errorMessage() {
		ResponseEntity<String> response = template.getForEntity("http://localhost:8080/api/produto/8", String.class);

		assertThat(response.hasBody());
	}

	@Test
	void clienteResponse() {
		ResponseEntity<Produtos> response = template.getForEntity("http://localhost:8080/api/produto/3", Produtos.class);

		assertThat(response.hasBody());
	}

	@Test
	void post() {
		Categorias categoria = new Categorias();
		categoria.setIdCategoria(2);
		Produtos produto = new Produtos(categoria, "Kinea", 100.1, 1000, "", "");

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<Produtos> entity = new HttpEntity<>(produto, headers);

		ResponseEntity<String> response = template.postForEntity("http://localhost:8080/api/produto", entity,
				String.class);

		assertTrue(response.getStatusCode().equals(HttpStatus.CREATED));
	}
}
