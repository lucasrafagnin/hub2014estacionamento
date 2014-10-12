package com.mmidgard.hubestacionamento.models;

import java.io.Serializable;
import java.util.Collection;

public class Motorista implements Serializable {

	private static final long serialVersionUID = 5236393908668102442L;
	private long id;
	private String nome;
	private Collection<Carro> carros;
	private Collection<Recarga> recargas;
	private double creditos;
	private boolean status;
	
	public Motorista(models.Motorista motorista) {
		this.nome = motorista.nome;
		this.creditos = motorista.creditos;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Collection<Carro> getCarros() {
		return carros;
	}

	public void setCarros(Collection<Carro> carros) {
		this.carros = carros;
	}

	public double getCreditos() {
		return creditos;
	}

	public void setCreditos(double creditos) {
		this.creditos = creditos;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public Collection<Recarga> getRecargas() {
		return recargas;
	}

	public void setRecargas(Collection<Recarga> recargas) {
		this.recargas = recargas;
	}
}
