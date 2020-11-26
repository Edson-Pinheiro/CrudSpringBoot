package com.example.demo.entities;

import javax.persistence.Column;
import javax.persistence.Entity;

import javax.persistence.Id;

@Entity
public class Cliente {

	 @Id
	 @Column(name="cliente_cpf")
	 private Integer cpf; 
	 
 
	 @Column(name="cliente_nome")
	 private String nome;
	 

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Integer getCpf() {
		return cpf;
	}

	public void setCpf(Integer cpf) {
		this.cpf = cpf;
	}

 
	
}
