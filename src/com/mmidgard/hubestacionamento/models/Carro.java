package com.mmidgard.hubestacionamento.models;

import java.io.Serializable;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "carro")
public class Carro implements Serializable {

	private static final long serialVersionUID = 4149085837268143831L;

	@DatabaseField(id = true)
	private long id;
	@DatabaseField
	private String placa;
	@DatabaseField
	private String nome;
	@DatabaseField(canBeNull = true)
	private boolean status;

	// private Collection<Estacionamento> estacionamentos;

	public Carro() {
	}

	public Carro(long id, String placa, String nome) {
		this.id = id;
		this.placa = placa;
		this.nome = nome;
	}

	public Carro(models.Carro carro) {
		this.nome = carro.descricao;
		this.placa = carro.placa;
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

	// public Collection<Estacionamento> getEstacionamentos() {
	// return estacionamentos;
	// }
	//
	// public void setEstacionamentos(Collection<Estacionamento>
	// estacionamentos) {
	// this.estacionamentos = estacionamentos;
	// }

}
