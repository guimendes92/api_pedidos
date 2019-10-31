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
import com.guilherme.api.repository.ClienteRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ClienteTests {

	@Autowired
	private ClienteRepository repository;

	private TestRestTemplate template = new TestRestTemplate();
	
	@Test
	void saveCliente() {
		Clientes clientes = new Clientes("Guilherme", "test@tes.com", "", "", "", "", "", "");
		
		Clientes c = repository.save(clientes);
		
		assertNotNull(c);
		
		List<Clientes> cList = repository.findByNome("Guilherme");
		
		assertFalse(cList.isEmpty());
		
		List<Clientes> clienteList = repository.findByEmail("test@tes.com");
		
		assertFalse(clienteList.isEmpty());
	}

	@Test
	void clienteListRepository() {
		List<Clientes> clientes = repository.findAll();

		assertNotNull(clientes);
	}

	@Test
	void clienteRepository() {
		Optional<Clientes> optional = repository.findById(3);

		assertThat(optional.isPresent());
	}

	@Test
	void responseOk() {
		ResponseEntity<String> response = template.getForEntity("http://localhost:8080/api/cliente", String.class);

		assertTrue(response.getStatusCode().equals(HttpStatus.OK));
	}

	@Test
	void response200() {
		ResponseEntity<String> response = template.getForEntity("http://localhost:8080/api/cliente", String.class);

		assertTrue(response.getStatusCodeValue() == 200);
	}

	@Test
	void notFound() {
		ResponseEntity<String> response = template.getForEntity("http://localhost:8080/api/cliente/8", String.class);

		assertTrue(response.getStatusCode().equals(HttpStatus.NOT_FOUND));
	}

	@Test
	void errorMessage() {
		ResponseEntity<String> response = template.getForEntity("http://localhost:8080/api/cliente/8", String.class);

		assertThat(response.hasBody());
	}

	@Test
	void clienteResponse() {
		ResponseEntity<Clientes> response = template.getForEntity("http://localhost:8080/api/cliente/3",
				Clientes.class);

		assertThat(response.hasBody());
	}
	
	@Test
	void post() {
		Clientes clientes = new Clientes("Camila", "camila@tes.com", "", "", "", "", "", "");
		
		HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Clientes> entity = new HttpEntity<>(clientes, headers);

        ResponseEntity<String> response = template.postForEntity("http://localhost:8080/api/cliente", entity, String.class);
        
        assertTrue(response.getStatusCode().equals(HttpStatus.CREATED));
	}
}