package com.teste.miniautorizador.controller;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.teste.miniautorizador.dto.CartaoDTO;
import com.teste.miniautorizador.exception.CartaoJaExisteException;
import com.teste.miniautorizador.exception.CartaoSemSaldoException;
import com.teste.miniautorizador.service.CartaoService;

@RestController
@RequestMapping("/cartoes")
public class CartaoController {

	@Autowired
	private CartaoService service;

	@PostMapping
	public CartaoDTO criarCartao(@RequestBody CartaoDTO cartaoDTO) throws CartaoJaExisteException, JsonProcessingException {
		service.criarCartao(cartaoDTO);
		return cartaoDTO;
	}
	
	@GetMapping(path = "/{numeroCartao}")
	public BigDecimal obterSaldoCartao(@PathVariable("numeroCartao") String numeroCartao) throws CartaoSemSaldoException {
		return service.obterSaldoCartao(numeroCartao);
	}
}
