package com.teste.miniautorizador;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.math.BigDecimal;
import java.util.Date;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.teste.miniautorizador.dominio.Cartao;
import com.teste.miniautorizador.dominio.Transacao;
import com.teste.miniautorizador.repository.CartaoRepository;
import com.teste.miniautorizador.repository.TransacaoRepository;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class TransacaoRepositoryTests {

	@Autowired
	private CartaoRepository cartaoRepository;
	
	@Autowired
	private TransacaoRepository transacaoRepository;

	@Test
	public void efetuarTransacao() {
		Cartao cartao = new Cartao(null, "6549873025634509", "1234", new BigDecimal(500), null);
		cartaoRepository.save(cartao);
		Integer countCartao = cartaoRepository.findAll().size();
		assertEquals(1, countCartao);
		
		Transacao transacao = new Transacao();
		transacao.setCartao(cartao);
		transacao.setValor(new BigDecimal(20));
		transacao.setDataTransacao(new Date());
		
		cartao.setSaldo(cartao.getSaldo().subtract(transacao.getValor()));
		cartaoRepository.save(cartao);
		
		transacaoRepository.save(transacao);
		Integer countTransacao = transacaoRepository.findAll().size();
		assertEquals(1, countTransacao);
		
		Cartao cartaoComSaldoAlterado = cartaoRepository.findByNumeroCartao("6549873025634509").get();
		assertEquals(cartao, cartaoComSaldoAlterado);
		
	}

}
