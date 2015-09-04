package br.gov.efprototype.model;

public class Atleta {

	private String nome;
	private Double qualidadeDefesa;
	private Double qualidadeAtaque;
	private Double vigor;
	private Integer idade;
	private Posicao posicao; 
	private Comportamento comportamento; 
	
	
	public Double getJogadaDefesa(){
		Double aleatorio = (Math.random()*50);
		Double resultadoJogada = (qualidadeDefesa*vigor)+aleatorio;
		//System.out.println("Q "+ qualidadeDefesa + " V " + vigor + " A " + aleatorio);
		atulizarVigor(aleatorio);
		return resultadoJogada;
	}
	
	public Double getJogadaAtaque(){
		Double aleatorio = (Math.random()*50);
		Double resultadoJogada = (qualidadeAtaque*vigor)+aleatorio;
		//System.out.println("Q "+ qualidadeAtaque + " V " + vigor + " A " + aleatorio);
		atulizarVigor(aleatorio);
		return resultadoJogada;
	}
	
	public Atleta (String nome, Double qualidadeDefesa, Double qualidadeAtaque, Double fadiga, Posicao posicao, Integer idade, Comportamento comportamento) {
		this.nome = nome;
		this.qualidadeDefesa = qualidadeDefesa;
		this.qualidadeAtaque = qualidadeAtaque;
		this.vigor = fadiga;
		this.posicao = posicao;
		this.idade = idade;
		this.comportamento = comportamento;
	}
	
	public void atulizarVigor(Double aleatorio){
		Double resultado = vigor-(aleatorio/10);
		if (resultado > 1.0) 
			this.vigor = resultado;
		else 
			this.vigor = 1.0;
	}
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}

	public Double getFadiga() {
		return vigor;
	}

	public void setFadiga(Double fadiga) {
		this.vigor = fadiga;
	}

	public Double getVigor() {
		return vigor;
	}

	public void setVigor(Double vigor) {
		this.vigor = vigor;
	}

	public Integer getIdade() {
		return idade;
	}

	public void setIdade(Integer idade) {
		this.idade = idade;
	}

	public Double getQualidadeDefesa() {
		return qualidadeDefesa;
	}

	public void setQualidadeDefesa(Double qualidadeDefesa) {
		this.qualidadeDefesa = qualidadeDefesa;
	}

	public Double getQualidadeAtaque() {
		return qualidadeAtaque;
	}

	public void setQualidadeAtaque(Double qualidadeAtaque) {
		this.qualidadeAtaque = qualidadeAtaque;
	}

	public Posicao getPosicao() {
		return posicao;
	}

	public void setPosicao(Posicao posicao) {
		this.posicao = posicao;
	}

	public Comportamento getComportamento() {
		return comportamento;
	}

	public void setComportamento(Comportamento comportamento) {
		this.comportamento = comportamento;
	}
	
	
}
