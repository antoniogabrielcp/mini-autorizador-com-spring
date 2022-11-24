package com.teste.miniautorizador.repository;

import java.util.Optional;

import javax.persistence.LockModeType;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.teste.miniautorizador.dominio.Cartao;

public interface CartaoRepository extends JpaRepository<Cartao, Long> {
	
	Optional<Cartao> findByNumeroCartao(String numeroCartao);
	
	@Lock(LockModeType.OPTIMISTIC_FORCE_INCREMENT)
	@Query("select car from Cartao car where car.numeroCartao = :numeroCartao")
	Optional<Cartao> findByNumeroCartaoWithLock(@Param("numeroCartao")String numeroCartao);
}
