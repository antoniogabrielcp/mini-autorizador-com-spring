package com.teste.miniautorizador.factory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import com.teste.miniautorizador.dominio.Operacao;
import com.teste.miniautorizador.enums.OperacaoTipo;

@Component
public class FactoryOperacao implements AbstractFactory<Operacao>{
	
	@Autowired
	private ApplicationContext context;

	public Operacao create(String tipo) {		
		return context.getBean(OperacaoTipo.of(tipo).getTipo());
	}

}
