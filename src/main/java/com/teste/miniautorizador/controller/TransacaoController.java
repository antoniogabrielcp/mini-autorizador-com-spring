package com.teste.miniautorizador.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.teste.miniautorizador.dto.TransacaoDTO;
import com.teste.miniautorizador.exception.TransacaoAutorizacaoBarradaException;
import com.teste.miniautorizador.service.TransacaoService;

@RestController
@RequestMapping("/transacoes")
public class TransacaoController {

	@Autowired
	private TransacaoService service;	

	@PostMapping
	public String efetuarTransacao(@RequestBody TransacaoDTO transacaoDTO) throws TransacaoAutorizacaoBarradaException{
		return service.efetuarTransacao(transacaoDTO);		
	}	
}
