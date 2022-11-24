package com.teste.miniautorizador.dto;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class TransacaoDTO {
	private String numeroCartao;
	
	private String senhaCartao;
	
	private BigDecimal valor;

}
