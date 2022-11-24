package com.teste.miniautorizador;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;
import java.net.URI;
import java.util.Collections;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.teste.miniautorizador.dominio.Cartao;
import com.teste.miniautorizador.dto.CartaoDTO;
import com.teste.miniautorizador.repository.CartaoRepository;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class MiniAutorizadorApplicationTests {

	// bind the above RANDOM_PORT
	@LocalServerPort
	private int port;

	@Autowired
	private TestRestTemplate restTemplate;	

	@Autowired
	CartaoRepository cartaoRepository;
	
	@Test
	
	public void contextLoad() {		
	}

	@Test
	public void criarCartao() throws Exception {
		CartaoDTO cartaoDTO = new CartaoDTO("1234", "6549873025634509");
		
		final String baseUrl = "http://localhost:" + port + "/";
		URI uri = new URI(baseUrl + "cartoes/");

		HttpHeaders headers = new HttpHeaders();

		HttpEntity<CartaoDTO> request = new HttpEntity<>(cartaoDTO, headers);

		ResponseEntity<String> response = restTemplate.postForEntity(uri, request, String.class);

		String expected = "{\"senha\":\"1234\",\"numeroCartao\":\"6549873025634509\"}";
		assertEquals(expected, response.getBody());

	}

	@Test
	public void obterSaldoCartao() throws Exception {
			
		String numeroCartao = "6549873025634510";	
		Cartao cartao = new Cartao(null, numeroCartao, "1234", new BigDecimal(500), null);			
		cartaoRepository.save(cartao);		
		
		final String baseUrl = "http://localhost:"+ port +"/";				
		URI uri = new URI(baseUrl + "cartoes/" + numeroCartao);
		
		ResponseEntity<BigDecimal> response = restTemplate.getForEntity(uri, BigDecimal.class);
	    
	    //Verify request succeed
	    assertEquals(200, response.getStatusCodeValue());					
		assertEquals(new BigDecimal("500.00"), response.getBody());

	}
}
