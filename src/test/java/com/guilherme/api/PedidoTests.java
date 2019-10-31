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

import com.guilherme.api.model.Clientes;
import com.guilherme.api.model.Pedidos;
import com.guilherme.api.repository.PedidoRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PedidoTests {

	@Autowired
	private PedidoRepository repository;

	private TestRestTemplate template = new TestRestTemplate();

	@Test
	void savePedido() {
		Clientes clientes = new Clientes("Camila", "camila@tes.com", "", "", "", "", "", "");
		clientes.setIdCliente(3);
		Pedidos pedido = new Pedidos(null, clientes, "test", "");

		Pedidos p = repository.save(pedido);

		assertNotNull(p);

		List<Pedidos> pedidos = repository.findByStatus("test");

		assertFalse(pedidos.isEmpty());
	}

	@Test
	void pedidoListRepository() {
		List<Pedidos> pedidos = repository.findAll();

		assertNotNull(pedidos);
	}

	@Test
	void pedidoRepository() {
		Optional<Pedidos> optional = repository.findById(3);

		assertThat(optional.isPresent());
	}

	@Test
	void responseOk() {
		ResponseEntity<String> response = template.getForEntity("http://localhost:8080/api/pedido", String.class);

		assertTrue(response.getStatusCode().equals(HttpStatus.OK));
	}

	@Test
	void response200() {
		ResponseEntity<String> response = template.getForEntity("http://localhost:8080/api/pedido", String.class);

		assertTrue(response.getStatusCodeValue() == 200);
	}

	@Test
	void notFound() {
		ResponseEntity<String> response = template.getForEntity("http://localhost:8080/api/pedido/8", String.class);

		assertTrue(response.getStatusCode().equals(HttpStatus.NOT_FOUND));
	}

	@Test
	void errorMessage() {
		ResponseEntity<String> response = template.getForEntity("http://localhost:8080/api/pedido/8", String.class);

		assertThat(response.hasBody());
	}

	@Test
	void clienteResponse() {
		ResponseEntity<Pedidos> response = template.getForEntity("http://localhost:8080/api/pedido/3", Pedidos.class);

		assertThat(response.hasBody());
	}

	@Test
	void post() {
		Clientes clientes = new Clientes("Camila", "camila@tes.com", "", "", "", "", "", "");
		clientes.setIdCliente(3);
		Pedidos pedido = new Pedidos(null, clientes, "test", "");

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<Pedidos> entity = new HttpEntity<>(pedido, headers);

		ResponseEntity<String> response = template.postForEntity("http://localhost:8080/api/pedido", entity,
				String.class);

		assertTrue(response.getStatusCode().equals(HttpStatus.CREATED));
	}
}
