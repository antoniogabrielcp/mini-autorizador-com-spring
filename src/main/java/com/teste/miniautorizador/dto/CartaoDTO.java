package com.teste.miniautorizador.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CartaoDTO {	
	private String senha;	
	
	private String numeroCartao;

	/*
	 * @Override public String toString() { return "{\r\n" + "      \"senha\": \"" +
	 * senha + "\",\r\n" + "      \"numeroCartao\": \" " +numeroCartao + "\"\r\n" +
	 * "   } "; }
	 */
	
	
}
