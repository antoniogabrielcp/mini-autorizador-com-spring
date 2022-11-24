package com.teste.miniautorizador.service;

import com.teste.miniautorizador.dto.TransacaoDTO;
import com.teste.miniautorizador.exception.TransacaoAutorizacaoBarradaException;

public interface TransacaoService {
       
    public String efetuarTransacao(TransacaoDTO transacaoDTO) throws TransacaoAutorizacaoBarradaException;
}
