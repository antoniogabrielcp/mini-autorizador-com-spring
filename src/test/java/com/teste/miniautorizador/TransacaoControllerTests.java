package com.teste.miniautorizador;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.teste.miniautorizador.dto.TransacaoDTO;
import com.teste.miniautorizador.service.TransacaoService;

@ExtendWith(MockitoExtension.class)
public class TransacaoControllerTests {
		
	@Mock
	private TransacaoService transacaoService;

	@Test
	public void criarCartao() throws Exception {
		String numeroCartao = "6549873025634509";
		TransacaoDTO transacaoDTO = new TransacaoDTO();
		transacaoDTO.setNumeroCartao(numeroCartao);
		transacaoDTO.setSenhaCartao("1234");
		transacaoDTO.setValor(new BigDecimal(20));

		when(transacaoService.efetuarTransacao(transacaoDTO)).thenReturn("OK");

		String resposta = transacaoService.efetuarTransacao(transacaoDTO);
		assertEquals(resposta, "OK");

	}
	
}
