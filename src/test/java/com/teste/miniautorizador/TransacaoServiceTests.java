package com.teste.miniautorizador;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.teste.miniautorizador.dominio.Cartao;
import com.teste.miniautorizador.dominio.Transacao;
import com.teste.miniautorizador.dto.CartaoDTO;
import com.teste.miniautorizador.dto.TransacaoDTO;
import com.teste.miniautorizador.exception.CartaoJaExisteException;
import com.teste.miniautorizador.repository.CartaoRepository;
import com.teste.miniautorizador.repository.TransacaoRepository;
import com.teste.miniautorizador.service.CartaoService;
import com.teste.miniautorizador.service.impl.CartaoServiceImpl;
import com.teste.miniautorizador.service.impl.TransacaoServiceImpl;

@ExtendWith(MockitoExtension.class)
public class TransacaoServiceTests {	
	@Mock
	private TransacaoRepository transacaoRepository;
	
	@Mock
	private CartaoRepository cartaoRepository;
	
	@Mock
	private CartaoService cartaoService;
	
	@InjectMocks
	private TransacaoServiceImpl transacaoService;

	@Test
	public void efetuarTransacao_com_sucesso() throws JsonProcessingException, CartaoJaExisteException {
				
		String numeroCartao = "6549873025634509";
		Cartao cartao = new Cartao(1L, "1234", "6549873025634509", new BigDecimal(500), 0L);
		Optional<Cartao> optionalCartao = Optional.of(cartao);
		Mockito.when(cartaoRepository.findByNumeroCartaoWithLock(numeroCartao)).thenReturn(optionalCartao);	
		
		optionalCartao = cartaoRepository.findByNumeroCartaoWithLock(numeroCartao);
		TransacaoDTO transacaoDTO = new TransacaoDTO();
		transacaoDTO.setNumeroCartao(numeroCartao);
		transacaoDTO.setSenhaCartao("1234");
		transacaoDTO.setValor(new BigDecimal(20));
		
		Cartao cartaoSaved = new Cartao(1L, "1234", "6549873025634509", new BigDecimal(480), 0L);
		
		Mockito.when(cartaoService.atualizarSaldoCartao(optionalCartao, transacaoDTO)).thenReturn(cartaoSaved);		
		cartaoSaved = cartaoService.atualizarSaldoCartao(optionalCartao, transacaoDTO);
		
		Transacao transacao = new Transacao();
		transacao.setCartao(cartaoSaved);
		transacao.setValor(transacaoDTO.getValor());
		transacao.setDataTransacao(new Date());
		
		Mockito.when(transacaoRepository.save(transacao)).thenReturn(transacao);		
		transacao = transacaoRepository.save(transacao);
		
        assertTrue(transacao.getValor().compareTo(new BigDecimal(20)) == 0);
        assertTrue(cartaoSaved.getSaldo().compareTo(new BigDecimal(480)) == 0);
	}
	
}
