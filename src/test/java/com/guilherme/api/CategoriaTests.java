package com.guilherme.api;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.guilherme.api.model.Categorias;
import com.guilherme.api.repository.CategoriaRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
class CategoriaTests {

	@Autowired
	private CategoriaRepository repository;

	private TestRestTemplate template = new TestRestTemplate();

	@Test
	void categoriaListRepository() {
		List<Categorias> categorias = repository.findAll();

		assertThat(!categorias.isEmpty());
	}

	@Test
	void categoriaRepository() {
		Optional<Categorias> optional = repository.findById(1);

		assertThat(optional.isPresent());
	}

	@Test
	void responseOk() {
		ResponseEntity<String> response = template.getForEntity("http://localhost:8080/api/categoria", String.class);

		assertThat(response.getStatusCode().equals(HttpStatus.OK));
	}

	@Test
	void response200() {
		ResponseEntity<String> response = template.getForEntity("http://localhost:8080/api/categoria", String.class);

		assertThat(response.getStatusCodeValue() == 200);
	}

	@Test
	void notFound() {
		ResponseEntity<String> response = template.getForEntity("http://localhost:8080/api/categoria/8", String.class);

		assertThat(response.getStatusCode().equals(HttpStatus.NOT_FOUND));
	}

	@Test
	void errorMessage() {
		ResponseEntity<String> response = template.getForEntity("http://localhost:8080/api/categoria/8", String.class);

		assertThat(response.hasBody());
	}

	@Test
	void categoriaResponse() {
		ResponseEntity<Categorias> response = template.getForEntity("http://localhost:8080/api/categoria/1",
				Categorias.class);

		assertThat(response.hasBody());
	}
}