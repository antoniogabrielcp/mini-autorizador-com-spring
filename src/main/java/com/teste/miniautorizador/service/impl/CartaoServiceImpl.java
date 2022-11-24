package com.teste.miniautorizador.service.impl;

import java.math.BigDecimal;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.teste.miniautorizador.dominio.Cartao;
import com.teste.miniautorizador.dto.CartaoDTO;
import com.teste.miniautorizador.dto.TransacaoDTO;
import com.teste.miniautorizador.exception.CartaoJaExisteException;
import com.teste.miniautorizador.exception.CartaoSemSaldoException;
import com.teste.miniautorizador.exception.TransacaoAutorizacaoBarradaException;
import com.teste.miniautorizador.repository.CartaoRepository;
import com.teste.miniautorizador.service.CartaoService;

@Service
public class CartaoServiceImpl implements CartaoService {

	private static final Logger log = LoggerFactory.getLogger(CartaoServiceImpl.class);

	@Autowired
	private CartaoRepository repository;

	public BigDecimal obterSaldoCartao(String numeroCartao) throws CartaoSemSaldoException{		
		Optional<Cartao> optionalCartao = findByNumeroCartao(numeroCartao);
		lancarExcecaoSeCartaoSemSaldo(optionalCartao);
		return optionalCartao.get().getSaldo();
	}

	public CartaoDTO criarCartao(CartaoDTO cartaoDTO) throws CartaoJaExisteException, JsonProcessingException {		
		lancarExcecaoSeNumeroCartaoExistir(cartaoDTO);
						
		Cartao cartao = new Cartao(null, cartaoDTO.getNumeroCartao(), cartaoDTO.getSenha(), new BigDecimal(500), null);  
		repository.save(cartao);		
						
		return cartaoDTO;
	}	

	public void verificarSeExisteNumeroCartao(Optional<Cartao> optionalCartao) throws TransacaoAutorizacaoBarradaException {		
		optionalCartao
		.orElseGet(() -> {
			throw new TransacaoAutorizacaoBarradaException("CARTAO_INEXISTENTE");
		});
		
	}

	public void verificarSenhaCartao(Optional<Cartao> optionalCartao, TransacaoDTO transacaoDTO) throws TransacaoAutorizacaoBarradaException {
		if(!optionalCartao.get().getSenha().equals(transacaoDTO.getSenhaCartao())){
			throw new TransacaoAutorizacaoBarradaException("SENHA_INVALIDA");
		}
	}
	
	@Override
	public void possuiSaldoCartao(Optional<Cartao> optionalCartao, TransacaoDTO transacaoDTO)
			throws TransacaoAutorizacaoBarradaException {
		// TODO Auto-generated method stub
		if((optionalCartao.get().getSaldo().compareTo(transacaoDTO.getValor())) < 0){
			throw new TransacaoAutorizacaoBarradaException("SALDO_INSUFICIENTE");
		}
	}
	
	@Override
	public Cartao atualizarSaldoCartao(Optional<Cartao> optionalCartao, TransacaoDTO transacaoDTO) {
		Cartao cartao = optionalCartao.get();
		cartao.setSaldo(cartao.getSaldo().subtract(transacaoDTO.getValor()));
		return repository.save(cartao);		
	}	
	
	private String converteStringToJson(Object object) throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();  
	    return mapper.writeValueAsString(object);
	}
	
	private void lancarExcecaoSeNumeroCartaoExistir(CartaoDTO cartaoDTO) throws CartaoJaExisteException, JsonProcessingException {
		String cartaoDTOJson = converteStringToJson(cartaoDTO);	
		Optional<Cartao> optionalCartao = findByNumeroCartao(cartaoDTO.getNumeroCartao());
		optionalCartao.ifPresent(data -> { 
		    throw new CartaoJaExisteException(cartaoDTOJson); 
		});
	}
	
	private void lancarExcecaoSeCartaoSemSaldo(Optional<Cartao> optionalCartao) throws CartaoSemSaldoException {
		optionalCartao
		.orElseGet(() -> {
			throw new CartaoSemSaldoException();
		});
	}

	private Optional<Cartao> findByNumeroCartao(String numeroCartao){
		return repository.findByNumeroCartao(numeroCartao);
	}	
	
}
