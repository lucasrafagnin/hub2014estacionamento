package com.mmidgard.hubestacionamento.models;

import java.io.Serializable;

public class Carro implements Serializable {

	private static final long serialVersionUID = 4149085837268143831L;

	private long id;
	private String placa;
	private String nome;
	private boolean status;

	public Carro(String placa, String nome, boolean status) {
		this.placa = placa;
		this.nome = nome;
		this.status = status;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getPlaca() {
		return placa;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

}