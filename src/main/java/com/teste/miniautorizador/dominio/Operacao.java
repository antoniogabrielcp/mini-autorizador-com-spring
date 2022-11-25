package com.teste.miniautorizador.dominio;

import com.teste.miniautorizador.dto.CartaoDTO;

public interface Operacao {
	
	Cartao processar(CartaoDTO cartaoDTO);
	
	String nome();

}
