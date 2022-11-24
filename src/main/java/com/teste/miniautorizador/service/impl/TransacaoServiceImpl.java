package com.teste.miniautorizador.service.impl;

import java.lang.StackWalker.Option;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Optional;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.teste.miniautorizador.dominio.Cartao;
import com.teste.miniautorizador.dominio.Transacao;
import com.teste.miniautorizador.dto.CartaoDTO;
import com.teste.miniautorizador.dto.TransacaoDTO;
import com.teste.miniautorizador.exception.CartaoJaExisteException;
import com.teste.miniautorizador.exception.CartaoSemSaldoException;
import com.teste.miniautorizador.exception.TransacaoAutorizacaoBarradaException;
import com.teste.miniautorizador.repository.CartaoRepository;
import com.teste.miniautorizador.repository.TransacaoRepository;
import com.teste.miniautorizador.service.CartaoService;
import com.teste.miniautorizador.service.TransacaoService;

@Service
public class TransacaoServiceImpl implements TransacaoService {

	private static final Logger log = LoggerFactory.getLogger(TransacaoServiceImpl.class);

	@Autowired
	private CartaoRepository cartaoRepository;
	
	@Autowired
	private TransacaoRepository repository;
	
	@Autowired
	private CartaoService cartaoService;

	@Override
	@Transactional(Transactional.TxType.REQUIRES_NEW)
	public String efetuarTransacao(TransacaoDTO transacaoDTO) throws TransacaoAutorizacaoBarradaException {
		// TODO Auto-generated method stub
		Optional<Cartao> optionalCartao = cartaoRepository.findByNumeroCartaoWithLock(transacaoDTO.getNumeroCartao());
		
		cartaoService.verificarSeExisteNumeroCartao(optionalCartao);
		cartaoService.verificarSenhaCartao(optionalCartao, transacaoDTO);
		cartaoService.possuiSaldoCartao(optionalCartao, transacaoDTO);
		
		Cartao cartao = cartaoService.atualizarSaldoCartao(optionalCartao, transacaoDTO);
		
		Transacao transacao = new Transacao();
		transacao.setCartao(cartao);
		transacao.setValor(transacaoDTO.getValor());
		transacao.setDataTransacao(new Date());
		
		repository.save(transacao);
		
		return "OK";
	}

	
}
