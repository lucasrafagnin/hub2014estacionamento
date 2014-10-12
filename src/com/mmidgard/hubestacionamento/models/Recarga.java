package com.mmidgard.hubestacionamento.models;

import java.io.Serializable;
import java.util.Date;

import models.Recargas;

public class Recarga implements Serializable {

	private static final long serialVersionUID = -436011359467600256L;
	private long id;
	private Date data;
	private double valor;
	
	public Recarga(Recargas recarga) {
		this.data = recarga.data;
		this.valor = recarga.valor;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

}
