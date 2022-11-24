package com.teste.miniautorizador.service;

import java.math.BigDecimal;
import java.util.Optional;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.teste.miniautorizador.dominio.Cartao;
import com.teste.miniautorizador.dto.CartaoDTO;
import com.teste.miniautorizador.dto.TransacaoDTO;
import com.teste.miniautorizador.exception.CartaoJaExisteException;
import com.teste.miniautorizador.exception.CartaoSemSaldoException;
import com.teste.miniautorizador.exception.TransacaoAutorizacaoBarradaException;

public interface CartaoService {
       
    public BigDecimal obterSaldoCartao(String numero) throws CartaoSemSaldoException;
	
	public CartaoDTO criarCartao(CartaoDTO cartaoDTO) throws CartaoJaExisteException, JsonProcessingException;
	
	public void verificarSeExisteNumeroCartao(Optional<Cartao> optionalCartao) throws TransacaoAutorizacaoBarradaException;
	
	public void verificarSenhaCartao(Optional<Cartao> optionalCartao, TransacaoDTO transacaoDTO) throws TransacaoAutorizacaoBarradaException;
	
	public void possuiSaldoCartao(Optional<Cartao> optionalCartao, TransacaoDTO transacaoDTO) throws TransacaoAutorizacaoBarradaException;
	
	public Cartao atualizarSaldoCartao(Optional<Cartao> optionalCartao, TransacaoDTO transacaoDTO);
		
}
