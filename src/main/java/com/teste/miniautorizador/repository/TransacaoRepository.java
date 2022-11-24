package com.teste.miniautorizador.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.teste.miniautorizador.dominio.Transacao;

public interface TransacaoRepository extends JpaRepository<Transacao, Long> {
	
	

}
