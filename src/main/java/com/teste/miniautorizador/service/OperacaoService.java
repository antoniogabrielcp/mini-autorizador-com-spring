package com.teste.miniautorizador.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.teste.miniautorizador.dominio.Cartao;
import com.teste.miniautorizador.dominio.Operacao;
import com.teste.miniautorizador.dto.CartaoDTO;
import com.teste.miniautorizador.factory.AbstractFactory;

@Service
public class OperacaoService {
	
	@Autowired
	private AbstractFactory<Operacao> factory;
	
	public Cartao process(String operacao, CartaoDTO cartaoDTO) {
		Operacao operacaoBean = factory.create(operacao);
		Cartao cartao = operacaoBean.processar(cartaoDTO);
		
		return cartao;
	}
}
