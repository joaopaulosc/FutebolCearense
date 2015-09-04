package br.gov.efprototype.model;

import java.util.ArrayList;
import java.util.List;

public class Time {
	String nome;
	Double moral; // de 1 a 10
	List<Atleta> jogadoresTitulares;
	List<Atleta> jogadoresSuplentes;
	List<Atleta> jogadoresMarcaramGol;
	Integer capacidadeEstadio;
	public Time (String nome, Double moral, Integer capacidadeEstadio) {
		this.nome = nome;
		this. moral =  moral;
		this.capacidadeEstadio = capacidadeEstadio;
		this.jogadoresMarcaramGol = new ArrayList<Atleta>();
	}
	
	public Double getQualidade() {
		Double qualidade = 0.0;
		Integer i =0;
		for (Atleta atleta: jogadoresTitulares){
			qualidade += atleta.getQualidadeAtaque() + atleta.getQualidadeDefesa();
			i+=1;
		}			
		return (qualidade/i);
	}
	
	
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public Double getMoral() {
		return moral;
	}
	public void setMoral(Double moral) {
		this.moral = moral;
	}
	
	public List<Atleta> getJogadoresMarcaramGol() {
		return jogadoresMarcaramGol;
	}

	public void setJogadoresMarcaramGol(List<Atleta> jogadoresMarcaramGol) {
		this.jogadoresMarcaramGol = jogadoresMarcaramGol;
	}

	public Integer getCapacidadeEstadio() {
		return capacidadeEstadio;
	}

	public void setCapacidadeEstadio(Integer capacidadeEstadio) {
		this.capacidadeEstadio = capacidadeEstadio;
	}

	public List<Atleta> getJogadoresTitulares() {
		return jogadoresTitulares;
	}

	public void setJogadoresTitulares(List<Atleta> jogadoresTirulares) {
		this.jogadoresTitulares = jogadoresTirulares;
	}

	public List<Atleta> getJogadoresSuplentes() {
		return jogadoresSuplentes;
	}

	public void setJogadoresSuplentes(List<Atleta> jogadoresSuplentes) {
		this.jogadoresSuplentes = jogadoresSuplentes;
	} 
	
}
