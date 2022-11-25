package com.teste.miniautorizador.enums;

import com.teste.miniautorizador.dominio.CriarCartao;
import com.teste.miniautorizador.dominio.EfetuarDebito;
import com.teste.miniautorizador.dominio.ObterSaldo;
import com.teste.miniautorizador.dominio.Operacao;

import java.util.Arrays;

public enum OperacaoTipo {
	CRIAR_CARTAO("Criar Cartao", CriarCartao.class),
	OBTER_SALDO("Obter Saldo", ObterSaldo.class),
	EFETUAR_DEBITO("Efetuar Débito", EfetuarDebito.class);
	
	private String name;
	private Class<? extends Operacao> tipo;
	
	OperacaoTipo(String name, Class<? extends Operacao> tipo){
		this.name = name;
		this.tipo = tipo;
	}
	
    public static OperacaoTipo of(String value) {
        return Arrays.stream(values()).filter(type -> type.name.equalsIgnoreCase(value)).findFirst().get();
    }
	
	public String getName() {
		return name;
	}
	
	public Class<? extends Operacao> getTipo(){
		return tipo;
	}

}
