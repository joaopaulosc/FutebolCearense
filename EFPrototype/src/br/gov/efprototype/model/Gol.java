package br.gov.efprototype.model;

public class Gol implements Evento {
	private Atleta jogador;
	private Integer minutoGol;
	public Atleta getJogador() {
		return jogador;
	}
	public void setJogador(Atleta jogador) {
		this.jogador = jogador;
	}
	public Integer getMinutoGol() {
		return minutoGol;
	}
	public void setMinutoGol(Integer minutoGol) {
		this.minutoGol = minutoGol;
	}
	
}
