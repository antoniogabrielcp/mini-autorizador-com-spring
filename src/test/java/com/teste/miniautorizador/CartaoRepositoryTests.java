package com.teste.miniautorizador;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.math.BigDecimal;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.teste.miniautorizador.dominio.Cartao;
import com.teste.miniautorizador.repository.CartaoRepository;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class CartaoRepositoryTests {

	@Autowired
	private CartaoRepository cartaoRepository;

	@Test
	public void criarCartao() {
		Cartao cartao = new Cartao(null, "6549873025634509", "1234", new BigDecimal(500), null);
		cartaoRepository.save(cartao);
		Integer countCartao = cartaoRepository.findAll().size();
		assertEquals(1, countCartao);
	}

	@Test
	public void checkCartaoSalvo() {
		Cartao cartao = new Cartao(null, "6549873025634509", "1234", new BigDecimal(500), null);
		cartaoRepository.save(cartao);
		Integer countCartao = cartaoRepository.findAll().size();
		assertEquals(1, countCartao);
		Optional<Cartao> cartao1 = cartaoRepository.findByNumeroCartao("6549873025634509");

		assertNotNull(cartao1.get());
		assertEquals(cartao, cartao1.get());
	}

	@Test
	public void checkCartaoSalvoComLock() {
		Cartao cartao = new Cartao(null, "6549873025634509", "1234", new BigDecimal(500), null);
		cartaoRepository.save(cartao);
		
		Optional<Cartao> cartao1 = cartaoRepository.findByNumeroCartao("6549873025634509");
		Optional<Cartao> cartao2 = cartaoRepository.findByNumeroCartao("6549873025634509");
		
		assertEquals(cartao1.get(), cartao2.get());
	}

}
