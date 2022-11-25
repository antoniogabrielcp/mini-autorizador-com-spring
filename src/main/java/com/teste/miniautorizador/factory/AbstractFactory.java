package com.teste.miniautorizador.factory;

public interface AbstractFactory<T> {
	T create(String type);
}
