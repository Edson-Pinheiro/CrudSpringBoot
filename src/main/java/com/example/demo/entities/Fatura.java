package com.example.demo.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Fatura {
	
	@Id
//	@Column(name="id")
//	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	
	@ManyToOne
    @JoinColumn(name="cliente_cpf")
    private Cliente  cliente;

	private int mes;
	
	private double leitura_Atual;
	
	private double leitura_Anterior;
	
	private double consumo;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}


	public int getMes() {
		return mes;
	}

	public void setMes(int mes) {
		this.mes = mes;
	}

	public double getLeitura_Atual() {
		return leitura_Atual;
	}

	public void setLeitura_Atual(double leitura_Atual) {
		this.leitura_Atual = leitura_Atual;
	}

	public double getLeitura_Anterior() {
		return leitura_Anterior;
	}

	public void setLeitura_Anterior(double leitura_Anterior) {
		this.leitura_Anterior = leitura_Anterior;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public double getConsumo() {
		return consumo;
	}

	public void setConsumo(double consumo) {
		this.consumo = consumo;
	}

	

}
