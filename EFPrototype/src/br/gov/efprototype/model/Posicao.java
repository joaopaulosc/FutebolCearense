package br.gov.efprototype.model;


public enum Posicao {	
	GOLEIRO(1,"G"), DEFESA(2, "D"), MEIOCAMPO(3,"M"), ATACANTE(4,"A");
	private final int codigo;
	private String descricao;
	
	/**
	 * Construtor da enumeration
	 * @param _codigo    O codigo do registro
	 * @param _descricao A descrição do registro
	 */
	private Posicao(int _codigo, String _descricao) {
		this.codigo = _codigo;
		this.descricao = _descricao;
	}
}

