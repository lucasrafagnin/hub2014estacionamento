package com.mmidgard.hubestacionamento.models;

import java.io.Serializable;
import java.sql.Timestamp;

public class Estacionamento implements Serializable {

	private static final long serialVersionUID = -2402310079770322100L;
	private long id;
	private Timestamp horaInicio;
	private Timestamp horaFinal;
	private double valor;
	
	public Estacionamento(models.Estacionamento estacionamento){
		this.horaInicio = estacionamento.hora_inicio;
		this.horaFinal = estacionamento.hora_final;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Timestamp getHoraInicio() {
		return horaInicio;
	}

	public void setHoraInicio(Timestamp horaInicio) {
		this.horaInicio = horaInicio;
	}

	public Timestamp getHoraFinal() {
		return horaFinal;
	}

	public void setHoraFinal(Timestamp horaFinal) {
		this.horaFinal = horaFinal;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

}
