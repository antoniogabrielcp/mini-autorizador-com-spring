package com.teste.miniautorizador;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.teste.miniautorizador.dto.CartaoDTO;
import com.teste.miniautorizador.service.CartaoService;

@ExtendWith(MockitoExtension.class)
public class CartaoControllerTests {

	@Mock
	private CartaoService cartaoService;

	@Test
	public void criarCartao() throws Exception {
		CartaoDTO cartaoDTO = new CartaoDTO("1234", "6549873025634509");

		when(cartaoService.criarCartao(cartaoDTO)).thenReturn(cartaoDTO);

		CartaoDTO cartaoDTOSaved = cartaoService.criarCartao(cartaoDTO);
		assertEquals(cartaoDTO.getNumeroCartao(), cartaoDTOSaved.getNumeroCartao());

	}

	@Test
	public void obterSaldoCartao() throws Exception {		
		when(cartaoService.obterSaldoCartao("6549873025634509")).thenReturn(new BigDecimal(500));

		BigDecimal saldoPesquisa = cartaoService.obterSaldoCartao("6549873025634509");
		assertEquals(new BigDecimal(500), saldoPesquisa);

	}

}
