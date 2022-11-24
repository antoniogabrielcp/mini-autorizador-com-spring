package com.teste.miniautorizador.dominio;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Version;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Cartao implements Serializable{
	
	private static final long serialVersionUID = -1657080992838646420L;

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;	
	
	private String numeroCartao;
	
	private String senha;
	
	private BigDecimal saldo;
	
	@Version
    private Long version;	

}
