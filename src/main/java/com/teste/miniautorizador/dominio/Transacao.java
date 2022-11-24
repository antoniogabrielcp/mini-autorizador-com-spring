package com.teste.miniautorizador.dominio;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Data;

@Entity
@Data
public class Transacao implements Serializable {	
	private static final long serialVersionUID = -8708486761075859603L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private Date dataTransacao;
	
	private BigDecimal valor;
	
	@JoinColumn(name = "cartaoId")
	@ManyToOne(cascade = CascadeType.ALL, optional = false)	
	private Cartao cartao;

}
