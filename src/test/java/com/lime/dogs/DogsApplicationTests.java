package com.lime.dogs;

import com.lime.dogs.entity.Dog;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class DogsApplicationTests {

	@LocalServerPort
	private int port;

	@Autowired
	private TestRestTemplate restTemplate;

	@Test
	public void getAllDogs() {
		TestRestTemplate testRestTemplate = this.restTemplate.withBasicAuth("admin", "password");
		ResponseEntity<Object> response = testRestTemplate.getForEntity("http://localhost:" + port + "/dogs/", Object.class);
		assertThat(response.getStatusCode(), equalTo(HttpStatus.OK));
	}

	@Test
	public void getADog() {
		TestRestTemplate testRestTemplate = this.restTemplate.withBasicAuth("admin", "password");
		ResponseEntity<Dog> response =
				testRestTemplate.getForEntity("http://localhost:" + port + "/dogs/1", Dog.class);

		assertThat(response.getStatusCode(), equalTo(HttpStatus.OK));
	}

}
