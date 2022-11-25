package com.teste.miniautorizador.dominio;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.teste.miniautorizador.dto.CartaoDTO;
import com.teste.miniautorizador.repository.CartaoRepository;

@Component
public class CriarCartao implements Operacao{
	@Autowired
	private CartaoRepository repository;

	@Override
	public Cartao processar(CartaoDTO cartaoDTO) {
		Cartao cartao = new Cartao(null, cartaoDTO.getNumeroCartao(), cartaoDTO.getSenha(), new BigDecimal(500), null);  
		repository.save(cartao);		
		
		return cartao;
	}

	@Override
	public String nome() {		
		return "Criar Cartao";
	}

}
