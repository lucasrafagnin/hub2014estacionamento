package com.mmidgard.hubestacionamento.models;

import java.io.Serializable;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "motorista")
public class Motorista implements Serializable {

	private static final long serialVersionUID = 5236393908668102442L;
	@DatabaseField(id = true)
	private Long id;
	@DatabaseField
	private double creditos;
	@DatabaseField(canBeNull = true)
	private String nome;
	@DatabaseField(canBeNull = true)
	private boolean status;

	public Motorista() {
	}

	public Motorista(models.Motorista motorista) {
		this.nome = motorista.nome;
		this.creditos = motorista.creditos;
	}

	public Motorista(Long id) {
		this.id = id;
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

}
