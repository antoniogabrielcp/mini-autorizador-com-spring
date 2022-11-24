package com.teste.miniautorizador;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;
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
import com.teste.miniautorizador.exception.CartaoJaExisteException;
import com.teste.miniautorizador.repository.CartaoRepository;
import com.teste.miniautorizador.service.impl.CartaoServiceImpl;

@ExtendWith(MockitoExtension.class)
public class CartaoServiceTests {	
	@Mock
	private CartaoRepository cartaoRepository;	
	
	@InjectMocks
	private CartaoServiceImpl cartaoService;

	@Test
	public void criarCartao_com_sucesso() throws JsonProcessingException, CartaoJaExisteException {
		//CartaoDTO cartaoDTO = new CartaoDTO("1234", "6549873025634509");
		Cartao cartao = new Cartao(null, "1234", "6549873025634509", new BigDecimal(500), null);
		Mockito.when(cartaoRepository.save(ArgumentMatchers.any(Cartao.class))).thenReturn(cartao);	
		
		Cartao cartaoSaved = cartaoRepository.save(cartao);
        assertThat(cartaoSaved.getNumeroCartao()).isNotNull();
	}
	
	@Test
	public void obterSaldoComCartao_com_sucesso() throws JsonProcessingException, CartaoJaExisteException {
		
		Cartao cartao = new Cartao(null, "1234", "6549873025634509", new BigDecimal(500), null);	
		
		Optional<Cartao> optionalCartao = Optional.of(cartao);
		Mockito.when(cartaoRepository.findByNumeroCartao("6549873025634509")).thenReturn(optionalCartao);	
		
		Optional<Cartao> optionalCartaoSaved = cartaoRepository.findByNumeroCartao("6549873025634509");
        assertThat(optionalCartaoSaved.get().getSaldo().compareTo(optionalCartao.get().getSaldo())).isEqualTo(0);
	}
	
	@Test
	public void verificarSeExisteNumeroCartao_com_sucesso() throws JsonProcessingException, CartaoJaExisteException {
		
		Cartao cartao = new Cartao(null, "1234", "6549873025634509", new BigDecimal(500), null);
				
		Optional<Cartao> optionalCartao = Optional.of(cartao);
		Mockito.when(cartaoRepository.findByNumeroCartao("6549873025634509")).thenReturn(optionalCartao);	
				
		Optional<Cartao> optionalCartaoSaved = cartaoRepository.findByNumeroCartao("6549873025634509");
        assertTrue(optionalCartaoSaved.get().getNumeroCartao().equals(optionalCartao.get().getNumeroCartao()));
	}	
	
	
	@Test
	public void verificarSenhaCartao_com_sucesso() throws JsonProcessingException, CartaoJaExisteException {
		
		Cartao cartao = new Cartao(null, "1234", "6549873025634509", new BigDecimal(500), null);
				
		Optional<Cartao> optionalCartao = Optional.of(cartao);
		Mockito.when(cartaoRepository.findByNumeroCartao("6549873025634509")).thenReturn(optionalCartao);	
				
		Optional<Cartao> optionalCartaoSaved = cartaoRepository.findByNumeroCartao("6549873025634509");
        assertTrue(optionalCartaoSaved.get().getSenha().equals(optionalCartao.get().getSenha()));
	}
	
	@Test
	public void possuiSaldoCartao_com_sucesso() throws JsonProcessingException, CartaoJaExisteException {
		BigDecimal saldoInicial = new BigDecimal(500);
		Cartao cartao = new Cartao(null, "1234", "6549873025634509", saldoInicial, null);
				
		Optional<Cartao> optionalCartao = Optional.of(cartao);
		Mockito.when(cartaoRepository.findByNumeroCartao("6549873025634509")).thenReturn(optionalCartao);	
				
		Optional<Cartao> optionalCartaoSaved = cartaoRepository.findByNumeroCartao("6549873025634509");
		
		optionalCartaoSaved.get().setSaldo(optionalCartaoSaved.get().getSaldo().subtract(new BigDecimal(20)));
		Mockito.when(cartaoRepository.save(ArgumentMatchers.any(Cartao.class))).thenReturn(optionalCartaoSaved.get());
		
		Cartao cartaoSaved = cartaoRepository.save(optionalCartaoSaved.get());		
		assertThat(saldoInicial.compareTo(cartaoSaved.getSaldo())).isEqualTo(1);
	}
	
}
