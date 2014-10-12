package com.mmidgard.hubestacionamento.models;

import java.io.Serializable;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "estacionamento")
public class Estacionamento implements Serializable {

	private static final long serialVersionUID = -2402310079770322100L;
	@DatabaseField(id = true)
	private long id;
	@DatabaseField
	private String horaInicio;
	@DatabaseField
	private String horaFinal;
	@DatabaseField
	private double valor;
	
	public Estacionamento()
	{
	}

	public Estacionamento(models.Estacionamento estacionamento) {
		this.horaInicio = estacionamento.data_inicio;
		this.horaFinal = estacionamento.data_final;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getHoraInicio() {
		return horaInicio;
	}

	public void setHoraInicio(String horaInicio) {
		this.horaInicio = horaInicio;
	}

	public String getHoraFinal() {
		return horaFinal;
	}

	public void setHoraFinal(String horaFinal) {
		this.horaFinal = horaFinal;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

}
