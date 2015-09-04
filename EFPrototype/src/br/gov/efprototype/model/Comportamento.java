package br.gov.efprototype.model;


public enum Comportamento {	
	
	Fair_Play(1,"Fair Play"), Cordeirinho(2,"Cordeirinho"), Cavalheiro(3,"Cavalheiro"), Caneleiro(4,"Caneleiro"), Caceteiro(5,"Caceteiro"), Sarrafeiro(6,"Sarrafeiro") ;
	private final int codigo;
	private String descricao;
	
	/**
	 * Construtor da enumeration
	 * @param _codigo    O codigo do registro
	 * @param _descricao A descrição do registro
	 */
	private Comportamento(int _codigo, String _descricao) {
		this.codigo = _codigo;
		this.descricao = _descricao;
	}
}

